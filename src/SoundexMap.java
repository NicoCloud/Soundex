/**
 * A class that implements a TreeMap using a linked list
 *      to resolve collisions.
 *
 * @author Nicole Weber
 * @version 5/1/2020
 */
import java.util.*;
public class SoundexMap<K, V>
{
    //TreeMap to hold soundex keys and collections of names
    private SortedMap<K, LinkedList<V>> soundex = new TreeMap<K,LinkedList<V>>();

    /**
     * Adds a new entry to soundex
     * @param key for the new entry
     * @param value for the key
     * @return null if the new entry was added or the value if it was added to a list
     */
    public V add(K key, V value){
        if(soundex.containsKey(key)){
            (soundex.get(key)).add(value);
            return value;
        }
        else{
            LinkedList temp = new LinkedList();
            temp.add(value);//Add value to linkedList associated with the key
            soundex.put(key,temp);//Add to the TreeMap
            return null;
        }
    }
    /**
     * Gets values associated with the key
     * @param key of an entry or entries
     * @return linked list if the key is present else return null
     */
    public LinkedList<V> getValues(K key){
        if(soundex.containsKey(key)){
            return soundex.get(key);
        }
        else{
            return null;
        }
    }
    /**
     * Displays values assosciated with a key
     * @param key, the key that will be displayed
     * @return presented, whether or not the soundex entry was presented
     */
    public boolean display(K key){
        boolean presented;
        if(soundex.containsKey(key)){
            LinkedList values = this.getValues(key);
            Collections.sort(values);
            if(values != null){
                Iterator i = values.listIterator(0);
                int index = 0;
                System.out.println("\tSoundex Key:\t\tNames:\n");
                System.out.println("\t" + key + "\t\t------------------------");
                while(i.hasNext()){
                    System.out.println("\t\t\t\t" + values.get(index));
                    index++;
                    i.next();
                }
            }
            presented = true;
        }
        else{
            System.out.println("This is not included in the soundex");
            presented = false;
        }
        System.out.println("\n");
        return presented;
    }
    /**
     * A method to find the start point of nearby entries within 5 places
     * @param key, the key to find the start point for.
     * @return startKey, the key that is the start of nearby entries
     */
    public K findNeighbors(K key){
        K startKey;
        K currentKey;
        int k = 0;
        Set keys = soundex.keySet();
        Iterator i = keys.iterator();//Iterator to find key
        Iterator s = keys.iterator();//Iterator to find start point
        startKey = (K)i.next();
        s.next();
        currentKey = startKey;
        while((!key.equals(currentKey)) && i.hasNext()){
            k++;
            if(k <= 5){
                currentKey = (K)i.next();
            }
            else{
                currentKey = (K)i.next();
                startKey = (K)s.next();
            }
        }
        return startKey;
    }
    /**
     * Display the 10 keys closest to a key if possible.
     * @param key, key whose neighbors you're looking for
     */
    public void displayNeighbors(K key){
        Set keys = soundex.keySet();
        Iterator i = keys.iterator();
        K start = findNeighbors(key);
        K current = (K)i.next();
        int keysPrinted = 0;
        System.out.println("Here are some neighboring soundex entries: ");
        try{Thread.sleep(2000);}
        catch(InterruptedException ex){
            System.out.println(ex);
        }
        while(i.hasNext() && (!current.equals(start))){
            current = (K)i.next();
        }
        while((!start.equals(key)) && i.hasNext()){
            display(start);
            start = (K)i.next();
        }
        while(i.hasNext() && keysPrinted < 5){
            current = (K)i.next();
            display(current);
            keysPrinted++;
        }
    }
    /**
     * Displays all keys and values in the soundex using an iterator.
     */
    public void displayAll(){
        Set keys = soundex.keySet();
        Iterator i = keys.iterator();
        while(i.hasNext()){
            K key = (K)i.next();
            display(key);
        }
    }
}
