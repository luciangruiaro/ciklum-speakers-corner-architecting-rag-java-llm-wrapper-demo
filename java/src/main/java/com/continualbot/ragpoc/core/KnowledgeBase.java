package com.continualbot.ragpoc.core;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeBase {

    public static final String DEFAULT_ANSWER = "Hello human! How are you?";
    @Autowired
    private VectorStore vectorStore;

    private KnowledgeBase() {
    }

    public List<Document> searchVectorStore(String query) {
        return vectorStore.similaritySearch(SearchRequest.query(query).withTopK(5));
    }
}
