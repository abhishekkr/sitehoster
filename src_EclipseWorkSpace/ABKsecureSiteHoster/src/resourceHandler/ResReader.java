package resourceHandler;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import serverConfig.appMACROS;

public class ResReader implements appMACROS{
	
	public void writeHTTPResponseMsg(String res, DataOutputStream ds, resourceHandler.ServerSideScripter objSvrSidScr) throws IOException
	{
		String resDataToSend;
		//ServerSideScripter objSvrSidScr = new ServerSideScripter();
		resourceHandler.SvrCGIHandler svrCGIHandler = new resourceHandler.SvrCGIHandler();
		resourceHandler.SvrACGIHandler svrACGIHandler = new resourceHandler.SvrACGIHandler();
		if(svrCGIHandler.isCGIPath(res)>0){
			resDataToSend=objSvrSidScr.getScriptToML();
			ds.write(resDataToSend.getBytes());
		}
		else if(svrACGIHandler.isACGIPath(res)>0){
			resDataToSend=objSvrSidScr.getScriptToML();
			System.out.println("++++++++++++++++ACGI to send - objSvrSideScr\n" + resDataToSend);
			ds.write(resDataToSend.getBytes());
			System.out.println("++++++++++++++++ACGI to send - resDataToSend");
		}
		else if(res.endsWith(".php")){
			resDataToSend=objSvrSidScr.getScriptToML();
			ds.write(resDataToSend.getBytes());
		}
		else if(res.endsWith(".htm") || res.endsWith(".html")){
			resDataToSend=objSvrSidScr.getScriptToML();
			ds.write(resDataToSend.getBytes());
		}        
		else{	
			FileR.writeResponseBytes(res,ds);
		}        
        return;// true;
	}
	
	public boolean isResourceExisting(String res) throws IOException
	{
		File fRes = new File (res);
		if(fRes.exists()==true)
			return true;        
        return false;
	}
}
