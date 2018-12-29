package control;

import java.util.Date;

import boundry.ViewLogic;
import entity.Block;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		TransactionLogic.addBlock(new Block("block9", "block8", "miner1", new Date(), 5));
		ViewLogic.initUI();
	}

}
