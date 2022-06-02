package com.meng.view_test.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meng.view_test.subject02.Trie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Test_Subject02 {
    public static void main(String[] args) throws IOException {





        //解析json文件
        String fileName = "src/com/meng/view_test/subject02/Dictionary.json";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        StringBuilder builder = new StringBuilder();
        while((line = br.readLine()) != null){
            builder.append(line);
        }
        JSONArray dictionaryJson = JSON.parseArray(builder.toString());

        //加载进trie树
        Trie trie = new Trie();
        for(int i = 0;i < dictionaryJson.size();i++){
            JSONObject wordItem = dictionaryJson.getJSONObject(i);
            trie.insert(wordItem.getString("EN"),wordItem.getString("CN"));
        }

        //查找测试
        System.out.println(trie.search("sky"));
        System.out.println(trie.search("flower"));

        //查找未在词典的词
        System.out.println(trie.search("he"));





    }
}
