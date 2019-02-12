package boundry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WorkerMenu {

    @FXML
    private Label label;
	
	
    @FXML
    void goToBenefitMang(ActionEvent event) {
    	close();
    	ViewLogic.newWindow(ViewLogic.class.getResource("BenefitMang.fxml"), new Stage(), false, "", false);

    }

    @FXML
    void goToLevelMang(ActionEvent event) {

    	close();
    	ViewLogic.newWindow(ViewLogic.class.getResource("LevelMang.fxml"), new Stage(), false, "", false);
    	
    }

    @FXML
    void goToRaffleMang(ActionEvent event) {
    	close();
    	ViewLogic.newWindow(ViewLogic.class.getResource("RaffleMang.fxml"), new Stage(), false, "", false);
    }

    @FXML
    void goToRiddleMang(ActionEvent event) {
    	close();
    	ViewLogic.newWindow(ViewLogic.class.getResource("RiddleMang.fxml"), new Stage(), false, "", false);
    }

    @FXML
    void goToTransMang(ActionEvent event) {
    	close();
    	ViewLogic.newWindow(ViewLogic.class.getResource("TransMang.fxml"), new Stage(), false, "", false);
    }
    
    
    private void close() {
    	
    	Stage stage = (Stage) label.getScene().getWindow();
    	stage.close();
    	
    	
    }

}
