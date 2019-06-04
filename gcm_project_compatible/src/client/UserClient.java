package client;

import java.io.IOException;
import java.util.ArrayList;

import controllers.ClientController;
import ocsf.client.*;

//the class the connects between the controller and the server
public class UserClient extends AbstractClient {
	// Class variables 
	ClientController clientCon; /*saves connection to the controller that created it will be 
									able to send him back feedback or data from the server*/
	
	// Class methods 
	public UserClient(String host, int port, ClientController clientCon) throws IOException
	{
		super(host,port);
		this.clientCon=clientCon;
		openConnection(); //opens the connection to the server (AbstractClient method)
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		clientCon.setCities((ArrayList <String>)msg); 	//send the cities that the server got from the DB to the controller
	}
	
	
	 public void handleMessageFromClientUI(Object message)  ///gets message from the controller and sends it to the server
	  {
	    try
	    {
	    	sendToServer(message);
	    }
	    catch(IOException e)
	    {
	    	quit();
	    }
	  }
	 
	  public void quit() //closes connection to the server
	  {
	    try
	    {
	      closeConnection();
	    }
	    catch(IOException e) {}
	    System.exit(0);
	  }
}
