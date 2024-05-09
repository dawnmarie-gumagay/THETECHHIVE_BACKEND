package WildCatOneTap.TECHHIVE.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblFeedback")
public class FeedbackEntity {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackID;

    @Column(name = "rating")
    private int rating;

    @Column(name = "userID")
    private int userID;

    @Column(name = "incidentID")
    private int incidentID;

    @Column(name = "announcementID")
    private int announcementID;

    // Constructors, getters, and setters
    public FeedbackEntity() {
    }

    public FeedbackEntity(int feedbackID, int rating, int userID, int incidentID, int announcementID) {
        this.feedbackID = feedbackID;
        this.rating = rating;
        this.userID = userID;
        this.incidentID = incidentID;
        this.announcementID = announcementID;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    public int getAnnouncementID() {
        return announcementID;
    }

    public void setAnnouncementID(int announcementID) {
        this.announcementID = announcementID;
    }
}
