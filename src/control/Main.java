package control;


import boundry.ViewLogic;
import entity.Block;
import entity.Miner;
import entity.Transaction;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Miner user = null;  

	public static void main(String[] args) {
		TransactionLogic.updateBlock("block2", 30);
		user = new Miner("miner2");
		
			//Communication.receiveJSON();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
	}

}
