package com.meng.view_test.test;


import com.meng.view_test.subject01.Trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test_Subject01 {
    public static void main(String[] args) throws IOException {


        //加载单词进trie树
        String fileName = "src/com/meng/view_test/subject01/words.txt";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        Trie wordsTree = new Trie();
        while((line = br.readLine()) != null){
            String[] spWords = line.split(" ");
            for(String word:spWords){
                wordsTree.insert(word);
            }
        }


        //需要检查的句子(没有加标点符号)
        String check = "hello yertoday";
        String[] checkWords = check.split(" ");

        StringBuilder affterCheck = new StringBuilder();
        for(String word:checkWords){
            if(wordsTree.search(word)){
                affterCheck.append(word + " ");
            }else{
                affterCheck.append(wordsTree.check(word) + " ");
            }
        }

        System.out.println(affterCheck);
    }


}
