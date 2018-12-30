package control;


import Utils.Type;
import boundry.ViewLogic;
import entity.Block;
import entity.Transaction;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		TransactionLogic.addToBlock(new Block("block3"), new Transaction("PAY7",2,Type.Pay,5,null));
	//	ViewLogic.initUI();
	}

}
