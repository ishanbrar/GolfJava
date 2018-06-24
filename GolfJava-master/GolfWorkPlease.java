/*
 *The GolfWithNewMethods class takes data from
 * user input about their round, and runs basic data
 *analytics on it and prints back information.
 *@author Ishan Brar
 *Date 4/10/18
 */


import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.text.DecimalFormat;
import java.util.Arrays;

public class GolfWorkPlease

{
    private static int score; //user total score for the round (ex: 75, 80)
    private static int par; // par for the course (71,72, or 73)
    private static int toPar; //the player's score to par (ex: +4, -2)
    private static String course = " "; // the string of the name of the course
    private static String playerName = " "; //name of the Golfer as a string
    private static String date = " "; //date the round of golf was played as a string
    private static int holePar; //the par of the individual hole (ex: 3,4,or 5)
    //private static int[] par3 = new int[18];
    //private static int[] par4 = new int[18];
    //private static int[] par5 = new int[18];
    private static List<Integer>  par5s = new ArrayList<Integer>();
    private static List<Integer>  par4s = new ArrayList<Integer>();
    private static List<Integer>  par3s = new ArrayList<Integer>();
    private static int[] roundScores = new int[19];
    private static int[] holePars = new int[18];
    public static void main(String[] args)
    {
        //declare scanner to read input info
        //Print statements and responses assigned to instance variables
        getInfo();

        //prints and returns the total score for the round
        scores(par,score);
        

        editPars(holePars);
        editScores(roundScores);
        getRoundSummary();
        getParScoring();
        getScorecard();
        printAll(holePars, roundScores);
    }
    public static int scores(int parx, int scorex)
    {
        int total = scorex - parx;
        toPar = total;
        if (total > -1)
        {
            System.out.println("Your total was +" + total);
        }
        else
        {
            System.out.println("Your total was " + total);
        }
        return total;
    }



    public static int[] editScores(int[] array)
    {
        Scanner scan2 = new Scanner(System.in);
        int holeScore;
        for(int index = 0; index < 18; index++)
        {
            System.out.println("What was your score on hole #" + (index + 1) + "?");
            holeScore = scan2.nextInt();
            if(holeScore > 9)
            {
                System.out.println("I hope this was a typo... re-enter score for hole #" + (index + 1));
                holeScore = scan2.nextInt();
            }
            else if(holeScore == 1)
            {
                System.out.println("Wow! Great hole in one!");
            }
            else if(holeScore < 1)
            {
                System.out.println("As nice as this score would be, please re-enter score for hole #" + (index + 1));
                holeScore = scan2.nextInt();

            }
            array[index] = holeScore;
        }
        return array;
    }
    public static void getInfo()
    {
    	Scanner scan = new Scanner(System.in);
    	System.out.println("What is your name?");
        playerName = scan.nextLine();
        System.out.println("Where did you play?");
        course = scan.nextLine();
        System.out.println("When did you play?");
        date = scan.nextLine();
        System.out.println("What did you shoot?");
        score = scan.nextInt();
        System.out.println("What was the par for the course?");
        par = scan.nextInt();
    }
    
    public static void getParScoring()
    {
    	System.out.println("You had a total score of " + getPar5() + " on Par 5s.");
        System.out.printf("Your Par 5 scoring average was %2.2f \n ", scoringAverage(5));
        System.out.println("You had a total score of " + getPar4() + " on Par 4s.");
        System.out.printf("Your Par 4 scoring average was %2.2f \n ", scoringAverage(4));
        System.out.println("You had a total score of " + getPar3() + " on Par 3s.");
        System.out.printf("Your Par 3 scoring average was %2.2f \n ", scoringAverage(3));
        System.out.println("");
    }
    public static void getRoundSummary()
    {
    	System.out.println(Arrays.toString(roundScores));
        System.out.println("Your front 9 score at " + course + " was " + frontNine(roundScores));
        System.out.println("Your back 9 score at " + course + " was " + backNine(roundScores));
        if(score - par > 0) {
            System.out.println( playerName + ", your total score at " + course + " on " + date + " was " + score + ", " + (Math.abs(score - par)) + " over par.");
            System.out.println("Practice more!");
        }
        else if (score - par == 0){
            System.out.println( playerName + ", your total score at " + course + " on " + date + " was " + score + ", even par.");
            System.out.println("Great score!");
        }
        else if (score - par <  0){
            System.out.println( playerName + ", your total score at " + course + " on " + date + " was "  +   score + ", " + (Math.abs(score - par)) + " under par!");
            System.out.println("Amazing round!");
    }
    }
    
    public static void getScorecard()
    {
    	System.out.println("" + playerName + " , here is your scorecard: ");
    	System.out.println(date + " at " + course + ":");
        System.out.println("HOLE: | " +  printHole() + " ");
        System.out.println("PAR: |  " +  printPars(holePars) + " ");
        System.out.println("--------------------------------------------------------");
        System.out.println("SCORE: |"  + printScores(roundScores) + " ");
        System.out.println("");
        System.out.println(" HOLE     PAR      SCORE     TO PAR");
    }

