package com.uelei.JDuplicateFinder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sqlmd5 {
	
	public static Connection openDB(){
		 try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 Connection con = null;

		 try {
				con = DriverManager.getConnection("jdbc:sqlite:filemd5.db");
//				con = DriverManager.getConnection("jdbc:sqlite:/Users/uelei/Downloads/filemd5.db");


			 //			init(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return con;	
	}
	
	public static void closeDB(Connection con){
		try {
			con.close();	
			System.out.println("closed");
		} catch(SQLException e3){
			System.out.println(e3.getMessage());
		} catch (Exception e3) {
			System.out.println(e3.getMessage());
		}
	}
	
	
	public static void addnew (Connection con, String path, String md5 ) throws SQLException{
		
		Statement statement = con.createStatement();
		ResultSet re = statement.executeQuery("select * from files where md5 like '"+md5+"'");
		while (re.next()){
			System.out.println("-------------duplicates ");
			System.out.println(path);
			System.out.println(re.getString("path"));
			System.out.println("--------------end");
			
		}
	
		statement.executeUpdate("insert into files values(null ,'"+path+"','"+md5+"')");
		
	}
	
	public static void listall(Connection con) throws SQLException{
		
		
		 Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("select * from files");
	      while(rs.next()){
	    	  System.out.println("name = "+rs.getString("path"));
	    	  System.out.println("id = "+ rs.getString("md5"));
	      }
	}
	
	
	
	public static boolean init(Connection con){
		
		 Statement statement;
		try {
			statement = con.createStatement();
			 statement.setQueryTimeout(30);
			 statement.executeUpdate("drop table if exists files");
			 statement.executeUpdate("create table if not exists files(id integer auto_increment, path string, md5 string)");
			 System.out.println(".........started");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
			 
		return true;
	}
	
	
	

	
}
