package requestHandler;

import java.util.StringTokenizer;

public class RequestRecordUtils {
	public String getRequest(String msg){
		StringTokenizer st = new StringTokenizer(msg);
	    for (int tokIndex=0; st.hasMoreTokens(); tokIndex++) {
	    	String currTok=st.nextToken();
	        if(tokIndex==0){
	        	return currTok;
	        }
	    }
		return "\0";
	}
	public String getResource(String msg){
		StringTokenizer st = new StringTokenizer(msg);
	    for (int tokIndex=0; st.hasMoreTokens(); tokIndex++) {
	    	String currTok=st.nextToken();
	    	if(tokIndex==1){
	        	return currTok;
	        }
	    }
		return "\0";
	}
	public String getProtocol(String msg){
		StringTokenizer st = new StringTokenizer(msg);
	    for (int tokIndex=0; st.hasMoreTokens(); tokIndex++) {
	    	String currTok=st.nextToken();
	    	if(tokIndex==2){
				StringTokenizer stTmp = new StringTokenizer(currTok,"/");
				return stTmp.nextToken();
	        }
	    }
		return "\0";
	}
	public String getProtocolVersion(String msg){
		StringTokenizer st = new StringTokenizer(msg);
	    for (int tokIndex=0; st.hasMoreTokens(); tokIndex++) {
	    	String currTok=st.nextToken();
	    	if(tokIndex==2){
				StringTokenizer stTmp = new StringTokenizer(currTok,"/");
				stTmp.nextToken();
				return stTmp.nextToken();
	        }
	    }
		return "\0";
	}
	public String getConnectionType(String msg) {
		StringTokenizer st = new StringTokenizer(msg);
	    for (int tokIndex=0; st.hasMoreTokens(); tokIndex++) {
	    	String currTok=st.nextToken();
	        if(tokIndex==1){
	        	return currTok;
	        }
	    }
		return "\0";
	}
}

/** 
 * HEAD
Asks for the response identical to the one that would correspond to a GET request, but without the response body. This is useful for retrieving meta-information written in response headers, without having to transport the entire content.
GET
Requests a representation of the specified resource. Note that GET should not be used for operations that cause side-effects, such as using it for taking actions in web applications. One reason for this is that GET may be used arbitrarily by robots or crawlers, which should not need to consider the side effects that a request should cause. See safe methods below.
POST
Submits data to be processed (e.g., from an HTML form) to the identified resource. The data is included in the body of the request. This may result in the creation of a new resource or the updates of existing resources or both.
PUT
Uploads a representation of the specified resource.
DELETE
Deletes the specified resource.
TRACE
Echoes back the received request, so that a client can see what intermediate servers are adding or changing in the request.
OPTIONS
Returns the HTTP methods that the server supports for specified URL. This can be used to check the functionality of a web server by requesting '*' instead of a specific resource.
CONNECT
Converts the request connection to a transparent TCP/IP tunnel, usually to facilitate SSL-encrypted communication (HTTPS) through an unencrypted HTTP proxy.[5]
 */