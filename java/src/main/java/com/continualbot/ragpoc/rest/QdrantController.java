package com.continualbot.ragpoc.rest;

import com.continualbot.ragpoc.core.KnowledgeBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QdrantController {

    private static final Logger logger = LoggerFactory.getLogger(QdrantController.class);
    private final KnowledgeBase knowledgeBase;

    @Autowired
    public QdrantController(KnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }

    @GetMapping("/search")
    public List<Document> search(@RequestParam String query) {
        logger.info("query: {}", query);
        return knowledgeBase.searchVectorStore(query);
    }


}