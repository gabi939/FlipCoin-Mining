package control;


import boundry.ViewLogic;
import entity.Miner;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Miner user = null;  

	public static void main(String[] args) {

		user = new Miner("miner2");
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
	}

}
