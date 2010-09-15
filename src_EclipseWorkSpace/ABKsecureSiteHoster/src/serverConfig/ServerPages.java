package serverConfig;

public class ServerPages {

	private String WEBRoot = "<html><head><title>ABK SiteHoster</title></head><body><h4>ABK SiteHoster HTTPv0.9" +
	"</h4><div style=\"margin-right:auto,0;margin:left:auto,0;\"><fieldset>" +
	"<legend>Settings Information</legend><div>ABK's SiteHoster is aLEHNS &lt; ABK's Lightweight HTTP(0.9) Extensible Network Server &gt;</div></fieldset>Try Again :)" +
	"<br><br>[] Copy all your Web Documents to ROOT folder, most probably would wok fine ;)" +
	"<br><br>still in testing phases..."+
	"</div></body></html>";
	
	private String WEBErr = "<html><head><title>HTTP Error#404</title></head><body><h4>Check URL! This link appears to be broken." +
	"</h4><div style=\"margin-right:auto,0;margin:left:auto,0;\"><fieldset>" +
	"<legend>Resource Not Found</legend><div>ABK's SiteHoster is aLEHNS &lt; ABK's Lightweight HTTP(0.9) Extensible Network Server &gt;</div></fieldset>Try Again :)" +
	"</div></body></html>";
	
	public void setWebRoot(String wRoot){
		WEBRoot = wRoot;
	}
	
	public void setWebErr(String wErr){
		WEBErr = wErr;
	}
	
	public String getWebRoot(){
		return WEBRoot;
	}
	
	public String getWebErr(){
		return WEBErr;
	}
}
