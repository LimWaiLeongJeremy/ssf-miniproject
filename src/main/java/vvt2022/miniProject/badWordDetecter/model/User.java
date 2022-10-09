package vvt2022.miniProject.badWordDetecter.model;

public class User {
    private String username;
    private String comment;
  

    @Override
    public String toString() {
        return "User [comment=" + comment 
                + ", username=" + username 
                + "]";
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