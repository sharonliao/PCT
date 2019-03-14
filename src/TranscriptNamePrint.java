import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by liaoxiaoyun on 2019-03-13.
 */
public class TranscriptNamePrint {
    String names = "";
    List<Character> vowelLits = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');


    public boolean isSurName(String name) {

        boolean issurname = false;
        //get last char
        char lastChar = name.charAt(name.length() - 1);
        if (vowelLits.contains(lastChar)) {
            issurname = true;
        }
        return issurname;
    }

    public String changeOrder(String names) {
        String newOrder = "";
        String[] fullName = names.substring(0, names.length() - 1).split(" ");
        if (fullName.length == 3) {
            newOrder = fullName[2] + " " + fullName[0] + " " + fullName[1] + "\n";
        } else if (fullName.length == 2) {
            if (isSurName(fullName[1])) { //  if it is surname,change order
                newOrder = fullName[1] + " " + fullName[0] + "\n";
            } else { //middle name ,keep order
                newOrder = names;
            }
        } else if (fullName.length == 1) {
            newOrder = names;
        }
        return newOrder;
    }

    public static void main(String[] args) {
        TranscriptNamePrint namePtrint = new TranscriptNamePrint();
//       List<String> nameList = Arrays.asList("Prithviraj Dajisaheb Chavan\n",
//               "Prithviraj D. Chavan\n","Barack Obama\n","Michael Jackson\n","sharon\n","Sharon L\n","kevin Oka\n");
//       String newOrder = "";
//
//
//       Scanner sc_in = new Scanner(System.in);
//       String input= sc_in.nextLine();
//
//       for(String names : nameList){
//           newOrder = namePtrint.changeOrder(names);
//           System.out.print(names +  newOrder + "\n" );
//       }
//
//        Scanner sc = new Scanner(System.in);
//        String names = sc.nextLine();
//        System.out.print(names);
//        System.out.print(namePtrint.changeOrder(names));

        try {
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            String names = br.readLine();
            System.out.print(namePtrint.changeOrder(names+"\n"));
        }catch (Exception e){
        }




    }

}

