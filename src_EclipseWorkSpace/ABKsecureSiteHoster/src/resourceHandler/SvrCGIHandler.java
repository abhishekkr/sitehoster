package resourceHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SvrCGIHandler {

	private ArrayList<String> getVar = new ArrayList<String>();
	private ArrayList<String> getVal = new ArrayList<String>();
	
	/**
	 * it checks if the file is in cgi-bin dir or not
	 * @return 0 if not; 1 if its CGI Script; 2 if its Self Executable
	 */
	public int isCGIPath(String requestRes) {
		if(requestRes.startsWith(serverConfig.ServerPaths.WEBDOCS + "/cgi-bin/")==true){
			return 1;
		}
		return 0;
	}

	public String getCGIConsole(String res){
		String mlResult="";
		Scanner scanner = null;
	    try {
		    scanner = new Scanner(new File(res));
		    while (scanner.hasNextLine()){
	    		String currLine=scanner.nextLine();
		    	if(currLine.trim()=="")
		    		continue;
		    	else if(currLine.trim().startsWith("#!")==true)
		    		mlResult=currLine.substring(currLine.indexOf("#!")+2);
		    	else
		    		break;
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
	
	public void parseGetValues(){
		String tmpGetReq = requestHandler.RequestRecord.GetVariables;
		//entire recursive parsing algo
		while(tmpGetReq!="" || tmpGetReq!=null){
			if (tmpGetReq.contains("&")==false){	
				//seperating variable and value
				addParsedGetValue(tmpGetReq);
				break;
			}
			int sepAND = tmpGetReq.indexOf("&");
			String tmpVar = tmpGetReq.substring(0,sepAND);
			tmpGetReq = tmpGetReq.substring(sepAND+1);	
			//seperating variable and value
			addParsedGetValue(tmpVar);
		}
		return;
	}
	
	private void addParsedGetValue(String tmpVar){
		String getToknVar, getToknVal;
		if(tmpVar.contains("=")==false){
			int sepEQL = tmpVar.indexOf("=");
			getToknVar = tmpVar.substring(0,sepEQL);
			if(tmpVar.length()>(sepEQL+1))
				getToknVal = tmpVar.substring(sepEQL+1);
			else
				getToknVal = "";
		}
		else{
			getToknVar = tmpVar;
			getToknVal = "";			
		}
		//adding it to var n val list
		getVar.add(getToknVar);
		getVal.add(getToknVal);
	}

	public boolean testCGI(){
		return false;
	}
}
