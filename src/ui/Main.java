package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	private PlayerManagerGUI gui;
	
	public Main() {
		gui = new PlayerManagerGUI();
	}
	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
        fxmlloader.setController(gui);
        Parent root = fxmlloader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        gui.initProgram();
	}
}
