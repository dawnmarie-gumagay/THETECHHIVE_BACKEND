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
@Table(name = "tblProfile")
public class ProfileEntity {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileID;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "userID")
    private UserEntity user;

    // Constructors, getters, and setters
    public ProfileEntity() {
    }

    public ProfileEntity(int profileID, String firstname, String lastname, UserEntity user) {
        this.profileID = profileID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.user = user;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
