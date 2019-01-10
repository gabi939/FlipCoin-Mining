package control;


import boundry.ViewLogic;
import entity.Block;
import entity.Transaction;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		Communication.receiveJSON();
		//launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
	}

}
