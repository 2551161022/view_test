package com.meng.view_test.subject01;

/*
 *trie树 用来快速查找单词
 */

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private Trie next[] = new Trie[26];
    private boolean isEnd = false;
    private String indexChar;

    public Trie(){
    }

    public Trie(String index){
        this.indexChar = index;
    }

    /*
     * 插入方法
     * @param word 插入单词
     * */
    public void insert(String word){
        Trie node = this;
        for(char c : word.toCharArray()){
            int index = c - 'a' > 0 ?  c - 'a' : 'a' - c;
            if(node.next[index] == null){
                node.next[index] = new Trie(c + "");
                node = node.next[index];
            }else{
                node = node.next[index];
            }
        }
        node.isEnd = true;
    }

    /*
     * 查找方法
     * @param word 需要查找的单词
     * @return 查询结果
     * */
    public boolean search(String word){
        Trie node = this;
        for(char c : word.toCharArray()){
            int index = c - 'a' > 0 ?  c - 'a' : 'a' - c;
            if(node.next[index] == null){
                return false;
            }else{
                node = node.next[index];
            }
        }
        return true;
    }
    /*
    * 获取类似的单词
    * */
    public String check(String word){

        String search = "";

        List<String> similar = new ArrayList<>();
        for(char c : word.toCharArray()){
            search += c;

            List<String> checked = new ArrayList<>();
            if(!(startWith(search ,checked) == null)){
                similar = checked;
                checked = startWith(search + c,checked);

            }else{
                break;
            }
        }


        int max = word.length();
        String mostSimilar = "";
        //使用字符串开头来减少字符串数量
        for(String checkWord : similar){
            int distance = minDistance(word,checkWord);
            if(distance < max){
                max = distance;
                mostSimilar = checkWord;
            }
        }

        return mostSimilar;

    }

    //获取以xxx开头的单词
    public List<String> startWith(String nowString,List<String> strings){

        //先遍历到最前面
        Trie node = this;
        for(char c : nowString.toCharArray()){
            int index = c - 'a' > 0 ?  c - 'a' : 'a' - c;
            if(node.next[index] == null){
                return null;
            }else{
                node = node.next[index];
            }
        }


        //遍历子节点
//        List<String> strings = new ArrayList<>();

        if(node.isEnd){
            strings.add(nowString);
        }else{
            for(Trie t : node.next)  {
                if(t != null){
                    startWith(nowString + t.indexChar,strings);
                }

            }
        }
        return strings;

    }

    /*
    * 获取两个字符串编辑距离
    */
    public static int minDistance(String sourceStr, String targetStr) {
        int sourceLen = sourceStr.length();
        int targetLen = targetStr.length();

        if (sourceLen == 0) {
            return targetLen;
        }
        if (targetLen == 0) {
            return sourceLen;
        }

        int[][] arr = new int[sourceLen + 1][targetLen + 1];

        for (int i = 0; i < sourceLen + 1; i++) {
            arr[i][0] = i;
        }
        for (int j = 0; j < targetLen + 1; j++) {
            arr[0][j] = j;
        }

        Character sourceChar = null;
        Character targetChar = null;

        for (int i = 1; i < sourceLen + 1; i++) {
            sourceChar = sourceStr.charAt(i - 1);

            for (int j = 1; j < targetLen + 1; j++) {
                targetChar = targetStr.charAt(j - 1);

                if (sourceChar.equals(targetChar)) {

                    arr[i][j] = arr[i - 1][j - 1];
                } else {
                    arr[i][j] = (Math.min(Math.min(arr[i - 1][j], arr[i][j - 1]), arr[i - 1][j - 1])) + 1;
                }
            }
        }
        return arr[sourceLen][targetLen];
    }
}
