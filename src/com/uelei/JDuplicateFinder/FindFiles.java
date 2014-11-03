package com.uelei.JDuplicateFinder;

import java.io.File;
import java.sql.Connection;

public class FindFiles {
	static Connection con = Sqlmd5.openDB();
	static String directorys[] = new String[20000] ;
	static int i = 0;
	static int ai = 0;
	
	
	public static void scand(String path){
		File fpath = new File(path); 
		File[] listOfFiles = fpath.listFiles();
		for(File f : listOfFiles ){
			if(f.isDirectory()){
				++i;
				directorys[i] = f.getPath();
			}
		 }
	}
	
	public static void lisfiles(String path){
//		Connection con = Sqlmd5.openDB();
		
		File folder = new File(path);
		File[] list = folder.listFiles();	
		for(File l : list ){
			if(l.isFile()){
				if(!l.getName().equals(".DS_Store")){
					
					try {
						
						Sqlmd5.addnew(con, l.getPath(),  Md5CheckSum.getMD5Checksum(l.getPath()));
//						System.out.println(Md5CheckSum.getMD5Checksum(l.getPath()));

						
						
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		}
		

	
	}
	
	public static void listing(String orig ){
		directorys[0] = orig ;
		while (ai <= i ){
			scand(directorys[ai]);
			lisfiles(directorys[ai]);
			++ai;
		 }		 
	}
	
	
	
}
