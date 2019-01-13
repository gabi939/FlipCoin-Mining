package boundry;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import control.TransactionLogic;
import entity.Miner;
import entity.Pairs;
import entity.Transaction;

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
	private Button backBtn;

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
	private Button backBtn1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
		System.out.println(TransactionLogic.getBestTrans());
		System.out.println("aziz");
		System.out.println(TransactionLogic.getBestTrans());
		idCol.setCellValueFactory(new PropertyValueFactory<>("trans1"));
		idCol2.setCellValueFactory(new PropertyValueFactory<>("trans2"));
		sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
		commisionCol.setCellValueFactory(new PropertyValueFactory<>("comission"));
		
		
		transactionsTable.getItems().addAll(FXCollections.observableArrayList(TransactionLogic.getBestTrans()));
	
	}

}
