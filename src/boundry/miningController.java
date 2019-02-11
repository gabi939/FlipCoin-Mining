package boundry;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import Exceptions.BlockSizeException;
import control.Main;
import control.MinerLogic;
import control.Sys;
import control.TransactionLogic;
import control.blockLogic;
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
	private JFXComboBox<Block> blockComboBox;
	@FXML
	private Button backBtn;

	private Block selected;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		addressLabel.setText(Sys.user.getAddress());

		firstSetCombo();
		setTblPairs();
		setTblMinning();

		
		
	}

	@FXML
	private boolean addTransactionToBlock(ActionEvent event) {
	
		
		int blockSize = selected.getSize();
		Transaction selectedTrans = transactionsTableT.getSelectionModel().getSelectedItem();
		if (selectedTrans == null) {
			errorLabel.setText("Select a Transaction from the Table");
			return false;
		}
		if (blockSize < selectedTrans.getSize()) {
			errorLabel.setText("No enough space in current Block");
			return false;
		}
		TransactionLogic.addToBlock(selected, selectedTrans);
		blockSize = (int) (blockSize - selectedTrans.getSize());
		
		try {
			selected.setSize(blockSize);
	
		transactionsTableT.getItems().remove(selectedTrans);
		transactionsTable.getItems().clear();
		errorLabel.setText("Transaction added to the Block");
		TransactionLogic.updateBlock(selected.getBlockAddress(), blockSize);
		updateTablePairs(selected);
		setCombo();
		return true;
		
		} catch (BlockSizeException e) {
			errorLabel.setText("No enough space in current Block");
			return false;
		}
	}

	@FXML
	private void back(ActionEvent event) {
		Stage stage = (Stage) backBtn.getScene().getWindow();
		stage.close();
		ViewLogic.mainMenu();

	}

	@FXML
	private void blockChoice(ActionEvent event) {
		selected = blockComboBox.getSelectionModel().getSelectedItem();
		if(selected!=null) {
			sizeLabel.setText(Integer.toString(selected.getSize()));	
			updateTablePairs(selected);

		}else 
			sizeLabel.setText("N/A");
		
	}
	
	
	private void firstSetCombo() {
		blockComboBox.getItems().addAll(blockLogic.getMinerBlocks(Sys.user));
		blockComboBox.getSelectionModel().select(0);
		selected = blockComboBox.getSelectionModel().getSelectedItem();
		
		if(selected!=null) {
			sizeLabel.setText(Integer.toString(selected.getSize()));	
		}else
			sizeLabel.setText("N/A");
		
	}
	
	
	private void setCombo() {
		selected = blockComboBox.getSelectionModel().getSelectedItem();
		
		if(selected!=null) {
			sizeLabel.setText(Integer.toString(selected.getSize()));	
		}else
			sizeLabel.setText("N/A");
	}
	
	
	
	private void setTblMinning() {
		idColT.setCellValueFactory(new PropertyValueFactory<>("ID"));
		sizeColT.setCellValueFactory(new PropertyValueFactory<>("size"));
		commisionColT.setCellValueFactory(new PropertyValueFactory<>("commission"));
		transactionsTableT.getItems()
		.addAll(FXCollections.observableArrayList(TransactionLogic.getAllNotExecutedTransactions()));
		
	}
	
	private void setTblPairs() {
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("trans1"));
		idCol2.setCellValueFactory(new PropertyValueFactory<>("trans2"));
		sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
		commisionCol.setCellValueFactory(new PropertyValueFactory<>("comission"));
		transactionsTable.getItems().addAll(FXCollections.observableArrayList(TransactionLogic.getBestTrans(selected)));
	}
	
	private void updateTablePairs(Block block) {
		transactionsTable.getItems().addAll(FXCollections.observableArrayList(TransactionLogic.getBestTrans(block)));
	}



}
