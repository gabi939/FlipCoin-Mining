package control;


import boundry.ViewLogic;
import entity.Miner;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	

	public static void main(String[] args) {
		Sys.system = new Sys();
		
		//System.out.println(RaffleLogic.getAllRaffles());
	
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
	}

}
