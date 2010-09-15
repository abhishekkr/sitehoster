package responseHandler;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class Responder {
	ResponseRecord resRec = new ResponseRecord();
	
	enum TypeOfFile{
		StoredFile,
		StringFile
	};
	
	public boolean sendResponseHeader(String res, TypeOfFile ToF, resourceHandler.ServerSideScripter objSvrSidScr) throws IOException{
		if(ToF == TypeOfFile.StoredFile){
			File rqRes = new File(res);
			if((rqRes.getCanonicalPath()).startsWith(serverConfig.ServerPaths.WEBDOCS)){
				if(rqRes.exists()){
					System.out.println("---StoreFile : exists");
					resRec.setHTTPResponseHeader(res, objSvrSidScr);
					return true;
				}
				else{
					System.out.println("---StoreFile : no");
					resRec.setHTTPResponseHeader(res, objSvrSidScr);
					return false;
				}
			}
			else{
				getSecured.ReportAttack.GETResManipulation();
			}
		}
		else if(ToF == TypeOfFile.StringFile){
			System.out.println("---StringFile : " + res);
			resRec.setHTTPResponseHeader(res, objSvrSidScr);	
		}
		return false;
	}
	
	/*
	 * description: sends customized HTTP Response as per Request
	 * getRES: the Resource as per mentioned in GET Request
	 * link: the Socket opened for Client Request
	 * return: true/false as per Response sent 
	 */
	public boolean sendHTTPResponseGET(String getRES, Socket link) throws IOException{
		DataOutputStream ds = new DataOutputStream(link.getOutputStream());
		resourceHandler.ServerSideScripter objSvrSidScr = new resourceHandler.ServerSideScripter();
		
		String res=null;		
		
		String WEBDOCS = serverConfig.ServerPaths.WEBDOCS;
		String defaultPage = serverConfig.ServerPaths.defaultPage;

		serverConfig.ServerPages svrPg = new serverConfig.ServerPages();
		String WEBRoot = svrPg.getWebRoot();
		String WEBErr = svrPg.getWebErr();

		if(getRES.length()>0){
	    	try{
	    		resourceHandler.ResReader resRead = new resourceHandler.ResReader();
				if(getRES.equalsIgnoreCase("/") && defaultPage.length()>0){
					res=WEBDOCS+platformSpecificLocator("/" + defaultPage);
					if(sendResponseHeader(res, TypeOfFile.StoredFile, objSvrSidScr)){//res Exists
						resRec.sendHTTPResponseHeader(ds);
						resRead.writeHTTPResponseMsg(res, ds, objSvrSidScr);
					}
					else{//res don't Exists
						sendResponseHeader("404", TypeOfFile.StringFile, objSvrSidScr);
						resRec.sendHTTPResponseHeader(ds);
	            		ds.writeBytes(WEBErr);
	            		ds.writeChars("\n");
					}
				} 
				else if(getRES.equalsIgnoreCase("/") && defaultPage.length()==0){
					sendResponseHeader("200", TypeOfFile.StringFile, objSvrSidScr);
					resRec.sendHTTPResponseHeader(ds);
					ds.writeBytes(WEBRoot);
            		ds.writeChars("\n");
				}
				else{
					res=WEBDOCS+platformSpecificLocator(getRES);
					if(sendResponseHeader(res, TypeOfFile.StoredFile, objSvrSidScr)){
						resRec.sendHTTPResponseHeader(ds);
						System.out.println("++++++++++++++++responseMsg wrote");
						resRead.writeHTTPResponseMsg(res, ds, objSvrSidScr);
						System.out.println("++++++++++++++++response clear");
					}
					else{//res don't Exists
						sendResponseHeader("404", TypeOfFile.StringFile, objSvrSidScr);
						resRec.sendHTTPResponseHeader(ds);
	            		ds.writeBytes(WEBErr);
	            		ds.writeChars("\n");
					}
				}
				System.out.println("Resource to Read: " + res);
        		/*ds.close();*/
				return true;
	    	}
            catch (Exception e){
            	try{
					System.out.println("++++++++++++++++Exception Raised");
					sendResponseHeader("404", TypeOfFile.StringFile, objSvrSidScr);
					resRec.sendHTTPResponseHeader(ds);
            		ds.writeBytes(WEBErr);
            		ds.writeChars("\n");
            		/*ds.close();*/
    				System.out.println("Resource to Read: " + res + ".{ERROR}");
    				return true;
    			} 
                catch (Exception e2){
    				System.err.println("File input error");
    			}
			}
		}
        else{
        	System.out.println("Invalid parameters");
        }
        
        return false;
	}
	
	/*
	 * description: sets Resource path as per Platform
	 * ResLoc: Resource's Location to be tweaked
	 * return: tweaked Resource Location
	 */
	public String platformSpecificLocator(String ResLoc){
		if (httpServer.ThreadExecutor.getPlatform().equalsIgnoreCase("WIN32"))
			return ResLoc.replace("/", "\\");
		else
			return ResLoc;
	}	

	/*
	 * description: returns boolean value on whether resource exists or not 
	 */
	public boolean isResourceExisting(String getRES){
		String resName="";
		
		String WEBDOCS = serverConfig.ServerPaths.WEBDOCS;
		String defaultPage = serverConfig.ServerPaths.defaultPage;
		
		if(getRES.equalsIgnoreCase("/") && defaultPage.length()>0)
			resName=WEBDOCS+platformSpecificLocator("/" + defaultPage);
		else if(getRES.equalsIgnoreCase("/") && defaultPage.length()==0)
			resName=WEBDOCS+platformSpecificLocator("/");
		else 
			resName=WEBDOCS+platformSpecificLocator(getRES);
		
		resourceHandler.ResReader resRedr = new resourceHandler.ResReader();
		try {
			if(resRedr.isResourceExisting(resName)==true)
				return true;
		} catch (IOException e) {
			System.out.println("Error generated while checking existence of Resource requested.");
			e.printStackTrace();
		}
		System.out.println("Error404:: " + resName + " doesn't exist.");
		return false;
	}

}
