import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by taylor hudson on 1/13/2017.
 */
public class Utility {

    public enum ExperienceGroup {ERRATIC, FAST, MEDIUMFAST, MEDIUMSLOW, SLOW, FLUCTUATING}

    public enum CATEGORY{PHYSICAL, SPECIAL, STATUS}

    public enum CONTEST{COOL, BEAUTIFUL, CUTE, CLEVER, TOUGH}

    public enum STATS{HP, ATTACK, DEFENSE, SP_ATTACK, SP_DEFENSE, SPEED, TOTAL, AVG}

    public enum TYPE{NORMAL, FIRE, WATER, GRASS, ELECTRIC, ICE, FIGHTING, POISON, HUDSON, GROUND, FLYING, PSYCHIC, BUG, ROCK, GHOST, DRAGON, DARK, STEEL, FAIRY, GLITCH, QMARK, SHADOW, TYPELESS, NONE }

    public static ArrayList<Long> Erratic = new ArrayList<Long>(){
        {
            add(0L); // Erratic.get(0);
            for(int i = 2; i <= 100; i++){
                Long xp;
                if(i <= 50){
                    xp = (long)(Math.pow(i,3)*(100-i)/50);
                    add(xp);
                }
                else if(i <= 68){
                    xp = (long)(Math.pow(i,3)*(150-i)/100);
                    add(xp);
                }
                else if(i <= 98){
                    xp = (long)(Math.pow(i,3)*Math.floor((1911-(10*i))/3)/100);
                    add(xp);
                }
                else {
                    xp = (long)(Math.pow(i,3)*Math.floor((1911-(10*i))/3)/100);
                    add(xp);
                }
            }
        }
    };

    public static ArrayList<Long> Fast = new ArrayList<Long>() {
        {
            add(0L); // *.get(0);

            for (int i = 2; i <= 100; i++) {
                Long xp;
                xp = (long)((int)(4 * Math.pow(i,3))/5);
                add(xp);
            }
        }
    };

    public static ArrayList<Long> MediumFast = new ArrayList<Long>() {
        {
            add(0L); // *.get(0);

            for (int i = 2; i <= 100; i++) {
                Long xp;
                xp = (long)((Math.pow(i,3)));
                add(xp);
            }
        }
    };

    public static ArrayList<Long> MediumSlow = new ArrayList<Long>() {
        {
            add(0L); // *.get(0);

            for (int i = 2; i <= 100; i++) {
                Long xp;
                xp = (long)((int)((6*Math.pow(i,3))/5 - (15*Math.pow(i,2)) + (100*i) - 140));
                add(xp);
            }
        }
    };

    public static ArrayList<Long> Slow = new ArrayList<Long>() {
        {
            add(0L); // *.get(0);

            for (int i = 2; i <= 100; i++) {
                Long xp;
                xp = (long)((int)(5 * Math.pow(i,3))/4);
                add(xp);
            }
        }
    };

    public static ArrayList<Long> Fluctuating = new ArrayList<Long>(){
        {
            add(0L); // *.get(0);

            for(int i = 2; i <= 100; i++){
                Long xp;
                if(i <= 15){
                    xp = (long)(Math.pow(i,3)*(Math.floor((i+1)/3)+24)/50);
                    add(xp);
                }
                else if(i <= 36){
                    xp = (long)(Math.pow(i,3)*(i+14)/50);
                    add(xp);
                }
                else {
                    xp = (long)(Math.pow(i,3)*(((Math.floor(i/2))+32)/50));
                    add(xp);
                }
            }
        }
    };

