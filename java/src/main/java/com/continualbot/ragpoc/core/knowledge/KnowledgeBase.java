package com.continualbot.ragpoc.core.knowledge;

import com.continualbot.ragpoc.helper.TextUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KnowledgeBase {

    public static final String HELLO_MESSAGE = "Hello human! How are you?";
    public static final String DEFAULT_ANSWER = "Sorry, I did not understand. Could you please rephrase?";
    public static final String DEFAULT_IDK = "At this time, I do not possess the necessary knowledge to provide a conclusive response. However, I am committed to expanding my knowledge and will return with a more informed answer at a later time. Thank you for your understanding.";
    @Autowired
    SolrAPI solrAPI;


    @Autowired
    TextUtils textUtils;


    public String getDataFromSolr(String inputStr) throws SolrServerException, IOException {
        return solrAPI.solrSearch(inputStr);
    }

    public String searchForMoreKnowledge(String inputText) throws Exception {
        String keyWord = textUtils.extractWordsAfterAbout(inputText);
//        String newAnswer = textAnalyzer.extractRelevantSentences(wikipedia.wikipediaGetContent(keyWord), keyWord, 1)
        String newAnswer = "NEW ANSWER";
        solrAPI.solrIndexer(newAnswer, keyWord);
        return newAnswer;
    }
}
