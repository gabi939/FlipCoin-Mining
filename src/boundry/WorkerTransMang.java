package boundry;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import control.Communication;
import control.TransactionLogic;
import entity.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class WorkerTransMang implements Initializable{

    @FXML
    private TableView<Transaction> table;

    @FXML
    private TableColumn<Transaction, String> idColm;

    @FXML
    private TableColumn<Transaction, Double> sizeColm;

    @FXML
    private TableColumn<Transaction, Double> comColm;

    @FXML
    private TableColumn<Transaction,String> blockColm;

    @FXML
    private TableColumn<Transaction, Date> addColm;

    @FXML
    void export(ActionEvent event) throws UnsupportedEncodingException {
    	Communication.sendXml();
    	
    	ArrayList<Transaction> temp =new ArrayList<>(table.getItems());
    	
    	
    	for(Transaction m : temp) {
    		if(m.getBlockAddress() != null)
    			table.getItems().remove(m);
    			
    		
    		
    	}
    }

    @FXML
    void goBack(ActionEvent event) {
    	Stage stage = (Stage) table.getScene().getWindow();
    	stage.close();
    	ViewLogic.WorkerMenu();

    }
    
    @FXML
    void importTrans(ActionEvent event) throws UnsupportedEncodingException {
    	Communication.receiveJSON();
    	table.getItems().setAll(TransactionLogic.getAllNotExecutedTransactions());

    }

  

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		idColm.setCellValueFactory(new PropertyValueFactory<>("ID"));
		sizeColm.setCellValueFactory(new PropertyValueFactory<>("size"));
		comColm.setCellValueFactory(new PropertyValueFactory<>("commission"));
		blockColm.setCellValueFactory(new PropertyValueFactory<>("blockAddress"));
		addColm.setCellValueFactory(new PropertyValueFactory<>("additionTime"));
		
		table.getItems().addAll(TransactionLogic.getAllTrans());


		
	}

}
