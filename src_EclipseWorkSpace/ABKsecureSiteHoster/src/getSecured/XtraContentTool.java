package getSecured;

public class XtraContentTool {
	public static String escapeScript(String dataToEscape){
		String dataToReturn=null;
		String backSlash=((char)92)+"";	//means	\
		String xBackSlash=((char)92)+""+((char)92);	//means	\\
		String singleQuote=((char)39)+"";	//means	'
		String xSingleQuote=((char)92)+""+((char)39);	//means	\'
		String dblQuote=((char)34)+"";	//means	"
		String xDblQuote=((char)92)+""+((char)34);	//means	\"
		String fwdSlash=((char)47)+"";		//means	/
		String xFwdSlash=((char)92)+""+((char)47);		//means	\/
		dataToReturn=dataToEscape.replace(backSlash, xBackSlash); //for {/}
		dataToReturn=dataToReturn.replace(singleQuote, xSingleQuote); //for {'}
		dataToReturn=dataToReturn.replace(dblQuote, xDblQuote); //for {"}
		dataToReturn=dataToReturn.replace(fwdSlash, xFwdSlash); //for {\}
		return dataToReturn;
	}
}
