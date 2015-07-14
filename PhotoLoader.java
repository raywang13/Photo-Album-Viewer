package project3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PhotoLoader {

	public void photoLoad(ArrayList<String> list) throws IOException{
		try {
			Scanner fileScan = new Scanner(new File("output.txt"));
			//String line = fileScan.nextLine();
			//Scanner scan = new Scanner(line).useDelimiter("\\s*, \\s*");
			while(fileScan.hasNextLine()) {
				String path = fileScan.nextLine();
				list.add(path);
//				System.out.println("blah blah blah ******");
			}
		} catch (IOException e1) {
		}
	} //end of photoLoader

} //end of class
