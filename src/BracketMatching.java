import java.util.*;

/**
 * Created by liaoxiaoyun on 2019-03-13.
 */
public class BracketMatching {
    List<String> stringList = new ArrayList<>();
    Set<Character> openBrackets ;
    Collection<Character> closeBrackets;
    HashMap<Character,Character> bracketsMap = new HashMap<>();

    BracketMatching(){
        bracketsMap.put('(',')');
        bracketsMap.put('[',']');
        bracketsMap.put('{','}');
        openBrackets = bracketsMap.keySet();
        closeBrackets = bracketsMap.values();
    }

    //stack

    public boolean isMachting(String str){
        Stack<Character> stack = new Stack<Character>();
        boolean result = true;
        char[] charStr = str.toCharArray();
        for(char c : charStr){
            if(openBrackets.contains(c)){
                stack.push(c);
            } else if(closeBrackets.contains(c)){
                if( ! bracketsMap.get(stack.pop()).equals(c)){
                    return false;
                }

            }
        }
        if(stack.size() != 0){
            result = false;
        }
        return result;
    }

    public static void main(String[] args){
        BracketMatching bracketMatching = new BracketMatching();
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());

        while (num > 0){
            scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            bracketMatching.stringList.add(str);
            -- num;
        }
        for(String str : bracketMatching.stringList){
            System.out.print(bracketMatching.isMachting(str)+"\n");
        }


    }

}
