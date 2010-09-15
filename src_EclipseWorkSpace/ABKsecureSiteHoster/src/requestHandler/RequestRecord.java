package requestHandler;

public class RequestRecord {
	//Msg#1: GET / HTTP/1.1
	//Msg#2: Host: 127.0.0.1:1234
	//Msg#3: User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.0.14) Gecko/2009082707 Firefox/3.0.14 (.NET CLR 3.5.30729) FirePHP/0.3
	//Msg#4: Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
	//Msg#5: Accept-Language: en-us,en;q=0.5
	//Msg#6: Accept-Encoding: gzip,deflate
	//Msg#7: Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7
	//Msg#8: Keep-Alive: 300
	//Msg#9: Connection: keep-alive
	//Msg#10: Cache-Control: max-age=0
	String Request,Resource,Protocol,ProtocolVersion;
	String Host,Port,UserAgent,Accept,AcceptLanguage,AcceptEncoding;
	String AcceptCharset,KeepAlive,CacheControl;
	String Connection = "Keep-Alive";
	
	public static String GetVariables="",SessionVariables;
	
	public String getResource(){
		return this.Resource;
	}
	
	public String getConnection(){
		return this.Connection;
	}

	
	public boolean setRecord(String msg){
		RequestRecordUtils ReqUtil = new RequestRecordUtils();
		
		if(msg.trim().startsWith("Host:")){
			Host = msg;
			return true;
		}
		else if(msg.trim().startsWith("User-Agent:")){
			return true;
		}
		else if(msg.trim().startsWith("Accept:")){
			return true;
		}
		else if(msg.trim().startsWith("Accept-Language:")){
			return true;
		}
		else if(msg.trim().startsWith("Accept-Encoding:")){
			return true;
		}
		else if(msg.trim().startsWith("Accept-Charset:")){
			return true;
		}
		else if(msg.trim().startsWith("Keep-Alive:")){
			return true;
		}
		else if(msg.trim().startsWith("Connection:")){
			Connection = ReqUtil.getConnectionType(msg);
			return true;
		}
		else if(msg.trim().startsWith("Cache-Control:")){
			return true;
		}
		else if(msg.trim().startsWith("Range:")){
			return true;
		}
		else if(msg.trim().startsWith("Referer:")){
			return true;
		}
		else if(msg.trim().startsWith("GET") || msg.trim().startsWith("HEAD") || msg.trim().startsWith("POST") || 
				msg.trim().startsWith("PUT") || msg.trim().startsWith("DELETE") ||
				msg.trim().startsWith("TRACE") || msg.trim().startsWith("OPTIONS") || 
				msg.trim().startsWith("CONNECT")){
			Request = ReqUtil.getRequest(msg);
			parseURLnGET(ReqUtil.getResource(msg));
			Protocol = ReqUtil.getProtocol(msg);
			ProtocolVersion = ReqUtil.getProtocolVersion(msg);
			return true;
		}
		return false;
	}
	
	private void parseURLnGET(String res){
		if (res.contains("?")==false){
			Resource = res;
			return;
		}
		int Q = res.indexOf("?");
		Resource = res.substring(0,Q);
		GetVariables = res.substring(Q+1);		
	}
}
