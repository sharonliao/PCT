import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by liaoxiaoyun on 2019-03-13.
 */
public class Intersection {
    List<Segment> verticalLines = new ArrayList<Segment>();
    List<Segment> horizontalLines = new ArrayList<Segment>();


     public int insertCount(){
        int count = 0;
        for(Segment vLine : verticalLines ){
            for(Segment hLine : horizontalLines){
                if(vLine.ifIntersection(hLine)){
                    ++count;
                }
            }
        }
        return count;
     }

     public static void main(String[] args){
         Intersection intersection = new Intersection();
         Scanner scanner = new Scanner(System.in);
         String line = scanner.nextLine();
         int vNum = Integer.parseInt(line.split(" ")[0]);
         int hNum = Integer.parseInt(line.split(" ")[1]);

         while(vNum > 0){
             scanner = new Scanner(System.in);
             line = scanner.nextLine();
             String[] numbers = line.split(" ");
             Segment segm = new Segment(Integer.parseInt(numbers[0]),Integer.parseInt(numbers[1]),Integer.parseInt(numbers[2]));
             intersection.verticalLines.add(segm);
             --vNum;
         }
         while(hNum > 0){
             scanner = new Scanner(System.in);
             line = scanner.nextLine();
             String[] numbers = line.split(" ");
             Segment segm = new Segment(Integer.parseInt(numbers[0]),Integer.parseInt(numbers[1]),Integer.parseInt(numbers[2]));
             intersection.horizontalLines.add(segm);
             --hNum;
         }

         int count = intersection.insertCount();
         System.out.print(count);

     }


}

class Segment{
    int a;
    int b;
    int c;

    Segment(int x, int y, int z){
        a = x;
        b = y;
        c = z;
    }
    boolean ifIntersection(Segment otherLine){
        boolean ifintersection = false;
        //check if a
        if( a >= otherLine.b && a <= otherLine.c && b <= otherLine.a && c >= otherLine.a ){
            ifintersection = true;
        }
        return ifintersection;
    }
}