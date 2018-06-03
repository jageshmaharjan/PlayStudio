package Jugs.PlayStudio;

import java.util.Date;
import java.util.List;

public class Transaction
{
    public String userID;
    public Date EVTDATE;
    public Date INSTALL_DATE;
    public int NUM_TRANSACTIONS;
    public float AMOUNT_SPENT_USD;
    public float SPINS_MACHINE1;
    public float SPINS_MACHINE2;
    public double CI_MACHINE1;
    public double CI_MACHINE2;
    public double CO_MACHINE1;
    public double CO_MACHINE2;
    public boolean WENT_BUST;
    public long TIME_PLAYING;
    public String AB_GROUP;

    public Transaction(String userID, Date EVTDATE, Date INSTALL_DATE, int NUM_TRANSACTIONS, float AMOUNT_SPENT_USD, float SPINS_MACHINE1,
                       float SPINS_MACHINE2, long CI_MACHINE1, long CI_MACHINE2, long CO_MACHINE1, long CO_MACHINE2,
                       boolean WENT_BUST, long TIME_PLAYING, String AB_GROUP)
    {
        this.userID = userID;
        this.EVTDATE = EVTDATE;
        this.INSTALL_DATE = INSTALL_DATE;
        this.NUM_TRANSACTIONS = NUM_TRANSACTIONS;
        this.AMOUNT_SPENT_USD = AMOUNT_SPENT_USD;
        this.SPINS_MACHINE1 = SPINS_MACHINE1;
        this.SPINS_MACHINE2 = SPINS_MACHINE2;
        this.CI_MACHINE1 = CI_MACHINE1;
        this.CI_MACHINE2 = CI_MACHINE2;
        this.CO_MACHINE1 = CO_MACHINE1;
        this.CO_MACHINE2 = CO_MACHINE2;
        this.WENT_BUST = WENT_BUST;
        this.TIME_PLAYING = TIME_PLAYING;
        this.AB_GROUP = AB_GROUP;
    }

    public String getUserID() {
        return userID;
    }

    public Date getEVTDATE() {
        return EVTDATE;
    }

    public int getNUM_TRANSACTIONS() {
        return NUM_TRANSACTIONS;
    }

    public float getAMOUNT_SPENT_USD() {
        return AMOUNT_SPENT_USD;
    }

    public boolean isWENT_BUST() {
        return WENT_BUST;
    }

    public long getTIME_PLAYING() {
        return TIME_PLAYING;
    }

    public String getAB_GROUP() {
        return AB_GROUP;
    }

    public List<Transaction> getActiveMonetizer(Date date)
    {


        return null;
    }

}
