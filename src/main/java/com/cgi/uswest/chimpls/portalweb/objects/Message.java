package com.cgi.uswest.chimpls.portalweb.objects;

public class Message {

	private String id;
	private String fromId;
	private String fromUserType;
	private String toId;
	private String toUserType;
	private String title;
	private String content;
	private String attachment;
	private String deletedDate;
	
	Message() {}
	
	public Message(String id, String fromId, String fromUserType, String toId, String toUserType,
			String title, String content, String attachment, String deletedDate) {
		super();
		this.id = id;
		this.fromId = fromId;
		this.fromUserType = fromUserType;
		this.toId = toId;
		this.toUserType = toUserType;
		this.title = title;
		this.content = content;
		this.attachment = attachment;
		this.deletedDate = deletedDate;
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
	
}
