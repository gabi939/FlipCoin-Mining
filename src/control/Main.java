package control;


import java.util.Date;

import Utils.RiddleStatus;
import boundry.SignUpLoginController;
import boundry.ViewLogic;
import entity.Company;
import entity.Miner;
import entity.Riddle;
import entity.RiddleLevel;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	

	public static void main(String[] args) {
		Sys.system = new Sys();
		
		//RiddleLogic.irreleventCheck();
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
	}

}
