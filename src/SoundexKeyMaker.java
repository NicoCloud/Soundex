
/**
 * A class to translate Strings into Soundex keys
 *
 * @author Nicole Weber
 * @version 5/2/2020
 */
public class SoundexKeyMaker
{
    /**
     * Takes a String and returns a soundex key based on the string
     * @param name, the name that will be made into a soundex key
     * @return key, the key produced from the string provided
     */
    public static String main(String name){
        String key = "";
        char firstChar = name.toUpperCase().charAt(0);
        name = name.toLowerCase();
        int lastChar = letterCheck(name.charAt(0));

        key += letterCheck(name.charAt(0));
        for(int i = 1; i < name.length(); i++){
            int currentChar = letterCheck(name.charAt(i));
            if(lastChar != currentChar){
                key += currentChar;
                lastChar = currentChar;
            }
        }
        key = firstChar + key.substring(1,key.length());
        key = key.replaceAll("7", "");


        if(key.length() > 4){
            key = key.substring(0,4);
        }
        else{
            while(key.length() < 4){
                key += 0;
            }
        }
        return key;
    }
    /**
     * Takes a character and returns the number to change it to soundex
     * @param c, character to be translated
     * @return number the character matchs to in soundex translation
     */
    public static int letterCheck(char c){
        if(c == 'l'){
            return 4;
        }
        else if(c == 'r'){
            return 6;
        }
        else if(c == 'm' || c == 'n'){
            return 5;
        }
        else if(c == 'd' || c == 't'){
            return 3;
        }
        else if(c == 'b' || c == 'p' || c == 'f' || c == 'v'){
            return 1;
        }
        else if(c == 'c' || c == 's' || c == 'k' || c == 'g' || c == 'j' ||
                c == 'q' || c == 'x' || c == 'z'){
            return 2;
        }
        else{
            return 7;
        }
    }
}
