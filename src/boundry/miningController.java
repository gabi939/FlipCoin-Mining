package boundry;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entity.Transaction;

public class miningController {
	
	    @FXML
	    private Label addressLabel;

	    @FXML
	    private Label blockLabel;

	    @FXML
	    private Label sizeLabel;

	    @FXML
	    private TableView<Transaction> transactionsTable;

	    @FXML
	    private TableColumn<Transaction, String> idCol;

	    @FXML
	    private TableColumn<Transaction, String> idCol2;
	    
	    @FXML
	    private TableColumn<Transaction, Integer> sizeCol;

	    @FXML
	    private TableColumn<Transaction, Double> commisionCol;

	    @FXML
	    private Button addBtn;

	    @FXML
	    private Button backBtn;

	    
}
