package com.cgi.uswest.chimpls.portalweb.objects;

import java.sql.Timestamp;

public class Message implements Comparable<Message> {

	private String id;
	private String fromId;
	private String fromUserType;
	private String toId;
	private String toUserType;
	private String title;
	private String content;
	private String attachment;
	private String createdDate;
	private String deletedDate;
	private String fromUserName;
	private String toUserName;
	
	private Timestamp createdDateTimestamp;
	
	Message() {}
	
	public Message(String id, String fromId, String fromUserType, String toId, String toUserType,
			String title, String content, String attachment, String createdDate,
			String deletedDate, String fromUserName,
			String toUserName, Timestamp createdDateTimestamp) {
		super();
		this.id = id;
		this.fromId = fromId;
		this.fromUserType = fromUserType;
		this.toId = toId;
		this.toUserType = toUserType;
		this.title = title;
		this.content = content;
		this.attachment = attachment;
		this.createdDate = createdDate;
		this.deletedDate = deletedDate;
		this.fromUserName = fromUserName;
		this.toUserName = toUserName;
		this.createdDateTimestamp = createdDateTimestamp;
	}

	public int compareTo(Message m) {
		if (m.getCreatedDateTimestamp() == null) {
			return this.getCreatedDateTimestamp() == null ? 0 : 1;
		}
		
		if (this.getCreatedDateTimestamp() == null) {
			return -1;
		}
		
		if (this.getCreatedDateTimestamp().before(m.getCreatedDateTimestamp())) {
			return 1;
		}
		else if (this.getCreatedDateTimestamp().equals(m.getCreatedDateTimestamp())) {
			return 0;			
		}
		else return -1;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getFromUserType() {
		return fromUserType;
	}

	public void setFromUserType(String fromUserType) {
		this.fromUserType = fromUserType;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getToUserType() {
		return toUserType;
	}

	public void setToUserType(String toUserType) {
		this.toUserType = toUserType;
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

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(String deletedDate) {
		this.deletedDate = deletedDate;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getCreatedDateTimestamp() {
		return createdDateTimestamp;
	}

	public void setCreatedDateTimestamp(Timestamp createdDateTimestamp) {
		this.createdDateTimestamp = createdDateTimestamp;
	}
	
}
