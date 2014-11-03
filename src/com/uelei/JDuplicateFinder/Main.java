package com.uelei.JDuplicateFinder;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException{
		Connection c = Sqlmd5.openDB();
		Sqlmd5.init(c);

		
		String[] dir= {"/Users/uelei/Downloads/temp","/Users/uelei/Downloads/untitled folder" };
		
		
		FindFiles.listing(dir);
			


		
//		Sqlmd5.listall(c);

		Sqlmd5.closeDB(c);
		
		
	}
}
