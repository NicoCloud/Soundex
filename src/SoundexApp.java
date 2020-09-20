/*
 * This app takes a file of names and produces a soundex utilizing the SoundexMap
 * class and the SoundexKeyMaker class. The soundex allows the user to search up
 * names, their soundex, and the closest soundex entries.
 *
 * @author Nicole Weber
 * @version 5/1/2020
 */
import java.util.*;
import java.io.*;
public class SoundexApp
{
    public static void main(String[] args){
        System.out.println("\f");

        //initialize the SoundexMap
        SoundexMap<String, String> ourSoundex =
                new SoundexMap<>();

        String name;
        File text = new File("src/nameTest.txt");//File to be read in
        //Build soundex
        try{
            Scanner file = new Scanner(text);//Set up scanner
            //Read in all the names
            while(file.hasNextLine()){
                name = file.next();
                file.nextLine();
                ourSoundex.add(SoundexKeyMaker.main(name), name);//Create the key and add to soundex
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

        Scanner keyboard = new Scanner(System.in);//Set up scanner for user input
        System.out.println("Welcome to the Soundex Name Lookup!");
        System.out.println("Press <?> for more info.");
        String answer = keyboard.next();
        String key;//null
        while(!answer.equals("q")){
            //press s to search
            if(answer.toLowerCase().equals("s")){
                System.out.println("If you're searching for a name with spaces \nplease " +
                        "type the name without them, as one word.");
                System.out.println("\nEnter a SURNAME: ");
                keyboard.nextLine();
                name = keyboard.nextLine();
                name = name.replace(" ","");
                key = SoundexKeyMaker.main(name);
                if(!ourSoundex.display(key)){
                    System.out.println("Key: "+ key);
                    System.out.println("Would you like to add this name to the Soundex?"+
                            "\nPress 'y' to confirm");
                    answer = keyboard.next();
                    if(answer.toLowerCase().equals("y")){
                        ourSoundex.add(SoundexKeyMaker.main(name), name);
                        System.out.println(name + " Added");
                    }
                }
                //display the 10 closest key entries
                else{
                    try{Thread.sleep(3000);}
                    catch(InterruptedException ex){
                        System.out.println(ex);
                    }
                    ourSoundex.displayNeighbors(key);
                }
            }
            //press d to display the complete soundex
            else if(answer.toLowerCase().equals("d")){
                System.out.println("Are you sure you want to print the complete Soundex?" +
                        "\nPress 'y' to confirm");
                answer = keyboard.next();
                if(answer.toLowerCase().equals("y")){
                    ourSoundex.displayAll();
                }
            }
            //press ? for options
            else if(answer.equals("?")){
                System.out.println("\nPress 's' to search the soundex."+
                        "\nPress 'd' to display the complete soundex \nPress 'q' to quit");
            }
            System.out.println("Press <?> for more info.");
            answer = keyboard.next();
        }
    }
}
