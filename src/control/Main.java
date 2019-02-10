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
		
		Riddle riddle = new  Riddle(5);
		
		riddle.setLevel("Complexed");
		riddle.setDescription("I love youssi");
		riddle.setPublishDate(new Date(1993,2,1));
		riddle.setSolutionFinishTime(new Date(1995,2,1));
		riddle.setStatus(RiddleStatus.solved);
		
		
		//launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
	}

}
