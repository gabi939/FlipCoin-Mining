package control;


import java.util.Date;

import Exceptions.RaffleException;
import Utils.RiddleStatus;
import boundry.SignUpLoginController;
import boundry.ViewLogic;
import entity.Benefit;
import entity.Company;
import entity.Miner;
import entity.Raffle;
import entity.Riddle;
import entity.RiddleLevel;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	

	public static void main(String[] args) {
		Sys.system = new Sys();
		
		Raffle raf = new Raffle(1);
		Benefit ben = new Benefit(1);
		Riddle rid = new Riddle(1);
		
		
		
		try {
			raf.setMaxMiners(1);
			raf.setNumBenefits(1);
			raf.setNumWinners(1);
			raf.setRaffleDate(new Date());
		} catch (RaffleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		ben.setDescription("set works");

		
		rid.setDescription("set works");
		rid.setLevel("Hard");
		rid.setPublishDate(new Date());
		rid.setSolutionFinishTime(new Date());
		rid.setStatus(RiddleStatus.irrelevent);
		
		
		//launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
	}

}
