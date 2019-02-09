package boundry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class menuController {

	    @FXML
	    private Button ieTestBtn;

	    @FXML
	    private Button addBtn;

	    @FXML
	    private Button exitBtn;
	    
	    @FXML
		private void ieWindow(ActionEvent event) {
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();
			ViewLogic.newWindow(ViewLogic.class.getResource("WorkerImportExport.fxml"), stage, false,"Import Export Testing",false);

		}
	    @FXML
		private void addTransWindow(ActionEvent event) {
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();
			ViewLogic.newWindow(ViewLogic.class.getResource("MinerMinning.fxml"), stage, false,"Block Management",false);
		}
	    @FXML
		private void exit(ActionEvent event) {
			Stage stage = (Stage) exitBtn.getScene().getWindow();
			stage.close();
		}

}


