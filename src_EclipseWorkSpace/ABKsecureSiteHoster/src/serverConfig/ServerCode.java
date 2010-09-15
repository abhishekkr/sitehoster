package serverConfig;

public class ServerCode {
	String[] code1 = new String[2];
	String[] code2 = new String[7];
	String[] code3 = new String[8];
	String[] code4 = new String[18];
	String[] code5 = new String[6];
	
	public ServerCode(){
		/*100-199 INFORMATIONAL Code List*/
		code1[0] = "continue";
		code1[1] = "switching protocols";
		
		/*200-299 SUCCESSFUL Code List*/
		code2[0] = "OK";
		code2[1] = "Created";
		code2[2] = "Accepted";
		code2[3] = "Non-Authoritative Information";
		code2[4] = "No Content";
		code2[5] = "Reset Content";
		code2[6] = "Partial Content";
		
		/*300-399 REDIRECTION Code List*/
		code3[0]= "Multiple Choices";
		code3[1]= "Moved Permanently";
		code3[2]= "Found";
		code3[3]= "See Other";
		code3[4]= "Not Modified";
		code3[5]= "Use Proxy";
		code3[6]= "(Unused)";
		code3[7]= "Temporary Redirect";
		   
		/*400-499 CLIENT-ERROR Code List*/
		code4[0] = "Bad Request";
		code4[1] = "Unauthorized";
		code4[2] = "Payment Required";
		code4[3] = "Forbidden";
		code4[4] = "Not Found";
		code4[5] = "Method Not Allowed";
		code4[6] = "Not Acceptable";
		code4[7] = "Proxy Authentication Required";
		code4[8] = "Request Timeout";
		code4[9] = "Conflict";
		code4[10] = "Gone";
		code4[11] = "Length Required";
		code4[12] = "Precondition Failed";
		code4[13] = "Request Entity Too Large";
		code4[14] = "Request-URI Too Long";
		code4[15] = "Unsupported Media Type";
		code4[16] = "Requested Range Not Satisfiable";
		code4[17] = "Expectation Failed";
		
		/*500-599 SERVER-ERROR Code List*/
		code5[0] = "Internal Server Error";
		code5[1] = "Not Implemented";
		code5[2] = "Bad Gateway";
		code5[3] = "Service Unavailable";
		code5[4] = "Gateway Timeout";
		code5[5] = "HTTP Version Not Supported";
	}
	
	public String getCodeString(int Code){
		System.out.println("))))))))))))))))))))))Code: " + Code);
		if(Code>99 && Code<200){
			if(code1[Code-100]!=null)
				return code1[Code-100];
		}
		else if(Code>199 && Code<300){
			if(code2[Code-200]!=null)
				return code2[Code-200];			
		}
		else if(Code>299 && Code<400){
			if(code3[Code-300]!=null)
				return code3[Code-300];			
		}
		else if(Code>399 && Code<500){
			if(code4[Code-400]!=null)
				return code4[Code-400];			
		}
		else if(Code>499 && Code<600){
			if(code5[Code-500]!=null)
				return code5[Code-500];			
		}
		//System.out.println("UnHnadled Code");		
		return "Unknown Code";
	}
}
