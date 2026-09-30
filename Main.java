import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) {

        System.out.println("Welcome to Program 2: Maps");
        Map<Integer, ArrayList<String>> TVList = new HashMap<>();

        Scanner input = new Scanner(System.in);

        //open output file
        try{
            PrintWriter out = new PrintWriter("report.txt");
            
            //load data into map
            Functions.loadData( TVList);

            //sort the map
            Map<Integer, ArrayList<String>> sortedTVList = new TreeMap<>(TVList);

            String menuItem = Functions.getMenuItem(input);

            while (!menuItem.equals("Q")){
                //test for valid menu options & call appropriate functions
                if (menuItem.equals("A")) {
                    Functions.addShow(sortedTVList, input, out);
                }
                else if (menuItem.equals("D")) {
                    Functions.deleteShow(sortedTVList, input, out);
                }
                else if (menuItem.equals("K")) {
                    Functions.printKeys(sortedTVList, out);
                }
                else if (menuItem.equals("P")) {
                    Functions.printMap(sortedTVList, out);
                }
                else if (menuItem.equals("S")) {
                    Functions.printKeyShows(sortedTVList, input, out);
                }

                menuItem = Functions.getMenuItem(input);
            }

            //close files
            input.close();
            out.close();

        }
        catch (Exception e){
            System.out.println("Error in input record");
            return;
        }
    }

}
