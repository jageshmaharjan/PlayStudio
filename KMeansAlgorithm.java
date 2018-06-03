package Jugs.PlayStudio;

import java.io.*;
import java.util.*;

public class KMeansAlgorithm
{

    public int cols = 14;
    public int rows;
    public static Map<String,Float> mapLastCol = new HashMap<String, Float>();
    public static String path1 = "/home/jugs/Downloads/psTransactions.txt";

    public KMeansAlgorithm()
    {
        mapLastCol = mapLastcolData();
    }

    private Map<String, Float> mapLastcolData()
    {
        Map<String, Float> map = new HashMap<>();
        map.put("A", Float.valueOf(1));
        map.put("B", Float.valueOf(2));
        map.put("NA", Float.valueOf(3));
        return map;
    }

    public void readFile(ArrayList<String> recordsList) throws FileNotFoundException
    {
        int j = 0;
        rows = recordsList.size();
        Float[][] records = new Float[rows][cols];
        for (String line : recordsList)
        {
            String[] splitstr = line.split(",");
            Float[] col = new Float[splitstr.length];

            for (int i=0; i<cols; i++)
            {
                if (i == 13)
                {
                    records[j][i] = getColValABGame(splitstr[i]);
                }
                else if ((i == 1) || (i == 2))
                {
                    records[j][i] = Float.valueOf(Float.parseFloat(splitstr[1].replaceAll("/","")));
                }
                else
                {
                    records[j][i] = Float.valueOf(splitstr[i]);
                }
            }
            j++;
        }
        System.out.println();
        Float[][] normalizedRec = normalizeRecords(records);

        evaluateKMeanOperation(normalizedRec,rows,cols);


    }

    private void evaluateKMeanOperation(Float[][] records, int rows, int cols)
    {
        //Considering The Cluster K = 5
        Float[] centroid1 = new Float[cols];
        Float[] centroid2 = new Float[cols];
        Float[] centroid3 = new Float[cols];
        Float[] centroid4 = new Float[cols];
        Float[] centroid5 = new Float[cols];
        Float[] newCentroid1 = new Float[cols];
        Float[] newCentroid2 = new Float[cols];
        Float[] newCentroid3 = new Float[cols];
        Float[] newCentroid4 = new Float[cols];
        Float[] newCentroid5 = new Float[cols];
        ArrayList<Float[]> cluster1 = new ArrayList<Float[]>();
        ArrayList<Float[]> cluster2 = new ArrayList<Float[]>();
        ArrayList<Float[]> cluster3 = new ArrayList<Float[]>();
        ArrayList<Float[]> cluster4 = new ArrayList<Float[]>();
        ArrayList<Float[]> cluster5 = new ArrayList<Float[]>();

        Random r = new Random();
        int randomNumber1 = r.nextInt(rows - 1) + 1;
        for (int i=0;i<cols;i++)
        {
            centroid1[i] = records[randomNumber1][i];
        }
        int randomNumber2 = r.nextInt(randomNumber1 -1) +1;
        for (int i=0;i<cols;i++)
        {
            centroid2[i] = records[randomNumber2][i];
        }
        int randomNumber3 = r.nextInt(randomNumber2 - 1) +1;
        for (int i=0;i<cols;i++)
        {
            centroid3[i] = records[randomNumber3][i];
        }
        int randomNumber4 = r.nextInt(randomNumber3 -1) +1;
        for (int i=0;i<cols;i++)
        {
            centroid4[i] = records[randomNumber4][i];
        }
        int randomeNumber5 = r.nextInt(randomNumber4 - 1) +1;
        for (int i=0;i<cols;i++)
        {
            centroid5[i] = records[randomeNumber5][i];
        }

        while(true)
        {
            cluster1.clear();
            cluster2.clear();
            cluster3.clear();
            cluster4.clear();
            cluster5.clear();

            float distance1, distance2, distance3, distance4, distance5;
            for (int i = 0; i < rows; i++)
            {
                distance1 = findDistance(centroid1, records[i]);
                distance2 = findDistance(centroid2, records[i]);
                distance3 = findDistance(centroid3, records[i]);
                distance4 = findDistance(centroid4, records[i]);
                distance5 = findDistance(centroid5, records[i]);

                float leastDistance = findLeastDistance(distance1, distance2, distance3, distance4, distance5);
                if (leastDistance == distance1)
                    cluster1.add(records[i]);
                else if (leastDistance == distance2)
                    cluster2.add(records[i]);
                else if (leastDistance == distance3)
                    cluster3.add(records[i]);
                else if (leastDistance == distance4)
                    cluster4.add(records[i]);
                else if (leastDistance == distance5)
                    cluster5.add(records[i]);

            }

            newCentroid1 = calculateNewCentroid(cluster1);
            newCentroid2 = calculateNewCentroid(cluster2);
            newCentroid3 = calculateNewCentroid(cluster3);
            newCentroid4 = calculateNewCentroid(cluster4);
            newCentroid5 = calculateNewCentroid(cluster5);

            if (!(Arrays.equals(centroid1, newCentroid1) && Arrays.equals(centroid2, newCentroid2) && Arrays.equals(centroid3, newCentroid3) &&
                    Arrays.equals(centroid4, newCentroid4) && Arrays.equals(centroid5, newCentroid5)))
            {
                centroid1 = newCentroid1;
                centroid2 = newCentroid2;
                centroid3 = newCentroid3;
                centroid4 = newCentroid4;
                centroid5 = newCentroid5;
            } else
                break;

        }

        clusterwriteToFile(cluster1,cluster2,cluster3,cluster4,cluster5);

    }

