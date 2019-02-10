package boundry;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import control.Main;
import control.Sys;
import control.TransactionLogic;
import entity.Block;
import entity.Pairs;
import entity.Transaction;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class miningController implements Initializable {

	@FXML
	private Label addressLabel;

	@FXML
	private Label blockLabel;

	@FXML
	private Label sizeLabel;

	@FXML
	private TableView<Pairs> transactionsTable;

	@FXML
	private TableColumn<Pairs, String> idCol;

	@FXML
	private TableColumn<Pairs, String> idCol2;

	@FXML
	private TableColumn<Pairs, Integer> sizeCol;

	@FXML
	private TableColumn<Pairs, Integer> commisionCol;

	@FXML
	private TableView<Transaction> transactionsTableT;

	@FXML
	private TableColumn<Transaction, String> idColT;

	@FXML
	private TableColumn<Transaction, Integer> sizeColT;

	@FXML
	private TableColumn<Transaction, Integer> commisionColT;

	@FXML
	private Button addBtn1;

    @FXML
    private Label errorLabel;

    @FXML
    private Button backBtn;
    
    private ArrayList<Block> blocks ;
	private Block block;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		 blocks = TransactionLogic.getMinerBlocks( Sys.user);
	     block= blocks.isEmpty() ? null : blocks.get(0);
		
		
		addressLabel.setText(Sys.user.getAddress());
		if(block != null) {blockLabel.setText(block.getBlockAddress());
		sizeLabel.setText(Integer.toString(block.getSize()));}
		idCol.setCellValueFactory(new PropertyValueFactory<>("trans1"));
		idCol2.setCellValueFactory(new PropertyValueFactory<>("trans2"));
		sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
		commisionCol.setCellValueFactory(new PropertyValueFactory<>("comission"));
		idColT.setCellValueFactory(new PropertyValueFactory<>("ID"));
		sizeColT.setCellValueFactory(new PropertyValueFactory<>("size"));
		commisionColT.setCellValueFactory(new PropertyValueFactory<>("commission"));

		
		transactionsTable.getItems().addAll(FXCollections.observableArrayList(TransactionLogic.getBestTrans(block)));
		transactionsTableT.getItems().addAll(FXCollections.observableArrayList(TransactionLogic.getAllNotExecutedTransactions()));
	}
	@FXML
	private boolean addTransactionToBlock(ActionEvent event) {
		int blockSize = block.getSize();
		Transaction selected = transactionsTableT.getSelectionModel().getSelectedItem();
		if(selected==null) {
			errorLabel.setText("Select a Transaction from the Table");
			return false;
		}
		if(blockSize<selected.getSize()) {
			errorLabel.setText("No enough space in current Block");
			return false;
		}
		TransactionLogic.addToBlock(block, selected);
		blockSize = (int) (blockSize - selected.getSize());
		block.setSize(blockSize);
		sizeLabel.setText(Integer.toString(blockSize));
		transactionsTableT.getItems().remove(selected);
		transactionsTable.getItems().clear();
		errorLabel.setText("Transaction added to the Block");
		TransactionLogic.updateBlock(block.getBlockAddress(), blockSize);
		transactionsTable.getItems().addAll(FXCollections.observableArrayList(TransactionLogic.getBestTrans(block)));
		return true;
	}
	@FXML
	private void back(ActionEvent event) {
		Stage stage = (Stage) backBtn.getScene().getWindow();
		stage.close();
		ViewLogic.mainMenu();

	}

}
