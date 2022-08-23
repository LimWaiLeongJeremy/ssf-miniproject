package vvt2022.miniProject.badWordDetecter.model;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import jakarta.json.JsonObject;

public class BadWordsList {
    // private static final Logger logger = LoggerFactory.getLogger(BadWordsList.class);
        
    private String original;
    private String word;
    private long deviations;
    private long info;
    private long start;
    private long end;
    private long replacedLen;


    @Override
    public String toString() {
        return "BadWordsList [deviations=" + deviations + ", end=" + end + ", info=" + info + ", original=" + original
                + ", replacedLen=" + replacedLen + ", start=" + start + ", word=" + word + "]";
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

    // public static BadWordsList createJsonArray(JsonArray o) {
    //     logger.info("createJson badwordlsit");
    //     //List<BadWordsList> bwl = new LinkedList;

    //     BadWordsList bwl = new BadWordsList();
    //     // JsonObject queryObj = o.getJsonObject("query");
    //     String originalStr = o.getString(0);
    //     bwl.original = originalStr;
    //     String wordStr = o.getString(1);
    //     bwl.word = wordStr;
    //     long deviationNum = o.getlong(2);
    //     bwl.deviations = deviationNum;
    //     long infoNum = o.getlong(3);
    //     bwl.info = infoNum;
    //     long startNum = o.getlong(4);
    //     bwl.start = startNum;
    //     long endNum = o.getlong(5);
    //     bwl.end = endNum;
    //     long replaceLenNum = o.getlong(6);
    //     bwl.replacedLen = replaceLenNum;
    //     return bwl;
    // }
} 