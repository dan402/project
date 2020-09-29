package com.sist.dao;

import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/*
 * 	private int mno;
	private int storeno;
	private int rank;
	private int increment;
	private String poster;
	private String title;
	private String author;
	private String name;
	private String price;
 * 
 * 
 */

public class BookChartManager {
	public void chartAllData() {
		try {
			
		int k=1;
			for(int i=1;i<=6;i++) {
			Document doc=Jsoup.connect("https://book.naver.com/bestsell/bestseller_list.nhn?type=image&cp=yes24&cate=001001015").get();
			Elements increment=doc.select("span.rank");
			Elements poster=doc.select("div.thumb_type img");
			Elements title = doc.select("div.thumb_type img");
			Elements author=doc.select("dd#book_author_");
			for(int n = 1; n < i; n++) {
		          author=doc.select("dd#book_author_"+(n-1));
			}     
		
			
			for(int j=0;j<title.size();j++) {
				try {
				BookChartVO vo=new BookChartVO();
				System.out.println("순위:"+k++);
				System.out.println("cateno:1");
				System.out.println("상승:"+increment.get(j).text());
				System.out.println("포스터:"+poster.get(j).attr("src"));
				System.out.println("도서명:"+title.get(j).attr("alt"));
				System.out.println("저자:"+author.get(j).text());
							
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

		BookChartManager m=new BookChartManager();
		m.chartAllData();
		
	}

}
