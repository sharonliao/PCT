import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.*;

/**
 * Created by liaoxiaoyun on 2019-03-13.
 */
class TreeNode{
    int key =0;
    String data = "";

     TreeNode leftChild = null;
     TreeNode rightChild = null;

    public TreeNode(){};

    public TreeNode(int Key, String data){
        this.key = key;
        this.data = data;
        leftChild = null;
        rightChild = null;
    }
}

public class BinaryTree {

     HashMap<String,TreeNode> nodeMap = new HashMap<>();
    ArrayList<TreeNode> preOrderArray = new ArrayList<>();
    ArrayList<TreeNode> inOrderArray = new ArrayList<>();
    ArrayList<TreeNode> postOrderArray = new ArrayList<>();


    private TreeNode root = null;

    public BinaryTree(){
        root = new TreeNode(0,"root");
    }

    public BinaryTree(String str){
        root = new TreeNode(0,str);
        nodeMap.put(str,root);
    }

    public void creatBnrTree(TreeNode root){

    }

    public void addTreeNode(String parent,TreeNode node){

        if(nodeMap.get(parent).leftChild == null){
            nodeMap.get(parent).leftChild = node;
            nodeMap.put(node.data,node);
        }else if (nodeMap.get(parent).rightChild == null){
            nodeMap.get(parent).rightChild = node;
            nodeMap.put(node.data,node);
        }
    }

    public void addTreeNode(TreeNode parent,TreeNode node){

        if(parent.leftChild == null){
            parent.leftChild = node;
            nodeMap.put(node.data,node);
        }else if (parent.rightChild == null){
            parent.rightChild = node;
            nodeMap.put(node.data,node);
        }
    }

    public static BinaryTree creatTree(ArrayList<String> strList){
        // root node
        String root = strList.get(0).split(" ")[0];
        String child = strList.get(0).split(" ")[1];
        TreeNode chilNode = new TreeNode(1,child);

        BinaryTree binaryTree = new BinaryTree(root);
        binaryTree.addTreeNode(binaryTree.root,chilNode);
        strList.remove(0);

        int id = 2;
        int i = 0;
        int round = 0;
        while (strList.size() > 0 && round < 5 ) {
            //需要再加个判读，如果只剩下独立的点，那么跳出循环
            if(i >= strList.size()){
                i = 0;
                round ++; //记录空转次数
            }
                root = strList.get(i).split(" ")[0];
                child = strList.get(i).split(" ")[1];
                if (binaryTree.nodeMap.containsKey(root)) {
                    chilNode = new TreeNode(id, child);
                    binaryTree.addTreeNode(root, chilNode);
                    ++id;
                    strList.remove(i);
                    i = 0;
                    round = 0; // 数据有变动，round重新记录
                } else{
                    i ++;
                }
        }
        return binaryTree;
    }

    //
    public boolean isCorrectRlt(String person1 ,String relationship, String person2){
        boolean isCorrectRelationship = false;


        return  isCorrectRelationship;
    }



    public void getPreOrderArray(){
       preOrderArray.clear();
       preOrder(root);
        //System.out.println 自带换行符
        System.out.println(printOrder(preOrderArray));
    }

    public void getInOrderArray(){
        inOrderArray.clear();
        inOrder(root);
        //System.out.println 自带换行符
        System.out.println(printOrder(inOrderArray));
    }

    public void getPostOrderArray(){
        postOrderArray.clear();
        postOrder(root);
        //System.out.println 自带换行符
        System.out.println(printOrder(postOrderArray));
    }

    //前序遍历：根结点 ---> 左子树 ---> 右子树

    //中序遍历：左子树---> 根结点 ---> 右子树

    //后序遍历：左子树 ---> 右子树 ---> 根结点

    //层次遍历：只需按层次遍历即可（queue）

    //pre Order 调用前先把array清空
    public void preOrder(TreeNode subTree){
        if(subTree != null){
            //visted(subTree);
            readToArray(subTree,preOrderArray);
            preOrder(subTree.leftChild);
            preOrder(subTree.rightChild);
        }
    }

    //in Oder, clear the array before invocation
    public void inOrder(TreeNode subTree){
        if(subTree != null){
            inOrder(subTree.leftChild);
            //visted(subTree);
            readToArray(subTree,inOrderArray);
            inOrder(subTree.rightChild);
        }
    }

    //post Order ,clear the array before invocation
    public void postOrder(TreeNode subTree){
        if(subTree != null){
            postOrder(subTree.leftChild);
            postOrder(subTree.rightChild);
            //visted(subTree);
            readToArray(subTree,postOrderArray);
        }
    }

