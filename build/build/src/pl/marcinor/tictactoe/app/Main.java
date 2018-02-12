package pl.marcinor.tictactoe.app;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		final String appName = "TicTacToe v1.0";
		try {
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource(
					"/pl/marcinor/tictactoe/view/MainPane.fxml"));
			Scene scene = new Scene(parent);
			primaryStage.setTitle(appName);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