    public static Long TNL(int currentLevel, ExperienceGroup classification){
        if(classification == ExperienceGroup.ERRATIC) {
            return Erratic.get(currentLevel);
        }
        else if(classification == ExperienceGroup.FAST){
            return Fast.get(currentLevel);
        }
        else if(classification == ExperienceGroup.MEDIUMFAST){
            return MediumFast.get(currentLevel);
        }
        else if(classification == ExperienceGroup.MEDIUMSLOW) {
            return MediumSlow.get(currentLevel);
        }
        else if(classification == ExperienceGroup.SLOW){
            return Slow.get(currentLevel);
        }
        if(classification == ExperienceGroup.FLUCTUATING){}

        return Fluctuating.get(currentLevel);
    }

    public static HashMap<Integer, Move> moveset = new HashMap<Integer, Move>(){
        {
               /*
               access "resource/Database/movelist.csv"
               each line pass through Move
                */
               try{
                   BufferedReader move = new BufferedReader(new FileReader("rescource/Database/movelist.csv"));
                   String line;
                   while((line = move.readLine())!=null){
                       put(Integer.valueOf(line.substring(0, line.indexOf(','))), new Move(line.split(",")));
                   }
                   move.close();
               }catch(Exception e){

               }
        }
    };

    public static HashMap<Integer, Creature> baseInfo = new HashMap<Integer, Creature>(){
        {
            try{
                BufferedReader move = new BufferedReader(new FileReader("rescource/Database/Pokemon.csv"));
                String line;
                while((line = move.readLine())!=null){
                    put(Integer.valueOf(line.substring(0, line.indexOf(','))), new Creature(line.split(",")));
                }
                move.close();
            }catch(Exception e){

            }
        }
    };

    public static float EFFECTIVE(TYPE Move, TYPE DefMain, TYPE DefSub){
        return chart(Move, DefMain) * chart(Move, DefSub);
    }

