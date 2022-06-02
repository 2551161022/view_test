package com.meng.view_test.subject01;

/*
 *trie树 用来快速查找单词
 */

public class Trie {
    private Trie next[] = new Trie[26];
    private boolean isEnd = false;

    /*
     * 插入方法
     * @param word 插入单词
     * */
    public void insert(String word){
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
}
