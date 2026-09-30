
import java.util.*;
import java.io.*;

public class Functions {

    //
    public static void loadData(Map<Integer, ArrayList<String>> TVList){
        String fileName = "tv.csv";
        
        //read the file & load the map
        try{
            Scanner inFile = new Scanner(new File(fileName));
            
            while (inFile.hasNext()){
                String inputRecord = inFile.nextLine();
                try{
                //set up data
                
                String input[] = inputRecord.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");// written by Claude 

                Integer movieDuration = Integer.parseInt(input[0]);
                String movieName = input[1].replace("\"", "");

                if(!TVList.containsKey(movieDuration)){
                    TVList.put(movieDuration,new ArrayList<String>());
                }
                
                TVList.get(movieDuration).add(movieName);

                //add to map
                }
                catch (Exception e){
                    System.out.println("Error in input record");
                }
            }
            inFile.close();
        }
        catch (Exception e){
            System.out.println("Error in input record");
        }
    }

    public static String getMenuItem(Scanner input){
        String choice = " ";
        System.out.println("\nACTIONS FOR TVSHOW MAP");
        System.out.println("A: Add a Show ");
        System.out.println("D: Delete a Show ");
        System.out.println("K: Print All Keys (Durations) to Report");
        System.out.println("P: Print Map Listing to Report ");
        System.out.println("S: Print Specific Key (Duration) Listing to Report ");
        System.out.println("Q: Quit ");
        System.out.print("Please enter your choice: ");
        choice = input.nextLine().toUpperCase().trim();

        while (!( choice.equals("A") || choice.equals("D") ||
                  choice.equals("K") || choice.equals("P") ||
                  choice.equals("S") || choice.equals("Q"))){
            System.out.print("You entered an invalid value. Please enter a valid choice: ");
            choice = input.nextLine().toUpperCase().trim();
        }
        System.out.println();
        return choice;
    }

    //PRE:TVList is a valid map; input is an open Scanner; out is an open PrintWriter
    //POST:the show is added under the entered duration (a new list is created if the key did not exist); a message is written to both the screen and the report file
    public static void addShow(Map<Integer, ArrayList<String>> TVList, Scanner input, PrintWriter out){
        
        int duration = 0;

        boolean valid = false;

        while(!valid){
            System.out.print("Enter the duration:");
            try{
                duration = Integer.parseInt(input.nextLine().trim());
                valid = true;
            }
            catch(NumberFormatException e){
                System.out.println("Invalid duration.Please enter a whole number.");
            }
        }

        System.out.print("Enter the name of the show:");
        String showName = input.nextLine();

        if(!TVList.containsKey(duration)){
            TVList.put(duration, new ArrayList<String>());
        }
        TVList.get(duration).add(showName);

        String message = "A: This new show was added to the map: " + showName + " with the duration of " + duration + " years.";
        System.out.println(message);
        out.println(message);
    }

    //PRE:TVList is a valid map; input is an open Scanner; out is an open PrintWriter
    //POST:the first show with the entered name is removed from the map; a success or
    //"not in the map" message is written to both the screen and the report file
    public static void deleteShow(Map<Integer, ArrayList<String>> TVList, Scanner input, PrintWriter out){
        System.out.print("Enter the name of the show to delete: ");
        String showName = input.nextLine();

        boolean deleted = false;

        for(Integer key:TVList.keySet()){
            if(TVList.get(key).remove(showName)){
                deleted = true;
                break;
            }
        }

        String message;
        if(deleted){
            message = "R: Deleted item " + showName + " from the map";
        }
        else{
            message = "R: Unable to delete " + showName + ". Item is not in the map";
        }
        System.out.println(message);
        out.println(message);
    }

    //PRE:TVList is a valid map; out is an open PrintWriter
    //POST:every key (duration) is written to the report file;
    //a confirmation message is written to the screen
    public static void printKeys(Map<Integer, ArrayList<String>> TVList, PrintWriter out){
        for(Integer Key:TVList.keySet()){
            out.println(Key);
        }

        System.out.println("The Key listing was printed to the report file");
    }

    //PRE:TVList is a valid map out is an open PrintWriter
    //POST:every key and its list of shows is written to the report file
    //a confirmation message is written to the screen
    public static void printMap(Map<Integer, ArrayList<String>> TVList, PrintWriter out){
        for(Integer Key:TVList.keySet()){
            out.println(Key + ":" +   TVList.get(Key));
        }

        System.out.println("The map listing was printed to the report file");
    }

    //PRE:TVList is a valid map; input is an open Scanner; out is an open PrintWriter;
    //the user enters a whole number for the duration
    //POST:the shows with the entered duration are written to the report file;
    //if the duration is not in the map, only a "no shows" message is written to the screen
    public static void printKeyShows(Map<Integer, ArrayList<String>> TVList, Scanner input, PrintWriter out){
        
        System.out.println("Enter the duration to list:");
        int researchingDuration = Integer.parseInt(input.nextLine());

        if(TVList.containsKey(researchingDuration)){
            for(String tvName:TVList.get(researchingDuration)){
                out.println(tvName);
            }
            System.out.println("The shows with the duration of " + researchingDuration + " were written to the report file");
        }
        
        else{
            System.out.println("there are no tv on the duration");
        }
    }

}

/*
 * Sources / Citations:
 * - Claude (Anthropic, Claude Sonnet 5.5), used as an AI assistant for explanations.
 *   The following ideas were explained by Claude and used as a reference for this file:
 *     - split(",") and Integer.parseInt()
 *     - An idea for not splitting on the commas inside a show name
 *       (names wrapped in quotes, e.g. "Murder, She Wrote")
 *     - The change in loadData was written by Claude: split(",") was replaced with a
 *       regular expression that only splits on commas outside of quotes, and the
 *       quotation marks are then removed from the show name with replace("\"", "").
 *     -help with PRE and POST
 *     - addShow / deleteShow were written by Claude (Claude Sonnet 5.5): try/catch around
 *       Integer.parseInt to re-ask for a number
 */




