package getSecured;

import java.util.ArrayList;

public class PatchForXSS {
	private String ML;
	private boolean isHead=false;
	
	public String getML(String realData){
		ML=realData;
		if(doesItNeedPatch1(realData)==true)
			real2ML();
		else
			ML=applyEventCodePatch2(ML);
		return ML;
	}

	private boolean real2ML(){
		String tmpML = null;
		ArrayList<String> headDef =  new ArrayList<String>(2);
		int headStart, headEnd, bodyStart, bodyEnd;
		tmpML = "<html>";
		if(isHead==true){
			headStart = ML.toLowerCase().indexOf("<head>")+6;
			headEnd = ML.toLowerCase().indexOf("</head>");
			String headContent = ML.substring(headStart, headEnd);
			headDef = XSSDeferJSBody.tellDefer(headContent);
			tmpML = tmpML + "<HEAD>" + headDef.get(0) + "</HEAD>";
		}
		String bodyArgs="";
		int bodyIStart = ML.toLowerCase().indexOf("<body");
		bodyStart = ML.toLowerCase().indexOf(">", bodyIStart)+1;
		if(bodyIStart+6!=bodyStart && bodyIStart+6<bodyStart){
			bodyArgs = ML.substring(bodyIStart+6,bodyStart-1);
		}
		bodyEnd = ML.toLowerCase().indexOf("</body>");
		String bodyML = applyEventCodePatch2(ML.substring(bodyStart, bodyEnd)); 
		bodyML = "<script type='text/javascript'>x=document.getElementsByTagName(\"BODY\");x[0].innerHTML = \"" 
			+ XtraContentTool.escapeScript(bodyML) 
			+ "\"</script>";
		tmpML = tmpML + "<BD><BODY "+ bodyArgs +">" + bodyML + headDef.get(1) + "</BODY></BD>";
		tmpML = tmpML + "</html>";
		ML = tmpML;
		System.out.println("+++++requires XSS Patch : ML");
		return false;
	}
	
	private boolean doesItNeedPatch1(String realData) {
		String realDataTmp = realData.toLowerCase();
		if(realDataTmp.contains("<head>") && realDataTmp.contains("</head>")){
			isHead = true;
		}
		if( realDataTmp.contains("<body") && realDataTmp.contains("</body>")){
			System.out.println("+++++requires XSS Patch");
			return true;
		}
		return false;
	}
	
	private String applyEventCodePatch2(String realData) {
		String realDataTmp = realData.replace("javascript", "javascript<span/>");
		return realDataTmp;
	}
}
