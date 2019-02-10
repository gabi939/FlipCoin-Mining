package control;


import boundry.SignUpLoginController;
import boundry.ViewLogic;
import entity.Company;
import entity.Miner;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	

	public static void main(String[] args) {
		Sys.system = new Sys();
		
		//System.out.println(MinerLogic.getAllCompanys());
		
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
	}

}
