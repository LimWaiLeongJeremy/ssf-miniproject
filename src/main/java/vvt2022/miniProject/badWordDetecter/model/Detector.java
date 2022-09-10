package vvt2022.miniProject.badWordDetecter.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class Detector implements Serializable{
    private static final Logger logger = LoggerFactory.getLogger(Detector.class);

    private String content;
    private long badWordsTotal;
    private List<BadWordsList> badWordsList;
    private String censoredContent;
    private String message;
    private String username;
    private String comment;
    private String id = UUID
                    .randomUUID()
                    .toString()
                    .substring(0, 2); 

    @Override
    public String toString() {
        return "Detector [badWordsList=" + badWordsList + ", badWordsTotal=" + badWordsTotal + ", censoredContent="
                + censoredContent + ", comment=" + comment + ", content=" + content + ", id=" + id + ", message="
                + message + ", username=" + username + "]";
    }

    public static Detector createJson(String json) throws IOException {
        logger.info("Detector");
        Detector d = new Detector();
        final List<BadWordsList> bwl = new LinkedList<>();

        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            d.content = o.get("content").toString();
            d.badWordsTotal = o.getJsonNumber("bad_words_total").longValue();
            d.censoredContent = o.get("censored_content").toString();
           
            for (JsonValue v : o.getJsonArray("bad_words_list")){
                bwl.add(BadWordsList.create((JsonObject)v));
            }
            d.badWordsList = bwl;
        }
        return d;
    }
 
    public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("content", this.content)
			.add("bad_word_total", this.badWordsTotal)
			.add("bad_word_list", (JsonValue) this.badWordsList)
			.add("url", this.censoredContent)
			.build();
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
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}