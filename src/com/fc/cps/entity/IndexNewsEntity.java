package com.fc.cps.entity;


/**
 * 
 * 编号、图片、标题、日期、来源、作者、简要内容、详细内容
 *
 */
public class IndexNewsEntity {
	private int nid;
	private String img_url;
	private String raw_title;
	private String date;
	private String source;
	private String author;
	private String content;
	private String detail_content;
	
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getRaw_title() {
		return raw_title;
	}
	public void setRaw_title(String raw_title) {
		this.raw_title = raw_title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDetail_content() {
		return detail_content;
	}
	public void setDetail_content(String detail_content) {
		this.detail_content = detail_content;
	}
	
	
}
