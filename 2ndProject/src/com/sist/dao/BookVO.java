package com.sist.dao;

import java.util.*;


/*
 MNO          NOT NULL NUMBER        
CATENO                NUMBER        
POSTER       NOT NULL VARCHAR2(300) 
TITLE        NOT NULL VARCHAR2(100) 
AUTHOR       NOT NULL VARCHAR2(20)  
PUBLISHING   NOT NULL VARCHAR2(30)  
RELEASE               DATE          
SCORE                 VARCHAR2(10)  
PRICE        NOT NULL VARCHAR2(20)  
INTRODUCTION NOT NULL CLOB          
 */

public class BookVO {
	private int mno;
	private int cateno;
	private String poster;
	private String title;
	private String author;
	private String publishing;
	private String release;
	private String score;
	private String price;
	private String intro;
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getCateno() {
		return cateno;
	}
	public void setCateno(int cateno) {
		this.cateno = cateno;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublishing() {
		return publishing;
	}
	public void setPublishing(String publishing) {
		this.publishing = publishing;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}

}
