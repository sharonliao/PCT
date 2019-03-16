import java.util.ArrayList;

/**
 * Created by liaoxiaoyun on 2019-03-14.
 */
public class graphic_DFS {
    int vNum; // point number
    int eNum; // edge number
    int[][]graph = new int[200][200];
    int[] nodes;//
    boolean isDAG = true; // Whether it is a directed acyclic graph

    public void create(int v,int e){
        vNum = v;
        eNum = e;

        //initial a matrix ( v*v)
        for(int i = 0; i< vNum; i++){
            for(int j = 0; j < vNum; j++){
                graph[i][j] = 0;
            }
        }

        //create edge
        //0-1 1-2 2-3 3-4 4
        ArrayList<String> edgeAry = new ArrayList<>();
        edgeAry.add("0 1");
        edgeAry.add("1 2");
        edgeAry.add("2 3");
        edgeAry.add("3 4");
        edgeAry.add("4 1");

        for(String edge : edgeAry){
            int i = Integer.parseInt(edge.split(" ")[0]);
            int j = Integer.parseInt(edge.split(" ")[1]);
            graph[i][j] = 1;
        }

        // initial nodes array, 0 means the node hasn't been visited.
        nodes = new int[vNum];
        for(int i = 0; i < vNum; i ++){
            nodes[i] = 0;
        }
    }

    void DFS(int i){
        System.out.println(i);
        nodes[i] = 1; // visited
        for(int j = 0; j < vNum; j++){
            if(graph[i][j] != 0){ //i and j is connected
                if(nodes[j] == 1){// and j is visited
                    isDAG = false;
                    break;
                }else if(nodes[j] == -1){
                    //the nodes after this nodes are visited, move to next node
                    continue;
                }else {
                    DFS(j);
                }
            }
        }
        // 遍历过所有相邻结点
        nodes[i] = -1;
    }

    public static void  main(String[] args){
        graphic_DFS graph = new graphic_DFS();
        graph.create(5,5);
        for(int i = 0; i < graph.vNum; i ++){
            if (graph.nodes[i] == -1){
                continue;
            }
            graph.DFS(i);
            if(!graph.isDAG){
                System.out.println("cyclic graph");
                break;
            }
        }
        if(graph.isDAG){
            System.out.print("acyclic graph");
        }
    }







}
