package com.uelei.JDuplicateFinder;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException{
		Connection c = Sqlmd5.openDB();
		Sqlmd5.init(c);

		FindFiles.listing("/Users/uelei/Downloads/2008-12-31_viradaAno");
			
		
		FindFiles.listing("/Users/uelei/Downloads/untitled folder");

		
		Sqlmd5.listall(c);

		Sqlmd5.closeDB(c);
		
		
	}
}
