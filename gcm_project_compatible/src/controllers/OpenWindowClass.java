package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
//class that switches between windows
public class OpenWindowClass {
	
	//method that get the event and information about the window that needs to be opened
	//hides the current window and opens the wanted window
	public void openWindow(ActionEvent event, String FXMLname, String cssName) throws IOException
	{
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/application/"+FXMLname+".fxml").openStream()); //loading FXML file
		Scene scene = new Scene(root);			
		scene.getStylesheets().add(getClass().getResource("/application/"+cssName+".css").toExternalForm()); //get relevant css file
		primaryStage.setScene(scene);		
		primaryStage.show();	//open window
	}
}
