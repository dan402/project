package com.sist.dao;

import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class BookManager {
	public void bookAllData() {
		try {
			
			int k=1;
			for(int i=1;i<=5;i++) {
			Document doc=Jsoup.connect("https://book.naver.com/category/index.nhn?cate_code=260010&tab=top100&list_type=list&sort_type=publishday&page="+i).get();
			Elements poster=doc.select("div.thumb img");
			Elements title = doc.select("dt#book_title_");
			for(int m = 1; m <= 20; m++) {
	          title=doc.select("dt#book_title_"+(m-1));
	         }
			Elements author=doc.select("dd.txt_block");
			Elements publishing=doc.select("dd.txt_block");
			Elements release=doc.select("dd.txt_block");
			Elements score=doc.select("dd.txt_desc");
			String price2="";
			try {
			Element price=doc.select("dd.txt_desc").get(i);
			price2=price.text();
			if(price2.equals(" ")) {
				price2="문의요망";
				System.out.println("가격:"+price2);
			}
			}catch(Exception ex) {}
			
			/*
			 String score2="";
								try {
								Element score = doc.select("div.review-rating").get(i);
								score2=score.attr("data-value");
								if(score2.equals(""))
									score2="0";
								System.out.println("����:"+score2);
								}catch(Exception ex) {}
			 
			String review2="";
			try {
			Element review = doc.select("span.ui_card__comment").get(i);
			System.out.println("����:" + review.text());
			review2=review.text();
			}catch(Exception ex) {
				review2="����";
				System.out.println("����:"+review2);
			}
			*/
			
			Elements intro=doc.select("dd#book_intro_");
			for(int n = 1; n <= 20; n++) {
		          intro=doc.select("dd#book_intro_"+(n-1)); 
			}
	
			for(int j=0;j<title.size();j++) {
				try {
				BookVO vo=new BookVO();
				System.out.println("번호:"+k++);
				System.out.println("cateno:1");
				System.out.println("포스터:"+poster.get(j).attr("src"));
				System.out.println("도서명:"+title.get(j).text());
				String auth=author.get(j).text();
				auth=auth.substring(0,auth.indexOf("|"));
				System.out.println("저자:"+auth);
				String pub=publishing.get(j).text();
				pub=pub.substring(pub.indexOf("|")+2,pub.lastIndexOf("|"));
				System.out.println("출판사:"+pub);
				String date=release.get(j).text();
				date=date.substring(date.lastIndexOf("|")+2);
				System.out.println("출간일:"+date);
				String sco=score.get(j).text();
				sco=sco.substring(0,sco.indexOf("|"));
				System.out.println("평점:"+sco);
			
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		System.out.println("End....");
		}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BookManager m=new BookManager();
		m.bookAllData();
		
	}

}
