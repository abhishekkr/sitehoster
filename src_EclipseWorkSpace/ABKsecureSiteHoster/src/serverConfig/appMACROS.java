package serverConfig;

public interface appMACROS {	
	//public static String PLATFORM = "WIN32"; //"POSIX"//"MAC"
	//public static String MACROS_WEBDOCS = "E:\\-=ABK=-\\EclipseWorkspace\\ABK_jNet_HTTPServer\\trialWebDocs";
	//public static String MACROS_404 = "E:\\-=ABK=-\\EclipseWorkspace\\ABK_jNet_HTTPServer\\trialWebDocs\\HTTP404.html";
	public static String TEMP_PHPCLI = "C:\\_AbhishekKr\\-=ABK=-\\Dev\\php5.2.13\\php.exe";
	
	public static String SERVER = "ABK_SiteHoster";
	
	public static enum TypeOfRequest{
		 OPTIONS,
		 GET,
		 HEAD,
		 POST,
		 PUT,
		 DELETE,
		 TRACE,
		 CONNECT 
	};
}

// getContentLen is responsible in begin to find ExtensionORLocation wise Res. data

//then resReader to send it