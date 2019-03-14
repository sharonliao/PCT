import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.SyncFailedException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by liaoxiaoyun on 2019-03-13.
 */
public class UniqueWordCOunt {
    public static int countWord(String inputStr){
        int count = 0;
        String[] words = inputStr.toLowerCase().split(" "); //case to lower letter
        HashMap<String,Integer> wordsMap = new HashMap<>();
        for(String word:words ){
            if(wordsMap.containsKey(word)){
                wordsMap.put(word,wordsMap.get(word)+1);
            } else {
                wordsMap.put(word,1);
            }
        }
        count = wordsMap.size();

        //hashset is also good to use
//        HashSet<String> wordsSet = new HashSet<String>();
//        for(String word:words ){
//            wordsSet.add(word);
//        }
//        System.out.print("set count:" + wordsSet.size());

        return count;
    }

    public static void main(String[] args){
        try{
            Scanner sc = new Scanner(System.in);
            String wordStr = sc.nextLine();

            System.out.print(countWord(wordStr)+"\n");
        }catch (Exception e){

        }

        //read in from txt

//        try{
//
//            BufferedReader br = new BufferedReader(new FileReader("UniqueWordCOunt.txt"));
//            String wordStr = br.readLine();
//            System.out.print(countWord(wordStr)+"\n");
//        }catch (Exception e){
//
//        }



    }
}
