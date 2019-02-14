package control;


import java.io.UnsupportedEncodingException;

import boundry.ViewLogic;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	

	public static void main(String[] args) throws UnsupportedEncodingException  {
		Sys.system = new Sys();
		
		//Communication.receiveRiddles();
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
	}

}
