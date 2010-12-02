package getSecured;

import java.util.ArrayList;

public class XSSDeferJSBody {

	public static ArrayList<String> tellDefer(String headContent) {
		ArrayList<String> retVal =  new ArrayList<String>(2);
		boolean isLT = false;
		boolean isJustAfterLT = false;
		boolean isQuote = false;
		boolean isSQuote = false;
		boolean isDQuote = false;
		boolean isSlash = false;
		boolean isTagOpen = false;
		
		boolean isDefer = false;

		char prevChr = ' ';
		char currChr = ' ';
		
		String tmpHD = headContent;
		int headLen = tmpHD.length();
		
		String hdCnt = "";
		String bdCnt = "";
		
		String hdrRefresh="";
		String hdrWord="";
		String tagName="";
		System.out.println("head : [ " + tmpHD + " ]\n");
		for(int idx=0; idx<headLen; idx++){
			//marking FLAG making checks aware of String usage of special chars
			currChr = tmpHD.charAt(idx);
			if(currChr=='\'' && isDQuote==false && prevChr!='\\'){
				isSQuote = !(isSQuote);
				isQuote = isSQuote;
			}
			else if(currChr=='"' && isSQuote==false && prevChr!='\\'){
				isDQuote = !(isDQuote);
				isQuote = isDQuote;
			}				
			hdrRefresh += Character.toString(currChr);
			//checking if word formed till now is DEFER
			boolean toChkWord = (currChr=='=' || currChr==' ' || currChr=='>' || currChr=='/');
			if(toChkWord==true && isLT==true && tagName.equalsIgnoreCase("script") && hdrWord.equalsIgnoreCase("defer")){
				//System.out.println("w : [ " + hdrWord + " ]");
				isDefer = true;
			}
			
			if(isQuote==false){
				//marking boolean char-based flags
				if(currChr=='<' && isLT==false){
					isLT = true;
					isJustAfterLT = true;
					tagName="";
					isTagOpen = true;
				}
				else if(isLT==true && currChr=='>'){
					isLT = false;
					if(isSlash==true){
						isTagOpen = false;
						if(isDefer == true){
							//System.out.println("deferred : [ " + hdrRefresh + " ]");
							bdCnt += hdrRefresh;
							isDefer=false;
						}
						else{
							hdCnt += hdrRefresh;
						}
						hdrRefresh = "";
					}
					isSlash = false;
				}
				else if(isLT==true && currChr=='/'){
					isSlash = true;
				}
				
				if(isTagOpen == true){
					if(isJustAfterLT==true){
						if(hdrWord.trim()!="" && ( isWhiteSpace(currChr)||(currChr=='>') )){
							isJustAfterLT=false;
							tagName=hdrWord.trim();
							//System.out.println("tag : [ " + tagName + " ]");
						}
					}
				}
				
				//calculating
			}

			if(toChkWord==true  || currChr=='<'){
				hdrWord = "";
			}
			else{
				hdrWord += Character.toString(currChr);
			}
						
			//it should be last
			prevChr = currChr;
		}		
		System.out.println("head : [ " + hdCnt + " ]");
		System.out.println("deferredToBody : [ " + bdCnt + " ]");
		retVal.add(hdCnt);
		retVal.add(bdCnt);
		return retVal;
	}

	private static boolean isWhiteSpace(char chr) {
		if(chr==' ' || chr=='\t' || chr=='\n')
			return true;
		return false;
	}

}
