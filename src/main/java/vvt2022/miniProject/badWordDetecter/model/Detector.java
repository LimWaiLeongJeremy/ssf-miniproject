package vvt2022.miniProject.badWordDetecter.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class Detector {
    private static final Logger logger = LoggerFactory.getLogger(Detector.class);

    private String content;
    private long badWordsTotal;
    private List<BadWordsList> badWordsList;
    private String censoredContent;
    private String message;


    public static Detector createJson(String json) throws IOException {
        logger.info("Detector");
        Detector d = new Detector();
        final List<BadWordsList> bwl = new LinkedList<>();

        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            d.content = o.get("content").toString();
            d.badWordsTotal = o.getJsonNumber("bad_words_total").longValue();
            //d.badWordsList = BadWordsList.createJsonArray(o.getJsonArray("bad_words_list").asJsonArray());
            d.censoredContent = o.get("censored_content").toString();
           
            for (JsonValue v : o.getJsonArray("bad_words_list")){
                bwl.add(BadWordsList.create((JsonObject)v));
            }
            d.badWordsList = bwl;
                
        
            
            logger.info(">>>>>>>" + d.toString());
        }
        return d;
    }
 
    
    @Override
    public String toString() {
        return "Detecter [badWordsList=" + badWordsList.toString() + ", badWordsTotal=" + badWordsTotal
                + ", censoredContent=" + censoredContent
                + ", content=" + content + ", message=" + message + "]";
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public long getBadWordsTotal() {
        return badWordsTotal;
    }
    public void setBadWordsTotal(long badWordsTotal) {
        this.badWordsTotal = badWordsTotal;
    }
    public List<BadWordsList> getBadWordsList() {
        return badWordsList;
    }
    public void setBadWordsList(List<BadWordsList> badWordsList) {
        this.badWordsList = badWordsList;
    }
    public String getCensoredContent() {
        return censoredContent;
    }
    public void setCensoredContent(String censoredContent) {
        this.censoredContent = censoredContent;
    }

  
}