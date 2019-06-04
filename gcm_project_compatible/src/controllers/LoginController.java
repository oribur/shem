package controllers;

import client.UserClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
	
	public static int DEFAULT_PORT = 5555;
	UserClient userClient;
	
	@FXML
	TextField userNametxt;
	
	@FXML
	TextField passwordtxt;
	
	//maybe general class (openwindowclass) that will do the connection to the server and send the queries/ all the controllers will extend it
	
	public void login(ActionEvent event)
	{
		//CONNECTION TO SERVER???? 
		//userClient = new UserClient(serverIPtxt.getText(), DEFAULT_PORT, this);
		QueryCommunicator queryCom = new QueryCommunicator("SELECT * FROM user WHERE name=\""+userNametxt.getText()+"\" AND password=\""+passwordtxt.getText()+"\";");
		userClient.handleMessageFromClientUI(queryCom);
		//send the query to the server using userclient
	}
}
