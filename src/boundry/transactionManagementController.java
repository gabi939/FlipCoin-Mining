package boundry;
import java.io.UnsupportedEncodingException;

import control.Communication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class transactionManagementController {

    @FXML
    private Button importBtn;

    @FXML
    private Button exportBtn;

    @FXML
    private Label sucLabel;
    
    @FXML
    private Button backBtn;

    @FXML
	private void importTransactions(ActionEvent event) throws UnsupportedEncodingException {
		Communication.receiveJSON();
		sucLabel.setText("JSON Received Transactions Added to DataBase");
		importBtn.setDisable(true);
	}
    @FXML
	private void exportTransactions(ActionEvent event) throws UnsupportedEncodingException {
		Communication.sendXml();
		sucLabel.setText("XML file Exported");
		exportBtn.setDisable(true);
	}
    @FXML
	private void back(ActionEvent event) {
		Stage stage = (Stage) backBtn.getScene().getWindow();
		stage.close();
		ViewLogic.mainMenu();
	}
}
