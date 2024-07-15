package com.it332.wildcatonetap.Entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.*;

@Entity
@Table(name = "tblpost")
public class PostEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    
    @Column(name = "content")
    private String content;
    
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    
    @Column(name = "userid")
    private int userId;
    
    @Column(name = "isverified")
    private boolean isVerified;
    
    @Column(name = "fullname")
    private String fullName;
    
    @Column(name = "idnumber")
    private String idNumber;
    
    @Column(name = "profilepicture")
    private String profilePicture;
    
    @Column(name = "image", columnDefinition = "LONGTEXT")
    private String image;
    
    @ElementCollection
    @CollectionTable(name = "post_reactions", joinColumns = @JoinColumn(name = "post_id"))
    @MapKeyColumn(name = "user_id")
    @Column(name = "reaction")
    private Map<Integer, String> reactions = new HashMap<>();
    
    public PostEntity() {
        super();
    }

    public PostEntity(int postId, String content, LocalDateTime timestamp, int userId, boolean isVerified,
                      String fullName, String idNumber, String profilePicture, String image) {
        super();
        this.postId = postId;
        this.content = content;
        this.timestamp = timestamp;
        this.userId = userId;
        this.isVerified = isVerified;
        this.fullName = fullName;
        this.idNumber = idNumber;
        this.profilePicture = profilePicture;
        this.image = image;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Map<Integer, String> getReactions() {
        return reactions;
    }

    public void setReactions(Map<Integer, String> reactions) {
        this.reactions = reactions;
    }

    public int getLikes() {
        return (int) reactions.values().stream().filter(r -> r.equals("like")).count();
    }

    public int getDislikes() {
        return (int) reactions.values().stream().filter(r -> r.equals("dislike")).count();
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "postId=" + postId +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", userId=" + userId +
                ", isVerified=" + isVerified +
                ", fullName='" + fullName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", image='" + (image != null ? "image present" : "no image") + '\'' +
                ", likes=" + getLikes() +
                ", dislikes=" + getDislikes() +
                '}';
    }
}