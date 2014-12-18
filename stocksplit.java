/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocksplit;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Math.abs;
import java.util.Scanner;


public class StockSplit {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        //gets and processes first line   
        Scanner scan = new Scanner (new File ("stockdata.txt"));
        String firstLine = scan.nextLine();
        String [] firstline = firstLine.split("\\s*\\;\\s*");
        String companyName = "";
        int numSplits = 0;
       
            
        while (scan.hasNextLine()){
            //gets and processes next line
            String secondLine = scan.nextLine();
            String [] secondline = secondLine.split("\\s*\\;\\s*");
            //check for stock company change
            if(!firstline[0].equals(companyName)){
                companyName = firstline[0];
                if(numSplits != 0){
                    System.out.println("splits: " + numSplits);
                    numSplits = 0;
                } 
                System.out.println("Processing " + firstline[0] + "...");
            }
            
            //catches edge case, when company name
            if(!secondline[0].equals(companyName)){
                firstline = secondline;
            }
            
            double closing_OpeningRatio = Double.parseDouble(secondline[5])/Double.parseDouble(firstline[2]);
            
            //looking for 2:1 splits
            if(abs(closing_OpeningRatio - 2.0) < 0.10){
                numSplits++;
                System.out.println("2:1 split on " + secondline[1] + " " + secondline[5] + " --> " + firstline[2]);
            }
            //looking for 3:1 splits
            if(abs(closing_OpeningRatio - 3.0) < 0.15){
                numSplits++;
                System.out.println("3:1 split on " + secondline[1] + " " + secondline[5] + " --> " + firstline[2]);
            }
            //looking for 3:2 splits
            if(abs(closing_OpeningRatio - 1.5) < 0.075){
                numSplits++;
                System.out.println("3:2 split on " + secondline[1] + " " + secondline[5] + " --> " + firstline[2]);
            }
            firstline = secondline;
                   
        }
       System.out.println("splits: " + numSplits);
        

    }
    
}
