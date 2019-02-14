package boundry;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import control.RaffleLogic;
import control.Sys;
import entity.Raffle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MinerRaffle implements Initializable{

  

    @FXML
    private JFXListView<Raffle> BenList;

    @FXML
    private TableView<Raffle> tableRaffles;

    @FXML
    private TableColumn<Raffle, Integer> idColm;

    @FXML
    private TableColumn<Raffle, Date> dateColm;

    @FXML
    private TableColumn<Raffle, Integer> minerColm;

    @FXML
    private TableColumn<Raffle, Integer> winColm;

    @FXML
    private TableColumn<Raffle, Integer> benColm;

    
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {

    	setRaffleTable();
    	setListJoined();
	}
    
    
    private void setListJoined() {
    	BenList.getItems().addAll(RaffleLogic.getAllRafflesUserJoined(Sys.user));
    	
    	
    	
	}


	private void setRaffleTable() {
		idColm.setCellValueFactory(new PropertyValueFactory<>("RaffleId"));
		dateColm.setCellValueFactory(new PropertyValueFactory<>("raffleDate"));
		minerColm.setCellValueFactory(new PropertyValueFactory<>("maxMiners"));
		winColm.setCellValueFactory(new PropertyValueFactory<>("numWinners"));
		benColm.setCellValueFactory(new PropertyValueFactory<>("numBenefits"));
		
		tableRaffles.getItems().addAll(RaffleLogic.getAllRafflesUserCanJoin(Sys.user));
	}


	@FXML
    void goBack(ActionEvent event) {
		close();
    	ViewLogic.mainMenu();

    }

    @FXML
    void joinRaffle(ActionEvent event) {
    	Raffle raf = tableRaffles.getSelectionModel().getSelectedItem();
    	if(raf!=null) {
    		
    		tableRaffles.getItems().remove(raf);
    		BenList.getItems().add(raf);
    		RaffleLogic.addToRaffle(Sys.user, raf);
    		tableRaffles.refresh();
    		BenList.refresh();
    		
    		
    		
    	}
    		
    		
    		
    		
    	
    	
    }

    @FXML
    void leaveRaffle(ActionEvent event) {
    	Raffle raf = BenList.getSelectionModel().getSelectedItem();
    	if(raf!=null) {
    		BenList.getItems().remove(raf);
    		tableRaffles.getItems().add(raf);
    		RaffleLogic.removeFromRaffle(Sys.user,raf);
    	}
    	

    }
 

    @FXML
    void marketPrediction(ActionEvent event) {
    	

    }
    
    private void close() {
    	Stage stage = (Stage) tableRaffles.getScene().getWindow();
    	stage.close();
    	
    }

	

}
