package resourceHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SvrACGIHandler {
	
	/**
	 * it checks if the file is in cgi-bin dir or not
	 * @return 0 if not; 1 if its CGI Script; 2 if its Self Executable
	 */
	public int isACGIPath(String requestRes) {
		if(requestRes.startsWith(serverConfig.ServerPaths.WEBDOCS + "/a-cgi/bin/")==true){
			return 2;
		}
		else if(requestRes.startsWith(serverConfig.ServerPaths.WEBDOCS + "/a-cgi/")==true){
				return 1;
		}
		return 0;
	}

	public String getACGIConsole(String res) {
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
	
	public String acgiToML(String CONSOLE, String acgiFile){
		String cgiShtm=""; 
        try {
            Runtime rt = Runtime.getRuntime();
            String getVarList=formatGetVar();
            //Process pr = rt.exec("cmd /c dir");
            System.out.println("_+_+_+_aCGIToML : getRuntime");
            Process pr = rt.exec(CONSOLE + " \"" + acgiFile + "\" " + getVarList );//+ requestHandler.RequestRecord.GetVariables.replace("&", " "));
            System.out.println("_+_+_+_aCGIToML : get process");

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line=null;
            while((line=input.readLine()) != null) {
                cgiShtm += line;
            }
            System.out.println("_+_+_+_aCGIToML : " + cgiShtm);

            //System.out.println(cgiShtm);
            //int exitVal = pr.waitFor();
            //System.out.println("Exited with error code "+exitVal);
            return cgiShtm;
        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "<div>File gives error.</div>";
        }		
	}

	public String acgibinToML(String acgiFile) {
		String cgiShtm=""; 
		
        try {
            Runtime rt = Runtime.getRuntime();
            String getVarList=formatGetVar();
            //Process pr = rt.exec("cmd /c dir");
            String processStr = "\"" + acgiFile.replace("/", "\\") + "\" " + getVarList;
            System.out.println("ProcStr: " + processStr);
            Process pr = rt.exec(processStr);

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line="";
            while((line=input.readLine()) != null) {
                cgiShtm += line;
                System.out.println("in: " + cgiShtm);
            }
            System.out.println(cgiShtm);

            //System.out.println(cgiShtm);
            //int exitVal = pr.waitFor();
            //System.out.println("Exited with error code "+exitVal);
            return cgiShtm;
        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "<div>File gives error.</div>";
        }		
	}
	
	public String formatGetVar(){
		String getVarList="";
        if(requestHandler.RequestRecord.GetVariables.isEmpty()==false){
        	getVarList = requestHandler.RequestRecord.GetVariables;
            System.out.println("formatGetVar():" + getVarList);
        }
        else{
            System.out.println("no GETVars");
        	return getVarList;
        }
        if(getVarList.contains("&")==true){
        	getVarList = getVarList.replace("&", " ");
        }
        return getVarList;
	}

}
