package control;


import boundry.ViewLogic;
import entity.Block;
import entity.Transaction;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
	
		
		TransactionLogic.addToBlock(new Block("block2"), new Transaction("PAY7"));
		
		//	Communication.receiveJSON();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
	}

}
