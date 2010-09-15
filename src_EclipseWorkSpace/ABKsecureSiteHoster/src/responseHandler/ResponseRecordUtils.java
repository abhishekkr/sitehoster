package responseHandler;

import java.io.File;
import java.util.Date;

public class ResponseRecordUtils {
	
	public String getServerSig(){
		return serverConfig.appMACROS.SERVER;
	}
	
	@SuppressWarnings("deprecation")
	public String getServerDate(){
		 Date d = new Date();
		 String dat=null;
		 //Mon, 18 Jan 2010 12:37:07 GMT
		 switch(d.getDay()){
		 	case 1:
		 		dat = "Sun, " + d.toGMTString();
		 		break;
		 	case 2:
		 		dat = "Mon, " + d.toGMTString();
		 		break;
		 	case 3:
		 		dat = "Tue, " + d.toGMTString();
		 		break;
		 	case 4:
		 		dat = "Wed, " + d.toGMTString();
		 		break;
		 	case 5:
		 		dat = "Thu, " + d.toGMTString();
		 		break;
		 	case 6:
		 		dat = "Fri, " + d.toGMTString();
		 		break;
		 	case 7:
		 		dat = "Sat, " + d.toGMTString();
		 		break;		 		
		 }
		return dat;
	 }

	public long getContentLen(String res, resourceHandler.ServerSideScripter objSvrSidScr) {
		File rqRes = new File(res);
		serverConfig.ServerPages svrPg = new serverConfig.ServerPages();
		//resourceHandler.ServerSideScripter objSvrSidScr = new resourceHandler.ServerSideScripter();
		resourceHandler.SvrCGIHandler svrCGIHandler = new resourceHandler.SvrCGIHandler();
		resourceHandler.SvrACGIHandler svrACGIHandler = new resourceHandler.SvrACGIHandler();
		long resLen = 0;
		if(rqRes.exists()){
			int isCGI = svrCGIHandler.isCGIPath(res); //0-No; 1-Script; 2-Binary
			int isACGI = svrACGIHandler.isACGIPath(res); //0-No; 1-Script; 2-Binary
			if(isCGI==1){
				System.out.println("Resource needs CGI");
				objSvrSidScr.cgiToML(res);
				resLen = objSvrSidScr.getLengthOfScriptToML();
			}
			else if(isACGI==1){ //for a-cgi/
				System.out.println("Resource needs ABK-CGI");
				objSvrSidScr.acgiToML(res,isACGI);
				resLen = objSvrSidScr.getLengthOfScriptToML();
			}
			else if(isACGI==2){ //for a-cgi/bin/
				System.out.println("Resource needs ABK-CGI");
				objSvrSidScr.acgiToML(res,isACGI);
				resLen = objSvrSidScr.getLengthOfScriptToML();
			}
			else if(res.endsWith(".php")){
				System.out.println("Resource needs PHP");
				objSvrSidScr.phpToML(res);
				resLen = objSvrSidScr.getLengthOfScriptToML();
			}
			else if(res.endsWith(".htm") || res.endsWith(".html")){
				System.out.println("Resource is MarkupLanguage");
				objSvrSidScr.htmlToML(res);
				resLen = objSvrSidScr.getLengthOfScriptToML();
			}
			else{
				System.out.println("Resource is PLAiN");
				resLen = rqRes.length();
			}
		}
		else{
			if(res.equalsIgnoreCase("200"))
				resLen = svrPg.getWebRoot().length();
			else //if(res.equalsIgnoreCase("404"))
				resLen = svrPg.getWebErr().length();
		}
		return resLen;
	}

	public String getContentTyp(String res) {
		File rqRes = new File(res);
		serverConfig.ContentType sCT = new serverConfig.ContentType();
		String typ = "*/*";
		String fileExt = res.substring(res.lastIndexOf('.')+1, res.length());
		if(rqRes.exists()){
			typ = sCT.getContentType(fileExt);
		}
		else{
			if(res.length()<4)
				typ = "text/html";
			else //if(res.equalsIgnoreCase("404"))
				typ = null;
		}
		System.out.println("\nServer detected the ContentType: " + typ + "; for a File Extension: " + fileExt );
		return typ;
	}

	public String getStatusCode(String res) {
		File rqRes = new File(res);
		serverConfig.ServerCode sCode = new serverConfig.ServerCode();
		String status = "500 Internal Server Error"; //null
		if(rqRes.exists()){
			status = "200" + " " + sCode.getCodeString(200);// OK";
		}
		else{
			if(res.length()<4){
				status = res + " " + sCode.getCodeString(Integer.parseInt(res));}
			else{
				status = "404" + " " + sCode.getCodeString(404);
			}
		}
		System.out.println("\nResponse Status set to " + status);
		return status;
	}

	public String getXXSSProtection() {
		/*
		[-] http parameter [X-XSS-Protection: 0] not detected.
		[-] http parameter [X-XSS-Protection: 1] defense is not applied at domain.
		[-] http parameter [X-FRAME-OPTIONS: DENY] clickjacking defense is not applied 
		[-] http parameter [X-FRAME-OPTIONS: SAMEORIGIN] clickjacking defense is not applied 
		[-] http parameter [X-CONTENT-TYPE-OPTIONS: NOSNIFF] mime handling-sniffing opt out is not applied 
		[-] http parameter [X-DOWNLOAD-OPTIONS: NOOPEN ] mime handling- download force save is not applied 
		[-] http parameter [X-CONTENT-SECURITY-POLICY: ALLOW SELF] content policy is not applied.
		[-] http parameter [X-CONTENT-SECURITY-POLICY: ALLOW https://self] content policy is not applied.
		[-] http parameter [ACCESS-CONTROL-ALLOW-ORIGIN] csrf origin access is not applied.
		*/
		return "0";
	}

	public String getConnectionTyp(String res) {
		File rqRes = new File(res);
		String connecn = null;
		if(rqRes.exists()){
			connecn = null;
		}
		else{
			if(res.length()<4){
				connecn = "Close";
			}
			else{
				connecn = "Close";
			}
		}
		return connecn;
	}

}
