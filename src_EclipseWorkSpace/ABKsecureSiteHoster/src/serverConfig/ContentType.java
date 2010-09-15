package serverConfig;

public class ContentType {

	public String getContentType(String fileExt) {
		String typ = "*/*";
		
		//TEXT
		if(fileExt.equalsIgnoreCase("html"))
			typ = "text/html";
		else if(fileExt.equalsIgnoreCase("htm"))
			typ = "text/html";
		else if(fileExt.equalsIgnoreCase("txt"))
			typ = "text/plain";
		else if(fileExt.equalsIgnoreCase("xml"))
			typ = "text/xml";
		else if(fileExt.equalsIgnoreCase("csv"))
			typ = "text/csv";
		else if(fileExt.equalsIgnoreCase("css"))
			typ = "text/css";
		else if(fileExt.equalsIgnoreCase("js"))
			typ = "text/javascript";
		//ServerSideScript
		else if(fileExt.equalsIgnoreCase("php"))
			typ = "text/html";
		else if(fileExt.equalsIgnoreCase("py"))
			typ = "text/html";
		else if(fileExt.equalsIgnoreCase("rb"))
			typ = "text/html";
		else if(fileExt.equalsIgnoreCase("sh"))
			typ = "text/html";
		else if(fileExt.equalsIgnoreCase("bat"))
			typ = "text/html";
		else if(fileExt.equalsIgnoreCase("exe"))
			typ = "text/html";
		//IMAGE
		else if(fileExt.equalsIgnoreCase("jpeg"))
			typ = "image/jpeg";
		else if(fileExt.equalsIgnoreCase("jpg"))
			typ = "image/jpg";
		else if(fileExt.equalsIgnoreCase("gif"))
			typ = "image/gif";
		else if(fileExt.equalsIgnoreCase("svg"))
			typ = "image/svg+xml";
		else if(fileExt.equalsIgnoreCase("tiff"))
			typ = "image/tiff";
		else if(fileExt.equalsIgnoreCase("ico"))
			typ = "image/vnd.microsoft.icon";
		else if(fileExt.equalsIgnoreCase("png"))
			typ = "image/png";
		//APP-SPECIFIC
		else if(fileExt.equalsIgnoreCase("ogg"))
			typ = "application/ogg";
		else if(fileExt.equalsIgnoreCase("ogx"))
			typ = "application/ogg";
		else if(fileExt.equalsIgnoreCase("pdf"))
			typ = "application/pdf";
		else if(fileExt.equalsIgnoreCase("xhtml"))
			typ = "application/xhtml+xml";
		else if(fileExt.equalsIgnoreCase("dtd"))
			typ = "application/xml-dtd";
		else if(fileExt.equalsIgnoreCase("json"))
			typ = "application/json";
		else if(fileExt.equalsIgnoreCase("zip"))
			typ = "application/zip";
		//AUDIO
		else if(fileExt.equalsIgnoreCase("mp3"))
			typ = "audio/mpeg";
		else if(fileExt.equalsIgnoreCase("oga"))
			typ = "audio/ogg";
		else if(fileExt.equalsIgnoreCase("spx"))
			typ = "audio/ogg";
		else if(fileExt.equalsIgnoreCase("flac"))
			typ = "audio/x-flac";
		else if(fileExt.equalsIgnoreCase("wma"))
			typ = "audio/x-ms-wma";
		else if(fileExt.equalsIgnoreCase("rm"))
			typ = "audio/vnd.rn-realaudio";
		else if(fileExt.equalsIgnoreCase("wav"))
			typ = "audio/vnd.wave";
		//VIDEO
		else if(fileExt.equalsIgnoreCase("ogv"))
			typ = "video/ogg";
		else if(fileExt.equalsIgnoreCase("mpg"))
			typ = "video/mpg";
		else if(fileExt.equalsIgnoreCase("mpeg"))
			typ = "video/mpg";
		else if(fileExt.equalsIgnoreCase("mp4"))
			typ = "video/mp4";
		else if(fileExt.equalsIgnoreCase("mov"))
			typ = "video/quicktime";
		else if(fileExt.equalsIgnoreCase("3gp"))
			typ = "video/quicktime";
		else if(fileExt.equalsIgnoreCase("wmv"))
			typ = "video/x-ms-wmv";
		
		return typ;
	}

}
