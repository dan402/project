package com.sist.dao;
import java.util.*;
import java.sql.*;

public class BookDAO {

	private Connection conn;
	private PreparedStatement ps;
	
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public BookDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL, "hr", "happy");
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}	
		}
		public void disConnection() {
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}		
	}
	
		
		public void bookInsert(BookVO vo) {
			try {
				getConnection();
				String sql="insert into book values(book_mno_seq.nextval,?,?,?,?,?,?,?,?,?)";
				ps=conn.prepareStatement(sql);
			/*
			 * vo.setCateno(1);
				vo.setPoster(poster.get(j).attr("src"));
				vo.setTitle(title.get(j).text());
				vo.setAuthor(auth);
				vo.setPublishing(pub);
				vo.setRelease(date);
				vo.setScore(sco);
				vo.setPrice(pri);
				vo.setIntro(intro.get(j).text());
			 */
				
				
				//?값을 채운다
				ps.setInt(1, vo.getCateno());
				ps.setString(2, vo.getPoster());
				ps.setString(3, vo.getTitle());
				ps.setString(4, vo.getAuthor());
				ps.setString(5, vo.getPublishing());
				ps.setString(6, vo.getRelease());
				ps.setString(7, vo.getScore());
				ps.setString(8, vo.getPrice());
				ps.setString(9, vo.getIntro());
				
				//실행명령
				ps.executeUpdate();
				
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			finally {
				disConnection();
			}
		}
		
		
		
		//장르
		 public ArrayList<String> bookCateAllData(){
			 ArrayList<String> list=new ArrayList<String>();
			 try {
				 getConnection();
				 String sql="select category from book_cate order by no";
				 ps=conn.prepareStatement(sql);
				 ResultSet rs=ps.executeQuery();
				 while(rs.next()) {
					 String cate=rs.getString(1);
					 list.add(cate);
				 }
				 rs.close();
				 
			 }catch(Exception ex) {}
			return list;
		 }
		 
		 
		 //music 출력
		 public ArrayList<BookVO> bookAllData(int cateno, int page){
			 ArrayList<BookVO> list=new ArrayList<BookVO>();
			 try {
				 //subquery
				 /*
				  * select ename,(select~~)--> 스칼라 서브 쿼리
				     from (select~) --> 인라인뷰
				     where sal=(select ~)  ---> 단일행 서브쿼리, 다중행 서브쿼리
				     ==> 다중 컬럼 서브쿼리 
				     join => select만 사용 가능
				     subquery => dml 전체
				  */
				 getConnection();
				 String sql="select mno,poster,title,author,publishing,release,score,price,introduction"
				 		+ "from (select mno,title,poster,singer,album,rownum as num "
				 		+ "from (select mno,title,poster,singer,album from music where cateno=? order by mno)) "
				 		+ "where num between ? and ?"; //페이징기법
				 int rowSize=30;
				 int start=(rowSize*page)-(rowSize-1);
				 //rownum => 시작번호 1
				 int end=rowSize*page;
				 
				 ps=conn.prepareStatement(sql);
				 ps.setInt(1, cateno);
				 ps.setInt(2, start);
				 ps.setInt(3, end);
				 
				 //실행
				 ResultSet rs=ps.executeQuery();
				 while(rs.next()) {
					 BookVO vo=new BookVO();
					 vo.setMno(rs.getInt(1));
					 vo.setPoster(rs.getString(2));
					 vo.setTitle(rs.getString(3));
					 vo.setAuthor(rs.getString(4));
					 vo.setPublishing(rs.getString(5));
					 vo.setRelease(rs.getString(6));
					 vo.setScore(rs.getString(7));
					 vo.setPrice(rs.getString(8));
					 vo.setIntro(rs.getString(9));
					 
					 list.add(vo);
				 }
				 rs.close();
			 }catch(Exception ex) {
				 System.out.println(ex.getMessage());
			 }
			 finally {
				 disConnection();
			 }
			 return list;
		 }
		 
		 public String bookGetCate(int cateno) {
			 String category="";
			 try {
				 getConnection();
				 String sql="select category from book_cate where no=?";
				 ps=conn.prepareStatement(sql);
				 ps.setInt(1, cateno);
				 ResultSet rs=ps.executeQuery();
				 rs.next();
				 category=rs.getString(1);
				 rs.close();
			 }catch(Exception ex) {
				 System.out.println(ex.getMessage());
			 }
			 finally {
				 disConnection();
			 }
			 return category;
		 }

}