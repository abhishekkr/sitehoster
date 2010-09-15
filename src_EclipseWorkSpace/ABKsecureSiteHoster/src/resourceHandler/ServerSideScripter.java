package resourceHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerSideScripter {
	private String scriptToML;
	private String CONSOLE="";
	private String PHPCLI="";
	
	private String callPatchForXSS(String resData) {
		getSecured.PatchForXSS objGetSecuredPatchXSS = new getSecured.PatchForXSS();
		String patchedML = objGetSecuredPatchXSS.getML(resData);
		return patchedML;
	}
	
	public void htmlToML(String resML) {
        scriptToML = callPatchForXSS(FileR.readChars(resML));
	}
	
	public void mlToML(String origML) {
        scriptToML = callPatchForXSS(origML);
	}
	
	public void phpToML(String phpFile) {
		String phpShtm=""; 
        try {
            Runtime rt = Runtime.getRuntime();
            //Process pr = rt.exec("cmd /c dir");
            Process pr = rt.exec(PHPCLI + " " + phpFile);

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line=null;
            while((line=input.readLine()) != null) {
                phpShtm += line;
            }

            System.out.println(phpShtm);
            int exitVal = pr.waitFor();
            System.out.println("Exited with error code "+exitVal);
            
            scriptToML = callPatchForXSS(phpShtm); 
        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            scriptToML = "<div>File gave error.</div>";
        }
    }

	public void cgiToML(String cgiFile) {
		String cgiShtm=""; 
		resourceHandler.SvrCGIHandler svrCGIHandler = new resourceHandler.SvrCGIHandler();
		CONSOLE = svrCGIHandler.getCGIConsole(cgiFile);
        try {
            Runtime rt = Runtime.getRuntime();
            //Process pr = rt.exec("cmd /c dir");
            Process pr = rt.exec(CONSOLE + " " + cgiFile);

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line=null;
            while((line=input.readLine()) != null) {
                cgiShtm += line;
            }

            System.out.println(cgiShtm);
            int exitVal = pr.waitFor();
            System.out.println("Exited with error code "+exitVal);

            scriptToML = callPatchForXSS(cgiShtm);  
        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            scriptToML = "<div>File gave error.</div>";
        }
    }
	
	public void acgiToML(String acgiFile, int isACGI) {
		resourceHandler.SvrACGIHandler svrACGIHandler = new resourceHandler.SvrACGIHandler();
		if(isACGI==1){
			CONSOLE = svrACGIHandler.getACGIConsole(acgiFile);
			scriptToML = callPatchForXSS(svrACGIHandler.acgiToML(CONSOLE,acgiFile));
		}
		else if(isACGI==2){
			scriptToML = callPatchForXSS(svrACGIHandler.acgibinToML(acgiFile));
		}
		System.out.println("++++++++++++++Console\n"+CONSOLE + "\ndata:\n" + scriptToML + "\n++++++++++++++");
    }
	
	public String getScriptToML(){
		System.out.println("===========data:\n" + scriptToML + "\n============");
		return scriptToML;
	}
	
	public int getLengthOfScriptToML(){
		return scriptToML.length();
	}

	public void setPHPCLI(String phpCLI) {
		PHPCLI = phpCLI;
	}

	public void setCONSOLE(String consoleStr) {
		CONSOLE=consoleStr;		
	}
}
