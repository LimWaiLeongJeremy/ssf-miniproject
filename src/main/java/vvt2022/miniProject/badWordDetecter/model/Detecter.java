package vvt2022.miniProject.badWordDetecter.model;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Detecter {
    private static final Logger logger = LoggerFactory.getLogger(Detecter.class);

    private String username;
    private String content;
    private final String id =   UUID.randomUUID()
                                .toString()
                                .substring(0, 8);
    private long badWordsTotal;
    private String censoredContent;


    public Detecter() {
      
    }

    public Detecter(String username, String content) {
        this.username = username;
        this.content = content;
    }

    // private synchronized String generateId() {
        
    // }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public long getBadWordsTotal() {
        return badWordsTotal;
    }

    public void setBadWordsTotal(long badWordsTotal) {
        this.badWordsTotal = badWordsTotal;
    }

    public String getCensoredContent() {
        return censoredContent;
    }

    public void setCensoredContent(String censoredContent) {
        this.censoredContent = censoredContent;
    }
}
