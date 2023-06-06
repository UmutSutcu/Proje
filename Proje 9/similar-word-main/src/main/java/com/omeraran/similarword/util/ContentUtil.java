package com.omeraran.similarword.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ContentUtil {

    public  String generateFromMultipleString(String[] texts) {

        int textsLength = texts.length;
        String[] ftexts = new String[textsLength];
        System.arraycopy(texts, 0, ftexts, 0, textsLength);
        ArrayList<String> commonWords = new ArrayList();

        for (int i = 0; i < textsLength-1; i++) {
            String[] words1 = texts[i].split(" ");
            String[] words2 = texts[i+1].split(" ");
            for (String word1 : words1) {
                for (String word2 : words2) {
                    if (word1.equals(word2)) {
                        commonWords.add(word1);
                    }
                }
            }

            for (String word: commonWords){
                for(String word2: words2){
                    if(word2.equals(word)){
                        ftexts[i+1] = ftexts[i+1].replace(word, "");
                    }
                }
            }

            if((words2[0]).contains(words1[words1.length-1])){
                if(!(words2[0]).equals(words1[words1.length-1])){
                    ftexts[i] = ftexts[i].replace(words1[words1.length-1], "");
                }       
            }
            commonWords.clear();
        }

        String result = "";
        for (int i = 0; i < ftexts.length; i++) {
            String[] words1 = ftexts[i].split(" ");
            for(String word : words1){
                result += word + " ";
            }
        }

        return result.trim();
    }
}