    public static float chart(TYPE Atk, TYPE Def){

        switch(Atk){
            case NORMAL:
                if(Def == TYPE.GHOST)
                    return 0f;
                else if (Def == TYPE.ROCK || Def == TYPE.ROCK)
                    return .5f;
                break;
            case FIGHTING:
                if(Def == TYPE.NORMAL || Def == TYPE.ROCK || Def == TYPE.STEEL || Def == TYPE.ICE || Def == TYPE.DARK){
                    return 2f;
                }
                else if(Def == TYPE.FLYING || Def == TYPE.POISON || Def == TYPE.BUG || Def == TYPE.PSYCHIC || Def == TYPE.FAIRY){
                    return .5f;
                }
                else if(Def == TYPE.GHOST)
                    return 0f;
                break;
            case FLYING:
                if(Def == TYPE.FIGHTING || Def == TYPE.BUG || Def == TYPE.GRASS)
                    return 2f;
                if(Def == TYPE.ROCK || Def == TYPE.STEEL || Def == TYPE.ELECTRIC)
                    return .5f;
                break;
            case POISON:
                if(Def == TYPE.POISON || Def == TYPE.GROUND || Def == TYPE.ROCK || Def == TYPE.GHOST)
                    return .5f;
                if(Def == TYPE.STEEL)
                    return 0f;
                if(Def == TYPE.GRASS || Def == TYPE.FAIRY)
                    return 2f;
                break;
            case GROUND:
                if(Def == TYPE.FLYING)
                    return 0f;
                if(Def == TYPE.POISON || Def == TYPE.ROCK || Def == TYPE.STEEL || Def == TYPE.FIRE || Def == TYPE.ELECTRIC)
                    return 2f;
                if(Def == TYPE.BUG || Def == TYPE.GRASS)
                    return .5f;
                break;
            case ROCK:
                if(Def == TYPE.FIGHTING || Def == TYPE.GROUND || Def == TYPE.STEEL)
                    return .5f;
                if(Def == TYPE.FLYING || Def == TYPE.BUG || Def == TYPE.ICE || Def == TYPE.FIRE)
                    return 2f;
                break;
            case BUG:
                if(Def == TYPE.FIGHTING || Def == TYPE.FLYING || Def == TYPE.POISON || Def == TYPE.GHOST || Def == TYPE.STEEL || Def == TYPE.FIRE || Def == TYPE.FAIRY)
                    return .5f;
                if(Def == TYPE.GRASS|| Def == TYPE.PSYCHIC || Def == TYPE.DARK)
                    return 2f;
                break;
            case GHOST:
                if(Def == TYPE.NORMAL)
                    return 0f;
                if(Def == TYPE.GHOST || Def == TYPE.PSYCHIC)
                    return 2f;
                if(Def == TYPE.DARK)
                    return .5f;
                break;
            case STEEL:
                if(Def == TYPE.ROCK || Def == TYPE.ICE || Def == TYPE.FAIRY)
                    return 2f;
                if(Def == TYPE.STEEL || Def == TYPE.FIRE || Def == TYPE.WATER || Def == TYPE.ELECTRIC)
                    return .5f;
                break;
            case FIRE:
                if(Def == TYPE.BUG || Def == TYPE.STEEL || Def == TYPE.GRASS)
                    return 2f;
                if(Def == TYPE.ROCK || Def == TYPE.FIRE || Def == TYPE.WATER || Def == TYPE.DRAGON)
                    return .5f;
                break;
            case WATER:
                if(Def == TYPE.GROUND || Def == TYPE.ROCK || Def == TYPE.FIRE)
                    return 2f;
                if(Def == TYPE.WATER || Def == TYPE.GRASS || Def == TYPE.DRAGON)
                    return .5f;
                break;
            case GRASS:
                if(Def == TYPE.FLYING || Def == TYPE.POISON || Def == TYPE.BUG || Def == TYPE.STEEL || Def == TYPE.FIRE || Def == TYPE.GRASS || Def == TYPE.DRAGON)
                    return .5f;
                if(Def == TYPE.GROUND || Def == TYPE.ROCK || Def == TYPE.WATER)
                    return 2f;
                break;
            case ELECTRIC:
                if(Def == TYPE.ROCK)
                    return 0f;
                if(Def == TYPE.FLYING || Def == TYPE.WATER)
                    return 2f;
                if(Def == TYPE.GRASS || Def == TYPE.ELECTRIC || Def == TYPE.DRAGON)
                    return .5f;
                break;
            case PSYCHIC:
                if(Def == TYPE.FIGHTING || Def == TYPE.POISON)
                    return 2f;
                if(Def == TYPE.STEEL || Def == TYPE.PSYCHIC)
                    return .5f;
                if(Def == TYPE.DARK)
                    return 0f;
                break;
            case ICE:
                if(Def == TYPE.FLYING || Def == TYPE.GROUND || Def == TYPE.GRASS || Def == TYPE.DRAGON)
                    return 2f;
                if(Def == TYPE.STEEL || Def == TYPE.FIRE || Def == TYPE.WATER || Def == TYPE.ICE)
                    return .5f;
                break;
            case DRAGON:
                if(Def == TYPE.DRAGON)
                    return 2f;
                if(Def == TYPE.STEEL)
                    return .5f;
                if(Def == TYPE.FAIRY)
                    return 0f;
                break;
            case DARK:
                if(Def == TYPE.FIGHTING || Def == TYPE.DARK || Def == TYPE.FAIRY)
                    return .5f;
                if(Def == TYPE.GHOST || Def == TYPE.PSYCHIC)
                    return 2f;
                break;
            case HUDSON:
                return 2f;
            case FAIRY:
                if(Def == TYPE.FIGHTING || Def == TYPE.DRAGON || Def == TYPE.DARK)
                    return 2f;
                if(Def == TYPE.POISON || Def == TYPE.STEEL || Def == TYPE.FIRE)
                    return .5f;
                break;
            case SHADOW:
                if(Def == TYPE.SHADOW)
                    return .5f;
                return 2f;
        }
        if(Def == TYPE.HUDSON)
            return 0f;
        return 1f;

    }

}
