package com.example.admin_backend.Entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
    
    @Column(name = "adminid")
    private int adminId;
    
    @Column(name = "isverified")
    private boolean isVerified;
    
    @Column(name = "likes")
    private int likes;
    
    @Column(name = "dislikes")
    private int dislikes;

    @Column(name = "fullname")
    private String fullName;
    
    @Column(name = "idnumber")
    private String idNumber;
    
    @ManyToOne
    @JoinColumn(name = "profile_id")  // The column in tblpost that refers to tblprofile
    private ProfileEntity profile;
    
    @Column(name = "image", columnDefinition = "LONGTEXT")
    private String image;
    
    @ElementCollection
    @CollectionTable(name = "post_likes", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "admin_id")
    private Set<Integer> likedBy = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "post_dislikes", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "admin_id")
    private Set<Integer> dislikedBy = new HashSet<>();
    
    @Column(name = "is_deleted")
    private boolean isDeleted = false;
    
    public PostEntity() {
        super();
    }

    public PostEntity(int postId, String content, LocalDateTime timestamp, int adminId, boolean isVerified, int likes,
                      int dislikes, String fullName, String idNumber, ProfileEntity profile, String image) {
        super();
        this.postId = postId;
        this.content = content;
        this.timestamp = timestamp;
        this.adminId = adminId;
        this.isVerified = isVerified;
        this.likes = likes;
        this.dislikes = dislikes;
        this.fullName = fullName;
        this.idNumber = idNumber;
        this.profile = profile;
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

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
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

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Integer> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Set<Integer> likedBy) {
        this.likedBy = likedBy;
    }

    public Set<Integer> getDislikedBy() {
        return dislikedBy;
    }

    public void setDislikedBy(Set<Integer> dislikedBy) {
        this.dislikedBy = dislikedBy;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "postId=" + postId +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", adminId=" + adminId +
                ", isVerified=" + isVerified +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", fullName='" + fullName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", profile='" + profile + '\'' +
                ", image='" + (image != null ? "image present" : "no image") + '\'' +
                ", likedBy=" + likedBy +
                ", dislikedBy=" + dislikedBy +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
