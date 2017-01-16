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


    public int getBase_Stat() {
        return Base_Stat;
    }

    public void setBase_Stat(int base_Stat) {
        Base_Stat = base_Stat;
    }
    public void increaseBase_Stat(int increase){
        Base_Stat += increase;
        if(Gen == Utility.STATS.TOTAL)
        TOTAL.increaseBase_Stat(increase);
    }
    public int getIV() {
        return IV;
    }

    public void setIV(int IV) {
        this.IV = IV;
    }
    public void increaseIV(int increase){
        IV += increase;
        if(Gen == Utility.STATS.TOTAL)
        TOTAL.increaseIV(increase);
    }
    public int getEV() {
        return EV;
    }

    public void setEV(int EV) {
        this.EV = EV;
    }
}
