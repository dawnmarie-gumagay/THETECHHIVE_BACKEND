package com.capstone.Corbete.Entity;

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

	public PostEntity() {
		super();
	}

	public PostEntity(int postId, String content, Date timestamp, int userId, boolean isVerified, int likes,
			int dislikes) {
		super();
		this.postId = postId;
		this.content = content;
		this.timestamp = timestamp;
		this.userId = userId;
		this.isVerified = isVerified;
		this.likes = likes;
		this.dislikes = dislikes;
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
	
	
	
	

}
