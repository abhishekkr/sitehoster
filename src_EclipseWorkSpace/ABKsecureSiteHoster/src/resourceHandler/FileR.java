package resourceHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileR {
	//reading Chars and returning the String to be sent
	public static String readChars(String res){
		String mlResult="";
		Scanner scanner = null;
	    try {
		    scanner = new Scanner(new File(res));
		    String NL = System.getProperty("line.separator");
		    boolean boolNL = true;
		    while (scanner.hasNextLine()){
		    	if(boolNL){
		    		String currLine=scanner.nextLine();
		    		if(currLine.toLowerCase().contains("</head>")){
		    			boolNL=false;
			    		mlResult+=currLine;
		    		}
		    		else{
		    			mlResult+=currLine + NL;
		    		}
		    	}
		    	else{
		    		mlResult+=scanner.nextLine();
		    	}
		    }
	    } catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("\nStatic ML Read Error :(\n");
		}
	    finally{
	      scanner.close();
	    }
	    return mlResult;
	}
	
	//any non-web file to be sent byte-by-byte directly from here
	public static void writeResponseBytes(String res, DataOutputStream ds){
		try {
			FileInputStream fstream = new FileInputStream(res);
			DataInputStream in = new DataInputStream(fstream);
			while (in.available() !=0){
				ds.writeByte(in.readUnsignedByte());					
			}
			in.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
