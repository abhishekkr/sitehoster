package httpServer;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import requestHandler.RequestRecord;
import responseHandler.Responder;

/**
 * uses TCP Sockets
 * server that echoes back back client's messages
 * at end of dialog sends messages indicating no. of messages received
 */

/**
 * @author AbhishekKr
 * @category N/W Programming
 */
public class HTTPServer implements Runnable{

	private Socket link;
	
	public HTTPServer(Socket lnk) {
		link = lnk;
	}

	@Override
	public void run() {
		handleClient();
	}
	
	/**
	 * handles client socket creation and listening/responding section
	 */
	private void handleClient() {
		//Socket link = null;
		RequestRecord reqRec = new RequestRecord();
		Responder respondR = new Responder();
		
		try {
			// STEP 1 : accepting client request in client socket
			//link = servSock.accept(); //-----> already done in *ThreadExecutor*
			
			// STEP 2 : creating i/o stream for socket
			Scanner input = new Scanner(link.getInputStream());
			do{
				//PrintWriter output = new PrintWriter(link.getOutputStream(),true);
				//DataOutputStream ds = new DataOutputStream(link.getOutputStream());
				int numMsg = 0;
				String msg = input.nextLine();
				
				//to write all requests to a File
				FileOutputStream reqFile = new FileOutputStream("reqFile.txt");
				DataOutputStream dat = new DataOutputStream(reqFile);
				
				// STEP 4 : listening iteratively till close string send
				while(msg.length()>0){
					numMsg++;
					dat.writeChars(msg + "\n");
					System.out.println("\nNew Message Received");
					if(reqRec.setRecord(msg)==false)
						System.out.println("\n-----\nMsg#"+numMsg+": "+msg+":Error with Request Header Parsing\n-----\n");
					else
						System.out.println("Msg#" + numMsg + ": " + msg);
					msg = input.nextLine();
				};
				dat.writeChars("\n-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n");
				dat.close();
				reqFile.close();
				System.out.println("---newEST : " + reqRec.getResource());
				
				System.out.println("\n==========Send HTTP Response for Get Request of\n"+reqRec.getResource()+"\n***********\n");
				if(respondR.sendHTTPResponseGET(reqRec.getResource(), link)==true)//RES, ds)==true)
					System.out.println("-----------Resource Read");
				System.out.println("Total Messages Transferred: " + numMsg);
				//link.close();
				if(reqRec.getConnection().equalsIgnoreCase("Keep-Alive")==true && respondR.isResourceExisting(reqRec.getResource())==true)
					link.setKeepAlive(true);
				else
					break;
			}while(true);
			System.out.print("Request Link Over as Connection: " + reqRec.getConnection());
		} catch (IOException e) {
			if(link.isClosed())
				System.out.print("\n\nCritical(report it to developer):: link closed at exception\n\n");
			System.out.println("Error in listening.\n {Error may be here or with RespondR}\nTraceString: ");
			e.printStackTrace();
		} finally {
			// STEP 5 : closing the connection
			System.out.println("\nClosing Connection\n");
			try {				
				link.close();
			} catch (IOException e) {
				System.out.println("Unable to disconnect.\nTraceString: ");
				e.printStackTrace();
				System.exit(1);
			}
		}/*ends :: try...catch...finally*/
		
	}/*ends :: method handleClient()*/
	
}/*ends :: class HTTPServer*/
