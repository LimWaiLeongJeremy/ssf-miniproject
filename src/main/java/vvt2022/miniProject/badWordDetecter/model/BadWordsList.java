package vvt2022.miniProject.badWordDetecter.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class BadWordsList {
            
    private String original;
    private String word;
    private long deviations;
    private long info;
    private long start;
    private long end;
    private long replacedLen;

    
    @Override
    public String toString() {
        return "BadWordsList [original=" + original 
                + ", word=" + word 
                + ", deviations=" + deviations 
                + ", info=" + info 
                + ", start=" + start 
                + ", end=" + end 
                + ", replacedLen=" + replacedLen 
                + "]";
    }

    public static BadWordsList create (JsonObject json) {
        final BadWordsList bwl = new BadWordsList();
        bwl.setOriginal(json.getString("original"));
        bwl.setWord(json.getString("word"));
        bwl.setDeviations(json.getJsonNumber("deviations").longValue());
        bwl.setInfo(json.getJsonNumber("info").longValue());
        bwl.setStart(json.getJsonNumber("start").longValue());
        bwl.setEnd(json.getJsonNumber("end").longValue());
        bwl.setReplacedLen(json.getJsonNumber("replacedLen").longValue());
        return bwl;
    }

    public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("id", this.original)
			.add("published_on", this.word)
			.add("title", this.deviations)
			.add("url", this.info)
			.add("imageurl", this.start)
			.add("body", this.end)
			.add("categories", this.replacedLen)
			.build();
	}
    

    public String getOriginal() {
        return original;
    }
    public void setOriginal(String original) {
        this.original = original;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public long getDeviations() {
        return deviations;
    }
    public void setDeviations(long deviations) {
        this.deviations = deviations;
    }
    public long getInfo() {
        return info;
    }
    public void setInfo(long info) {
        this.info = info;
    }
    public long getStart() {
        return start;
    }
    public void setStart(long start) {
        this.start = start;
    }
    public long getEnd() {
        return end;
    }
    public void setEnd(long end) {
        this.end = end;
    }
    public long getReplacedLen() {
        return replacedLen;
    }
    public void setReplacedLen(long replacedLen) {
        this.replacedLen = replacedLen;
    }
}