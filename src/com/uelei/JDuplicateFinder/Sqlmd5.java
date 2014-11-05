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
		if (re.next()){
//
//				System.out.println("-------------duplicates ");
//		do	
//		{			System.out.println(path);
//		System.out.println(re.getString("path"));
//		}
//		while (re.next());
//	
//		
//		System.out.println("--------------end");
			ResultSet rr = statement.executeQuery("select * from duplicates where md5 like '"+md5+"'");
			if(!rr.next()){
				
				statement.executeUpdate("insert into duplicates values( NULL,'"+md5+"')");
				
			}
			


			
			}
	
		statement.executeUpdate("insert into files values(NULL,'"+path+"','"+md5+"')");
		
	}
	
	public static void listall(Connection con) throws SQLException{
		
		 Statement statement = con.createStatement();
		 ResultSet rs = statement.executeQuery("select * from duplicates;");
 
		 while(rs.next()){
			 Statement sta = con.createStatement();
	    	  System.out.println(rs.getString("md5")+"\n");
	    	  ResultSet res = sta.executeQuery("select * from files where md5 like '"+rs.getString("md5")+"'");
	      	  while(res.next()){
	      			System.out.println("name = "+res.getString("path"));}
	      	  System.out.println("\n-------------------------------------------------------------------\n");
	      }
	     
	      
	}
	
	
	
	public static boolean init(Connection con){
		
		 Statement statement;
		try {
			statement = con.createStatement();
			 statement.setQueryTimeout(30);
			 statement.executeUpdate("drop table if exists files");
			 statement.executeUpdate("drop table if exists duplicates");
			 statement.executeUpdate("create table if not exists files(id integer auto_increment, path string, md5 string)");	 
//			 statement.executeUpdate("drop table if exists duplicates");
			 statement.executeUpdate("create table if not exists duplicates(id integer auto_increment, md5 string)");			 
			 System.out.println(".........started");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
			 
		return true;
	}
	
	
	

	
}