    public static int frontNine(int[] array)
    {


        int frontNineScore = 0;
        for(int index = 0; index < 9; index++)
        {
            frontNineScore += array[index];

        }
        return frontNineScore;
    }
    public static int backNine(int[] array)
    {


        int backNineScore = 0;
        for(int index = 9; index < 18; index++)
        {
            backNineScore += array[index];

        }
        return backNineScore;
    }


    public static int[] editPars(int[] array)
    {
        Scanner scan2 = new Scanner(System.in);

        for (int index = 0; index < 18; index++)
        {
            System.out.println("What was the par for hole #" + (index + 1) + "? (Must be 3,4, or 5)");
            holePar = scan2.nextInt();
            if(holePar == 5)
            {
                par5s.add(index);
                array[index] = holePar;


            }

            else if(holePar == 4)
            {
                par4s.add(index);
                array[index] = holePar;


            }

            else if(holePar == 3)
            {
                par3s.add(index);
                array[index] = holePar;


            }

            if (holePar > 5 || holePar < 3)
            {
                System.out.println("Not a valid par, please re-enter par value for hole #" + (index + 1));
                holePar = scan2.nextInt();
                if(holePar == 5)
                    {
                    par5s.add(index + 1);
                        array[index] = holePar;


                    }

                else if(holePar == 4)
                    {
                        par4s.add(index + 1);
                        array[index] = holePar;


                    }

                else if(holePar == 3)
                    {
                        par3s.add(index + 1);
                        array[index] = holePar;


                    }


                        array[index] = holePar;
                    }



        }
        return array;
    }

    public static int getPar5()
    {
        int sum = 0;
        for(int count = 0; count < par5s.size();count++)
        {
            sum+= roundScores[par5s.get(count)];
        }
        return sum;
    }

    public static int getPar4()
    {
        int sum = 0;
        for(int count = 0; count < par4s.size();count++)
        {
            sum+= roundScores[par4s.get(count)];
        }
        return sum;
    }

    public static int getPar3()
    {
        int sum = 0;
        for(int count = 0; count < par3s.size();count++)
        {
            sum+= roundScores[par3s.get(count)];
        }
        return sum;
    }
    public static double scoringAverage(int par)
    {
        if(par == 3)
        {
           double p3 = ((double)getPar3() / (double)par3s.size());


            return p3;
        }

        else if(par == 4)
        {
            return ((double)getPar4() / (double)par4s.size());
        }

        else if(par == 5)
        {
            return  ((double)getPar5() / (double)par5s.size());
        }
        else{
            return 0;
        }
    }
    public static String printScores(int[] scores)
    {
        String x = " ";
        for(int index = 0; index < (scores.length) - 1; index++)
        {
            x += scores[index] + "  ";
        }
        return x;
    }

    public static String printPars(int[] pars)
    {
        String y = " ";
        for(int index = 0; index < (pars.length); index++)
        {
            y += pars[index] + "  ";
        }
        return y;
    }

    public static String printHole()
    {
        String z = " ";

        for(int index = 0;index < 18; index ++)
        {
            z += (index + 1) + "  ";
        }
        return z;
    }
    public static int sumPars(int[] pars)
    {
        int sum = 0;
        for(int index = 0; index < pars.length; index ++)
        {
            sum+= pars[index];
        }
        return sum;
    }

    public static int sumScores(int[] scores)
    {
        int sum = 0;
        for (int index = 0; index < scores.length; index++)
        {
            sum += scores[index];
        }
        return sum;
    }

    public static int sumToPar(int[] pars, int[] scores)
    {
        int sum = 0;
        for (int index = 0; index < pars.length; index++)
        {
            sum += (scores[index] - pars[index]);
        }
        return sum;
    }

    /*public static int live(int[] pars, int[] scores)
    {
        int x = 0;
        int sum = 0;
        int live = 0;
        int[] sums = new int[18];
        for (int index = 0; index < pars.length; index++)
        {
            sum += (scores[index] - pars[index]);
            sums[index] = sum;

        }
        for(int index = 1; index < 18; index ++)


            *//*if(index == 0)
            {
                x = (sums[index]);
                return x;
            }
            else
            {*//*
                x = (sums[index] + sums[index - 1]);
                return x;


        }
*/


    public static void printAll(int[] pars, int[] scores)
    {
        for(int index = 0; index < 9; index ++){

            System.out.println( " " + (index + 1) + "       | " + pars[index] + "        | " + scores[index] + "       | " + (scores[index] - pars[index]) + "     " /*live(holePars,roundScores)*/);
        }
        for(int index = 9; index < 18; index ++){

            System.out.println( " " + (index + 1) + "      | " + pars[index] + "        | " + scores[index] + "       | " + (scores[index] - pars[index]));
        }
        System.out.println("_____________________________________________________");
        System.out.println("          " + sumPars(holePars) + "        " + sumScores(roundScores) + "         " + sumToPar(holePars, roundScores));
    }
}