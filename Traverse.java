package project3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Traverse {
	
	public void traverseArray (File aFile, ArrayList<String> list) throws IOException {
		//checks if the file is a directory
		//if it is, add files in the directory to array
		//recursion calls traverse again
		if(aFile.isDirectory()) {
			File [] files = aFile.listFiles();
			for(int i = 0; i < files.length; i++) {
				traverseArray(files[i], list);
			}
		} //end of if statement
		else { //if aFile is a file and not directory, get absolute path
			   //if path ends with "jpg", read in path and add to arraylist
			String path = aFile.getAbsolutePath();
				if(path.endsWith("jpg")) {
					list.add(path);
//					System.out.println("Path: " +path);
				}
		}
	}

}