    // nonRec
    //前序遍历：根结点 ---> 左子树 ---> 右子树
    public void nonRecPreOrder(TreeNode p) {
        preOrderArray.clear();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = p;
        while (node != null || stack.size() > 0) {
            if (node != null) {
                readToArray(node, preOrderArray);
                stack.push(node);
                node = node.leftChild;
            } else { //pNode == null && !stack.isEmpty()
                node = stack.pop();
                node = node.rightChild;
            }
        }
    }

    public void nonRecInOrder(TreeNode p) {
        inOrderArray.clear();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = p;
        while (node != null || stack.size() > 0) {
            if (node != null) {
                stack.push(node);
                node = node.leftChild;
            } else { //pNode == null && !stack.isEmpty()
                node = stack.pop();
                readToArray(node, inOrderArray);
                node = node.rightChild;
            }
        }
    }

    // lever Traverse , can traverse given root subtree
    public ArrayList<TreeNode> levelTraverse(TreeNode root) {
        ArrayList<TreeNode> levelAry = new ArrayList<>();
        if (root == null) {
            return levelAry;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            readToArray(node,levelAry);
            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.offer(node.rightChild);
            }
        }
        return levelAry;
    }

    // check if A is ancestor of B
    public boolean isAncestor(TreeNode ancestor, TreeNode descendant){
        boolean result = false;
        ArrayList<TreeNode> levelAry = levelTraverse(ancestor);
        if(levelAry.contains(descendant)){
            result = true;
        }
        return result;
    }

    //check if A is sibliing of B
    public boolean isSibling(TreeNode node1, TreeNode node2){
        boolean result = false;
        for(TreeNode node: nodeMap.values()){
            if ((node.leftChild == node1 || node.rightChild == node1)
                    && (node.leftChild == node2 || node.rightChild == node2)){
                result = true;
            }
        }
        return result;
    }

    public boolean isParent(TreeNode parent, TreeNode child){
        boolean result = false;
        if (parent.leftChild != null ){
            if(parent.leftChild == child){
                result = true;
            }
        }else if (parent.rightChild != null ){
            if(parent.rightChild == child){
                result = true;
            }
        }
        return result;
    }

    public boolean isCorrectRltship(String str){
        boolean result = false;
        String[] relation = str.split(" ");
        TreeNode node1 = nodeMap.get(relation[0].trim());
        String relationship = relation[1].toLowerCase();
        TreeNode node2 = nodeMap.get(relation[2].trim());

        switch (relationship) {
            case "parent" : result = isParent(node1,node2); break;
            case "child" : result = isParent(node2,node1); break;
            case "ancestor" : result = isAncestor(node1,node2); break;
            case "descendant" : result = isAncestor(node2,node1); break;
            case "sibling" : result = isSibling(node2,node1); break;
        }
        return result;
    }

    public String checkAllRelatonship(ArrayList<String> relationAry){
        String result = "";
        for(String relation : relationAry){
            if (isCorrectRltship(relation))
                result = result + "T" +" ";
            else
                result = result + "F" +" ";
        }
        return result.trim();
    }



    public String printOrder(ArrayList<TreeNode> array){
        String rtn = "";
        for(TreeNode node:array){
            rtn = rtn + node.data + " ";
        }
        rtn = rtn.trim();
        return  rtn;
    }


    public void readToArray(TreeNode node,ArrayList<TreeNode> array){
        array.add(node);
        //System.out.print(node.data+"\n");
    }


    //
    //
    public static void main(String[] args){
        ArrayList<String> strList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());

        while (num>0){
            // input format : parentName childName
            scanner = new Scanner(System.in);
            strList.add(scanner.nextLine());
            --num;
        }




//        strList.add("Jawahar Indira");
//        strList.add("Motilal Kamala");
//        strList.add("Indira Sanjay");
//        strList.add("Sanjay Varun");
//        strList.add("Indira Rajiv");


        ArrayList<String> relationList = new ArrayList<>();
        scanner = new Scanner(System.in);
        num = Integer.parseInt(scanner.nextLine());
        while (num>0){
            // input format : parentName childName
            scanner = new Scanner(System.in);
            relationList.add(scanner.nextLine());
            --num;
        }


//        relationList.add("Motilal child Jawahar");
//        relationList.add("Varun descendant Indira");

        BinaryTree binaryTree = BinaryTree.creatTree(strList);
        System.out.println(binaryTree.checkAllRelatonship(relationList));
        binaryTree.getPreOrderArray();




        //








//
//        binaryTree.nonRecPreOrder(binaryTree.root);
//        System.out.println(binaryTree.printOrder(binaryTree.preOrderArray));
//
//        binaryTree.getInOrderArray();
//
//        binaryTree.nonRecInOrder(binaryTree.root);
//        System.out.println(binaryTree.printOrder(binaryTree.inOrderArray));
//
//
//        binaryTree.getPostOrderArray();
//
//        System.out.println(binaryTree.printOrder(binaryTree.levelTraverse(binaryTree.root)));

    }

}
