package per.leetcode.subject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import per.leetcode.subject.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的所有路径
 * https://leetcode-cn.com/problems/binary-tree-paths/
 *
 * @Author：TangWenBiao
 * @Email：tangwenbiao@souche.com
 * @CreateTime：2022/4/21 - 9:36 上午
 **/
public class GetPathOfBinaryTree {

    public static void main(String[] args) {
        Gson gson=new GsonBuilder().disableHtmlEscaping().create();
        GetPathOfBinaryTree tree=new GetPathOfBinaryTree();
        TreeNode root=tree.buildTree();
        List<String> list = tree.binaryTreePaths(root);
        System.out.println(gson.toJson(list));

    }

    private TreeNode buildTree(){
        TreeNode root=new TreeNode(1);
        TreeNode two=new TreeNode(2);
        TreeNode three=new TreeNode(3);
        TreeNode five=new TreeNode(5);

        root.setLeft(two);
        root.setRight(three);
        two.setRight(five);
        return root;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths=new ArrayList<>();
        buildPath(root,new StringBuilder(root.getVal()+""),paths);
        return paths;
    }

    private void buildPath(TreeNode node, StringBuilder path, List<String> paths){
        if(node.getLeft()==null&&node.getRight()==null){
            paths.add(path.toString());
            return;
        }
        int curLength = path.length();
        if(node.getLeft()!=null){
            buildPath(node.getLeft(),getFormatPath(path,node.getLeft()),paths);
            path.setLength(curLength);
        }
        if(node.getRight()!=null){
            buildPath(node.getRight(),getFormatPath(path,node.getRight()),paths);
            path.setLength(curLength);
        }


    }

    private StringBuilder getFormatPath(StringBuilder parentPath, TreeNode node){
        parentPath.append("->");
        parentPath.append(node.getVal());
        return parentPath;
    }

}
