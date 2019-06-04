package controllers;

import java.io.IOException;
import java.util.ArrayList;
import client.UserClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

// A class that controls the client GUI and actions

public class ClientController{
	
	// Class variables 
	public static int DEFAULT_PORT = 5555;
	UserClient userClient;
	ObservableList <String> cityList;
	
	@FXML
	Button changeVerBtn;
	
	@FXML
	TextField newVersiontxt;
	
	@FXML
	Label yesNumber;
	
	@FXML
	Label notNumber;
	
	@FXML
	Label IPfail;
	
	@FXML
	Label IPok;
	
	@FXML
	private ComboBox <String> cities;

	@FXML
	TextField serverIPtxt;
	
	@FXML
	Button connectToServerBtn;
	
	// Class methods

	//runs when "connect to server" button is clicked. activate the connection to server.
	public void connectToServer(ActionEvent event)
	{
			try { 
				userClient = new UserClient(serverIPtxt.getText(), DEFAULT_PORT, this); /*creates new client (that extends AbstractClient)
																						sends him the IP that was inserted to serverIPtxt textField
																						the default port and "this" so that the client could send back
																						feedback or data to the controller*/ 
				IPok.setVisible(true); //set the labels according to successful/unsuccessful connection
				IPfail.setVisible(false);
				serverIPtxt.setDisable(true); //disables the option to change IP of server once the connection was made
			} catch (IOException e) {
				IPfail.setVisible(true);
				return; //quits the method if failed to connect
			} 
		QueryCommunicator queryCom=new QueryCommunicator("SELECT * FROM city;"); //creates query to receive city names
		userClient.handleMessageFromClientUI(queryCom); //sends the query to the client so he will send it to the server
	
		changeVerBtn.setDisable(false); //enable the cities and change version button after sending request to the server
		newVersiontxt.setDisable(false);
		cities.setDisable(false);
	}
	
	//runs when "editVersion" button is clicked. 
	public void editVersion(ActionEvent event) {
		Boolean isNumber = true;
		notNumber.setVisible(false);
		yesNumber.setVisible(false);

		for (int i = 0; i < newVersiontxt.getLength(); i++) { //check if the inserted text is number
			if (!(Character.isDigit(newVersiontxt.getText().charAt(i)))) {
				isNumber = false;
				break;
			}
		}
		if ((isNumber == false)||(newVersiontxt.getLength()==0)) //not number or empty input
			notNumber.setVisible(true);
		else { //create query from selected city name and inserted version
			QueryCommunicator queryCom=new QueryCommunicator("UPDATE city SET version ="+newVersiontxt.getText()+ " WHERE Name = \""+cities.getValue()+"\"");
			userClient.handleMessageFromClientUI(queryCom); //sends the query to the client so he will send it to the server
			yesNumber.setVisible(true); 
		}
	}
	
	//gets list of city names and sets them to the comboBox
	public void setCities(ArrayList <String> cityList)
	{
		ObservableList<String> list = FXCollections.observableArrayList(); //initialize the ObservableList
		for(String name: cityList) //transfer from ArrayList to ObservableList
			list.add(name);
		cities.setItems(list);
	}
	//***********
}
