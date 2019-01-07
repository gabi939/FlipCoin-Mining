package boundry;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import control.TransactionLogic;
import entity.Block;
import entity.Miner;
import entity.Transaction;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class blockController implements Initializable {

	@FXML
	private TableView<Block> Table;

	@FXML
	private TableColumn<Block, String> ColumnAddress;

	@FXML
	private TableColumn<Block, Integer> ColumnSize;

	@FXML
	private TextField address1;

	@FXML
	private TextField address2;

	@FXML
	private TextField sum;

	@FXML
	private Button addButton;

	@FXML
	private Button backButton;

	private ArrayList<Transaction> trans;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		trans = TransactionLogic.getBestTrans();
		if (trans.size() > 2) {
			address1.setText(trans.get(0).getID());
			address2.setText(trans.get(1).getID());
			sum.setText(Integer.toString(trans.get(0).getSize() + trans.get(1).getSize()));

		} else if (trans.size() > 1) {
			address1.setText(trans.get(0).getID());
			sum.setText(Integer.toString(trans.get(0).getSize()));

		}

		ColumnAddress.setCellValueFactory(new PropertyValueFactory<>("blockAddress"));
		ColumnSize.setCellValueFactory(new PropertyValueFactory<>("size"));

		Table.getItems()
				.addAll(FXCollections.observableArrayList(TransactionLogic.getMinerBlocks(new Miner("miner1"))));

	}

	@FXML
	void add(ActionEvent event) {

	}

	@FXML
	void back(ActionEvent event) {

	}

}
