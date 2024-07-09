package com.it332.wildcatonetap.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private Date timestamp;
	
	@Column(name = "userid")
	private int userId;
	
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
    
    @Column(name = "profilepicture")
    private String profilePicture;
    
	@Column(name = "image", columnDefinition = "LONGTEXT")
	private String image;
	
	
	public PostEntity() {
		super();
	}

	public PostEntity(int postId, String content, Date timestamp, int userId, boolean isVerified, int likes,
	                  int dislikes, String fullName, String idNumber, String profilePicture, String image) {
        super();
        this.postId = postId;
        this.content = content;
        this.timestamp = timestamp;
        this.userId = userId;
        this.isVerified = isVerified;
        this.likes = likes;
        this.dislikes = dislikes;
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

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
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

    @Override
    public String toString() {
        return "PostEntity{" +
                "postId=" + postId +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", userId=" + userId +
                ", isVerified=" + isVerified +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", fullName='" + fullName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", image='" + (image != null ? "image present" : "no image") + '\'' +
                '}';
    }
}
