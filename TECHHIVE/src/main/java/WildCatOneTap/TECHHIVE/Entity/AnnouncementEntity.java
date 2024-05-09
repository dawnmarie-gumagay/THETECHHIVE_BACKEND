package WildCatOneTap.TECHHIVE.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblAnnouncement")
public class AnnouncementEntity {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int announcementID;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "userID")
    private UserEntity user;

    // Constructors, getters, and setters
    public AnnouncementEntity() {
    }

    public AnnouncementEntity(int announcementID, String title, String content, UserEntity user) {
        this.announcementID = announcementID;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public int getAnnouncementID() {
        return announcementID;
    }

    public void setAnnouncementID(int announcementID) {
        this.announcementID = announcementID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
