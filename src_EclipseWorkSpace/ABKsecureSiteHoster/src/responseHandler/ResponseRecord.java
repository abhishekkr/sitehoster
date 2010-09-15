package responseHandler;

import java.io.DataOutputStream;
import java.io.IOException;

public class ResponseRecord {
	/*
	HTTP/1.0 400 Bad Request
	Content-Type: text/html; charset=UTF-8
	Content-Length: 1350
	Date: Mon, 18 Jan 2010 12:37:07 GMT
	Server: GFE/2.0
	X-XSS-Protection: 0
	*/
	/*
	HTTP Status Code: HTTP/1.1 302 Found
	Location:	http://www.google.de/	
	Cache-Control:	private	
	Content-Type:	text/html; charset=UTF-8	
	Set-Cookie:	PREF=ID=337f53654176b064:TM=1263813335:LM=1263813335:S=hefeNT7MO6bQWYI5; expires=Wed, 18-Jan-2012 11:15:35 GMT; path=/; domain=.google.com	
	Set-Cookie:	NID=31=NSuZcqJhoWF_kGa07p5Hci8sLJRG6J0LkjwO4UQphz5u6rJhjQWvYxqLG-NTjKBEFe5CxQ9pE3NyiqfwMuJv3zunbsJHXRdYzMIvWdnCbUyF3jLLJB5YgokF4MCsx2xR; expires=Tue, 20-Jul-2010 11:15:35 GMT; path=/; domain=.google.com; HttpOnly	
	Date:	Mon, 18 Jan 2010 11:15:35 GMT	
	Server:	gws	
	Content-Length:	218	
	X-XSS-Protection:	0	
	Connection:	close	
	*/
	private String HTTPStatusCode, Date, Server, ContentType, ContentLength, X_XSS_Protection, Connection;
	//private String Location, CacheControl, SetCookie, X_XSS_Protection;
	
	public boolean setHTTPResponseHeader(String res, resourceHandler.ServerSideScripter objSvrSidScr){
		ResponseRecordUtils RRU = new ResponseRecordUtils();
		try{
			this.HTTPStatusCode = "HTTP/1.1 " + RRU.getStatusCode(res);//"HTTP/0.9 200 OK";
			this.ContentType = "Content-Type: " + RRU.getContentTyp(res);//text/html; charset=UTF-8";
			this.ContentLength = "Content-Length: " + RRU.getContentLen(res, objSvrSidScr);//1350";
			this.Connection= RRU.getConnectionTyp(res);
			this.Date = "Date: " + RRU.getServerDate();
			this.Server = "Server: " + RRU.getServerSig();
			this.X_XSS_Protection = "X-XSS-Protection: " + RRU.getXXSSProtection();//0";
			return true;	
		}catch (Exception e){
			System.out.println("\n\nException Raised: Problems in Setting Response Header.\n\nStackTrace::");
			e.printStackTrace();
			return false;
		}
	}
	
	public void sendHTTPResponseHeader(DataOutputStream ds){
		try {
			ds.writeBytes(this.HTTPStatusCode + "\n");
			if(this.ContentType!=null)
				ds.writeBytes(this.ContentType + "\n");
			if(this.ContentLength!=null)
				ds.writeBytes(this.ContentLength + "\n");
			if(this.Date!=null)
				ds.writeBytes(this.Date + "\n");
			if(this.Server!=null)
				ds.writeBytes(this.Server + "\n");
			if(this.X_XSS_Protection!=null)
				ds.writeBytes(this.X_XSS_Protection + "\n");
			if(this.Connection!=null)
				ds.writeBytes(this.Connection + "\n");				
			ds.writeBytes("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
