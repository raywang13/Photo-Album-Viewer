package project3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PhotoSaver {
	
	public void photoSave(ArrayList<String> photoList) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i = 0; i < photoList.size(); i++) {
			out.println(photoList.get(i));
//			System.out.println("swag swag swag ********");
		}
		out.close();
	}
	
}
