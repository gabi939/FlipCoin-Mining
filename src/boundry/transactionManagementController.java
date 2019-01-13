package boundry;
import java.io.UnsupportedEncodingException;

import control.Communication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class transactionManagementController {

    @FXML
    private Button importBtn;

    @FXML
    private Button exportBtn;

    @FXML
    private Label sucLabel;
    
    @FXML
	private void importTransactions(ActionEvent event) {
		Communication.receiveJSON();
		sucLabel.setText("JSON Received Transactions Added to DataBase");
	}
    @FXML
	private void exportTransactions(ActionEvent event) throws UnsupportedEncodingException {
		Communication.sendXml();
		sucLabel.setText("XML file Exported");
	}
}
