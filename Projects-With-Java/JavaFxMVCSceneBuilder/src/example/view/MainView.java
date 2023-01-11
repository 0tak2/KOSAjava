package example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("myView.fxml"));
		root = loader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}
