/**
 * Created by Taylor on 1/15/2017.
 */
public class Base {
    private Utility.STATS Gen;
    private int Base_Stat;
    private int IV;
    private int EV;
    public Base TOTAL;

    public Base(){
        Gen = Utility.STATS.TOTAL;
        Base_Stat = 0;
        IV = 0;
        EV = 0;
    }
    public Base(Utility.STATS type, int Base, Base total){
        TOTAL = total;
        Base_Stat = Base;
        Gen = type;
        EV = 0;
        IV = (int)Math.random()*32;
    }



}
