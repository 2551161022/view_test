package com.meng.view_test.subject02;

/*
*trie树 用来快速查找单词
*/

public class Trie {
    private Trie next[] = new Trie[26];
    private boolean isEnd = false;
    //查找的数据
    private String translation;

    /*
    * 插入方法
    * @param word 插入单词
    * */
    public void insert(String word,String translation){
        Trie node = this;
        for(char c : word.toCharArray()){
            int index = c - 'a' > 0 ?  c - 'a' : 'a' - c;
            if(node.next[index] == null){
                node.next[index] = new Trie();
                node = node.next[index];
            }else{
                node = node.next[index];
            }
        }
        node.isEnd = true;
        node.translation = translation;
    }

    /*
    * 查找方法
    * @param word 需要查找的单词
    * @return 查询结果
    * */
    public String search(String word){
        Trie node = this;
        for(char c : word.toCharArray()){
            int index = c - 'a' > 0 ?  c - 'a' : 'a' - c;
            if(node.next[index] == null){
               return null;
            }else{
                node = node.next[index];
            }
        }
        return node.translation;
    }
}