    private void clusterwriteToFile(ArrayList<Float[]> cluster1, ArrayList<Float[]> cluster2, ArrayList<Float[]> cluster3, ArrayList<Float[]> cluster4, ArrayList<Float[]> cluster5)
    {
        String savepath1 = "Kmean5C1.txt";
        String savepath2 = "Kmean5C2.txt";
        String savepath3 = "Kmean5C3.txt";
        String savepath4 = "Kmean5C4.txt";
        String savepath5 = "Kmean5C5.txt";

        try
        {
            File file1 = new File(savepath1);
            File file2 = new File(savepath2);
            File file3 = new File(savepath3);
            File file4 = new File(savepath4);
            File file5 = new File(savepath5);
            file1.delete();
            file2.delete();
            file3.delete();
            file4.delete();
            file5.delete();
            FileWriter fw1 = new FileWriter(file1);
            FileWriter fw2 = new FileWriter(file2);
            FileWriter fw3 = new FileWriter(file3);
            FileWriter fw4 = new FileWriter(file4);
            FileWriter fw5 = new FileWriter(file5);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            BufferedWriter bw2 = new BufferedWriter(fw2);
            BufferedWriter bw3 = new BufferedWriter(fw3);
            BufferedWriter bw4 = new BufferedWriter(fw4);
            BufferedWriter bw5 = new BufferedWriter(fw5);
            for (Float[] content : cluster1)
            {
                bw1.write(Arrays.toString(content));
                bw1.write("\n");
            }
            bw1.close();
            for (Float[] content : cluster2)
            {
                bw2.write(Arrays.toString(content));
                bw2.write("\n");
            }
            bw2.close();
            for (Float[] content : cluster3)
            {
                bw3.write(Arrays.toString(content));
                bw3.write("\n");
            }
            bw3.close();
            for (Float[] content : cluster4)
            {
                bw4.write(Arrays.toString(content));
                bw4.write("\n");
            }
            bw4.close();
            for (Float[] content : cluster5)
            {
                bw5.write(Arrays.toString(content));
                bw5.write("\n");
            }
            bw5.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private Float[] calculateNewCentroid(ArrayList<Float[]> cluster)
    {
        Float[] centroid = new Float[cols];
        for (int i=0; i<cols; i++)
        {
            float sum=0;
            for (int j=0;j<cluster.size();j++)
            {
                sum += cluster.get(j)[i];
            }
            centroid[i] = sum/cluster.size();
        }

        return centroid;
    }

    private Float[][] normalizeRecords(Float[][] records)
    {
        Float[][] normalizedRec = new Float[rows][cols];
        for (int i=0;i<rows;i++)
        {
            float magnitude = calculateMagnitude(records[i]);
            for (int j=0;j<cols;j++)
            {
                normalizedRec[i][j] = records[i][j]/magnitude;
            }
        }
        return normalizedRec;
    }

    private float findLeastDistance(float distance1, float distance2, float distance3, float distance4, float distance5)
    {
        float leastval = 0;
        if (distance1 < distance2 && distance1<distance3 && distance1<distance4 && distance1<distance5)
            leastval = distance1;
        else if (distance2 < distance1 && distance2<distance3 && distance2<distance4 && distance2<distance5)
            leastval = distance2;
        else if (distance3 < distance1 && distance3<distance2 && distance3<distance4 && distance3<distance5)
            leastval = distance3;
        else if (distance4 < distance1 && distance4<distance2 && distance4<distance3 && distance4<distance5)
            leastval = distance4;
        else if (distance5 < distance1 && distance5<distance2 && distance5<distance4 && distance5<distance4)
            leastval = distance5;

        return leastval;
    }

    private float findDistance(Float[] centroid1, Float[] record)
    {
        float distanceResult = 0;
        for (int i=0;i<centroid1.length;i++)
        {
            //System.out.println(record[i] + " , " + centroid1[i]);
            distanceResult += Math.pow((centroid1[i]) - ((record[i])),2);
        }
        return (float) Math.sqrt(distanceResult);
    }

    private float calculateMagnitude(Float[] rec)
    {
        float quantity =0;
        for (int i=0;i<cols;i++)
        {
            quantity += Math.pow(rec[i],2);
        }
        return (float) Math.sqrt(quantity);
    }

    private Float getColValABGame(String str)
    {
        Float val = null;
        for (Map.Entry entry : mapLastCol.entrySet())
        {
            if (str.equals(entry.getKey()))
                val = (Float) entry.getValue();
        }
        return val;
    }
}
