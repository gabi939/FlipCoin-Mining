package boundry;

import java.io.IOException;
import java.net.URL;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class ViewLogic {

	public static void initUI() {
//		Stage stage = new Stage();
//	//	newWindow(ViewLogic.class.getResource("2.fxml"), stage, false,"Transaction Test",false);
//		newWindow(ViewLogic.class.getResource("1.fxml"), stage, false,"Transaction Mining",false);
//		//blocksWindow();
		LoginScreen();
	}
	
	protected static void mainMenu() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("MinerMainMenu.fxml"), stage, false,"FlipCoinMining",false);
		
	}
	
	
	
	
	/**
	 * login screen 
	 */
	protected static void LoginScreen() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("SignUpLogin.fxml"), stage, false,"FlipCoinMining",false);
	
	
	
	}
	

	protected static void RaffleScreen() {
		Stage stage = new Stage();
		newWindow(ViewLogic.class.getResource("MinerRaffleGame.fxml"), stage, false,"FlipCoinMining",false);
	
	
	
	}
	


	
	
	/**
	 * this method manages the window properties
	 * 
	 * @param fxmlLocation
	 * @param stage
	 * @param prefWidth
	 * @param prefHeight
	 * @param minWidth
	 * @param minHeight
	 * @param maxWidth
	 * @param maxHeight
	 * @param resizable
	 * @param title
	 * @param waitFor
	 */
	protected static void newWindow(URL fxmlLocation, Stage stage, boolean resizable, String title, boolean waitFor) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					FXMLLoader loader = new FXMLLoader(fxmlLocation);
					Parent root = loader.load();
					Scene scene;
					scene = new Scene(root);
					scene.getStylesheets().add("menuStyles.css");
					stage.setScene(scene);
					stage.setResizable(resizable);

					if (title != null && !title.isEmpty() && !title.trim().isEmpty())
						stage.setTitle(title);

					if (waitFor)
						stage.initModality(Modality.APPLICATION_MODAL);

					stage.showAndWait();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
