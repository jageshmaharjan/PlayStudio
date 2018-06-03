package Jugs.PlayStudio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class psDataAnalyis
{
    public String filepath = "/home/jugs/Downloads/psTransactions.txt";
    public ArrayList<String> records = new ArrayList<>();
    public HashSet<String> userID = new HashSet<>();
    public HashMap<String, ArrayList<String> > userBasedRecord = new HashMap<String, ArrayList<String>>();
    public List<Transaction> transactionList = new ArrayList<>();

    public static void main(String[] args) throws Exception
    {
        psDataAnalyis psData = new psDataAnalyis();
        psData.userDatabase();
    }

    private void userDatabase() throws Exception {
        File file = new File(filepath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine();
        String line;
        while ((line = br.readLine()) != null)
        {
            try
            {
                String[] recStr = line.split("\t");
                userID.add(recStr[0]);
//                transactionList.add(new Transaction(recStr[0], formatter(recStr[1]), formatter(recStr[2]), Integer.parseInt(recStr[3]), Float.parseFloat(recStr[4]),
//                        Float.parseFloat(recStr[5]), Float.parseFloat(recStr[6]), Long.parseLong(recStr[7]), Long.parseLong(recStr[8]),
//                        Long.parseLong(recStr[9]), Long.parseLong(recStr[10]),Boolean.parseBoolean(recStr[11]), Long.parseLong(recStr[12]),recStr[13]));
                records.add(line);

            }
            catch (Exception e)
            {
                System.out.println("Not an ID");
            }
        }

        /*
        * Solution To Question 1
        * */
        HashMap<String, ArrayList<String>> moneytizersByDate = getRecordsByDate(records);
        System.out.println("Total Monetizer on '11/16/2017' : " + moneytizersByDate.size());
        HashMap<String, String> monitisedDateRange = moneytizerMonytizeOnDateRange(records, moneytizersByDate);
        System.out.println("Total Monetizer Moneitised in Between '11/17/2017' and '11/23/2017' who were monetizer in '11/16/2017' : " + monitisedDateRange.size());

        /*
        * Solution To Question 2 -> a and b
        * */
        analysisABgameTest(records);

        /*
        * Solution To Question 3 -> a , b and c
        * */
        //No records Found on "18/01/2018"
        newGameFeature(records);

        /*
        * Solution To Question 4 -> a and b [Clustering Algorithm]
        * */
        clusteringAlgorithm(records);


    }

    private void clusteringAlgorithm(ArrayList<String> records) throws Exception
    {
        KMeansAlgorithm kmeans = new KMeansAlgorithm();
        kmeans.readFile(records);

    }

    private void newGameFeature(ArrayList<String> records) throws Exception
    {
        for (String rec : records)
        {
            String[] strSplit = rec.split(",");

            Date givenDate = new SimpleDateFormat("MM/dd/yyyy").parse("18/01/2018");
            Date cmpDate = new SimpleDateFormat("MM/dd/yyyy").parse(strSplit[1]);
            if (cmpDate.compareTo(givenDate) == 0)
            {
                System.out.println();
            }
        }
    }

    private void analysisABgameTest(ArrayList<String> records) throws ParseException
    {
        float amountSpendA = (float) 0.0;
        float amountSpendB = (float) 0.0;
        long timeSpendA = 0;
        long timeSpendB = 0;
        long nosOfTransactionA = 0;
        long nosOfTransactionB = 0;
        Date fromDate = new SimpleDateFormat("MM/dd/yyyy").parse("12/09/2017");
        Date toDate = new SimpleDateFormat("MM/dd/yyyy").parse("12/19/2017");
        for (String rec : records)
        {
            String[] recArray = rec.split(",");
            Date cmdDate = new SimpleDateFormat("MM/dd/yyyy").parse(recArray[1]);
            if (cmdDate.compareTo(fromDate) >= 0 && cmdDate.compareTo(toDate) <= 0)
            {

                if (recArray[13].equals("A"))
                {
                    amountSpendA += Float.parseFloat(recArray[4]);
                    timeSpendA += Long.parseLong(recArray[12]);
                    nosOfTransactionA += Long.parseLong(recArray[3]);

                }
                else if (recArray[13].equals("B"))
                {
                    amountSpendB += Float.parseFloat(recArray[4]);
                    timeSpendB += Long.parseLong(recArray[12]);
                    nosOfTransactionB += Long.parseLong(recArray[3]);
//                    System.out.println(amountSpendB);
                }
            }
        }
        System.out.println("Total Amount Spend on Game A (in USD) :" + amountSpendA);
        System.out.println("Total Amount Spend on Game A (in USD) : " + amountSpendB);
        System.out.println("Total Time Spend on Game A (in hrs): " +(timeSpendA/60)/60);
        System.out.println("Total Time Spend on Game B (in hrs): " + timeSpendB/60/60);
        System.out.println("Total Transactions in Game A : " + nosOfTransactionA);
        System.out.println("Total Transactions in Game B : " + nosOfTransactionB);

    }


    private HashMap<String, String> moneytizerMonytizeOnDateRange(ArrayList<String> records, HashMap<String, ArrayList<String>> moneytizers) throws ParseException
    {
        HashMap<String, String> monetizerMontized = new HashMap<>();
        Date fromDate = new SimpleDateFormat("MM/dd/yyyy").parse("11/17/2017");
        Date toDate = new SimpleDateFormat("MM/dd/yyyy").parse("11/23/2017");
        for (String rec : records)
        {
            Date cmpDate = new SimpleDateFormat("MM/dd/yyyy").parse(rec.split(",")[1]);
            if (cmpDate.compareTo(fromDate) >= 0 && cmpDate.compareTo(toDate) <= 0)
            {
                for (Map.Entry pair : moneytizers.entrySet())
                {
                    if (pair.getKey().equals(rec.split(",")[0]) && Float.parseFloat(rec.split(",")[4]) > 0)
                    {
                        monetizerMontized.put(rec.split(",")[0] , rec);
                    }
                }
            }
        }
        return monetizerMontized;
    }

    private HashMap<String, ArrayList<String>> getRecordsByDate(ArrayList<String> records)
    {
        HashMap<String, ArrayList<String> > result = new HashMap<>();
        for (String rec : records)
        {
            if ((rec.split(",")[1]).equals("11/16/2017"))
            {
                if (result.containsKey(rec.split(",")[0]))
                {
                    ArrayList<String> value = result.get(rec.split(",")[0]);
                    value.add(rec);
                    result.put(rec.split(",")[0], value);
                }
                else
                {
                    ArrayList<String> value = new ArrayList<>();
                    value.add(rec);
                    result.put(rec.split(",")[0], value);
                }
            }
        }
        return result;
    }

    private Date formatter(String dateInString) throws ParseException
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        if (dateInString.equals(""))
        {
            date = formatter.parse("01/01/1900");
        }
        else
        {
            date = formatter.parse(dateInString);
        }
        return date;
    }
}
