package per.leetcode.subject;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * N皇后问题
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * https://leetcode.cn/problems/n-queens/
 * @Author：TangWenBiao
 * @Email：tangwenbiao@souche.com
 * @CreateTime：2022/6/9 - 2:51 下午
 **/
public class NQueen {

    private static Gson GSON=new Gson();

    public static void main(String[] args) {
        int n=1;
        List<List<String>> lists = solveNQueens(n);
        System.out.println(GSON.toJson(lists));
        printEasyForHuman(lists);
        System.out.println(answer(n,lists));

    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> descriptions=new ArrayList<>(n);
        boolean[][] boards=new boolean[n][n];
        choice(descriptions,boards,0);
        return descriptions;
    }

    private static void choice(List<List<String>> descriptions,boolean[][] boards,int row){
        //满足加入条件
        if(row>=boards.length){
            add(descriptions,boards);
            return;
        }
        for (int i = 0; i < boards.length; i++) {
            if(!isValid(boards,i,row)){
                continue;
            }
            //进行选择
            boards[row][i]=true;
            //下一步
            choice(descriptions,boards,row+1);
            //撤销
            boards[row][i]=false;
        }

    }

    private static boolean isValid(boolean[][] boards,int col,int row){
        //不能攻击同一行
        for (int i = 0; i < col; i++) {
            if(boards[row][i]){
                return false;
            }
        }
        //不能攻击同一列
        for (int i = 0; i < row+1; i++) {
            if(boards[i][col]){
                return false;
            }
        }
        //不能攻击左上斜列
        for (int i = 1; i <= Math.max(row,col); i++) {
            if(col - i < 0||row-i<0){
                break;
            }
            if (boards[row - i][col - i]) {
                return false;
            }
        }
        //不能攻击右上斜列
        for (int i = 1; i <= Math.max(row,col); i++) {
            if((col+i)>=boards.length||row-i<0){
                break;
            }
            if(boards[row-i][col+i]){
                return false;
            }
        }
        return true;
    }

    private static void add(List<List<String>> descriptions,boolean[][] boards){
        List<String> description=new ArrayList<>(boards.length);
        for (boolean[] boardLine : boards) {
            StringBuilder builder=new StringBuilder();
            for (boolean b : boardLine) {
                builder.append(b?"Q":".");
            }
            description.add(builder.toString());
        }
        descriptions.add(description);
    }

    private static boolean answer(int n,List<List<String>> descriptions){
        switch (n){
            case 4:
                return answerFor4(descriptions);
            case 1:
                return answerFor1(descriptions);
            default:
                return false;
        }
    }

    private static boolean answerFor4(List<List<String>> descriptions){

        String answer="[[\".Q..\",\"...Q\",\"Q...\",\"..Q.\"],[\"..Q.\",\"Q...\",\"...Q\",\".Q..\"]]";
        return GSON.toJson(descriptions).equals(answer);
    }

    private static boolean answerFor1(List<List<String>> descriptions){
        String answer="[[\"Q\"]]";
        return GSON.toJson(descriptions).equals(answer);
    }

    private static void printEasyForHuman(List<List<String>> descriptions){
        StringBuilder builder=new StringBuilder();
        for (List<String> description : descriptions) {
            for (String s : description) {
                builder.append(GSON.toJson(s));
                builder.append("\n");
            }
            builder.append("=============");
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }

}
