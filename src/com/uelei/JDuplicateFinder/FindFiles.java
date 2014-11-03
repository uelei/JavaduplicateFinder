package com.uelei.JDuplicateFinder;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class FindFiles {
	static Connection con = Sqlmd5.openDB();
	static String directorys[] = new String[20000] ;
	static int i = 0;
	static int ai = 0;
	
	
//	public static void main(String[] args) {
//		File currentDir = new File("."); // current directory
//		displayDirectoryContents(currentDir);
//	}

	public static void displayDirectoryContents(File dir) {
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				//System.out.println("looking in directory:" + file.getCanonicalPath());
				
				displayDirectoryContents(file);
			} else {
				
				
				if(!file.getName().equals(".DS_Store")){
					
				
						
						try {
							Sqlmd5.addnew(con, file.getCanonicalPath(),  Md5CheckSum.getMD5Checksum(file.getPath()));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//							System.out.println(Md5CheckSum.getMD5Checksum(l.getPath()));

						
		
				}
					
				
//					System.out.println("     file:" + file.getCanonicalPath());
			}
		}
	}
	
	
	
	
	
	public static void listing(String[] orig ){
		for (String or : orig ){

			File currentDir = new File(or); // current directory
			displayDirectoryContents(currentDir);
			
			
			}
		


//		while (ai < i ){			
//			scand(directorys[ai]);
//			lisfiles(directorys[ai]);
//			++ai;
//		 }
		

		
	}
	
	
	
}
