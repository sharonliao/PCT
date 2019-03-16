import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by liaoxiaoyun on 2019-03-15.
 */
public class graph_deadLock {
    int vNum;
    int eNum;
    int matrix[][];
    int visited[];
    boolean isDAG = true;//有向无环图


    public void create_graph(ArrayList<String> stringList ){
        ArrayList<Integer> nodeList = new ArrayList<Integer>();
        //先遍历一遍看有多少个顶点
        for(String str : stringList){
            for(String s : str.split(" ") ){
                int node = Integer.parseInt(s);
               if(!nodeList.contains(node) && node != -1){
                   nodeList.add(node);
               }
            }
        }
        vNum = nodeList.size();
        //根据顶点数 初始化矩阵
        matrix = new int[vNum][vNum];
        for(int i = 0; i < vNum; i++){
            for(int j = 0; j < vNum; j++){
                matrix[i][j] = 0;
            }
        }
        visited = new int[vNum];

        //连接顶点
        for(String str : stringList){
            int a = Integer.parseInt(str.split(" ")[0]);
            int b = Integer.parseInt(str.split(" ")[1]); //如果a和b相连，b->a,[b][a]
            int c = Integer.parseInt(str.split(" ")[2]); // a->c,[a][c]
            int indexA = nodeList.indexOf(a);
            if(b != -1){
                int indexB = nodeList.indexOf(b);
                matrix[indexB][indexA] = 1;
            }
            if(c != -1){
                int indexC = nodeList.indexOf(c);
                matrix[indexA][indexC] = 1;
            }
        }
    }


    void DFS(int i){
        visited[i] = 1; // visited
        for(int j = 0; j < vNum; j++){
            if(matrix[i][j] != 0){ //i and j is connected
                if(visited[j] == 1){// and j is visited
                    isDAG = false;
                    break;
                }else if(visited[j] == -1){
                    //the nodes after this nodes are visited, move to next node
                    continue;
                }else {
                    DFS(j);
                }
            }
        }
        // 遍历过所有相邻结点
        visited[i] = -1;
    }

    public boolean checkIfaDAG(){
        for(int i = 0; i < vNum ; i++){
            if(visited[i] == -1){
                continue;
            }
            DFS(i);
            if(!isDAG){
                return isDAG;
            }
        }
        return isDAG;
    }


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        ArrayList<String> strList = new ArrayList<String>();
        while (num > 0 ){
            String str = scanner.nextLine().trim();
            strList.add(str);
            -- num;
        }
        graph_deadLock graph = new graph_deadLock();
        graph.create_graph(strList);
        String result = graph.checkIfaDAG() ? "NO" : "YES";
        System.out.println(result);
    }
}
