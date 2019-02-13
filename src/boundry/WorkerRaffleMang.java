package boundry;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import control.RaffleLogic;
import entity.Raffle;
import javafx.animation.SequentialTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WorkerRaffleMang implements Initializable {

	@FXML
	public TableView<Raffle> table;

	@FXML
	private TableColumn<Raffle, Integer> idColm;

	@FXML
	private TableColumn<Raffle, Date> dateColm;

	@FXML
	private TableColumn<Raffle, Integer> minersColm;

	@FXML
	private TableColumn<Raffle, Integer> winColm;

	@FXML
	private TableColumn<Raffle, Integer> benColm;

	public static  WorkerRaffleMang WorkerRaffleMang;
	
	public Raffle toUpdate;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		WorkerRaffleMang = this;
		setTable();
	}

	private void setTable() {
		
		
		idColm.setCellValueFactory(new PropertyValueFactory<>("RaffleId"));
		dateColm.setCellValueFactory(new PropertyValueFactory<>("raffleDate"));
		minersColm.setCellValueFactory(new PropertyValueFactory<>("maxMiners"));
		winColm.setCellValueFactory(new PropertyValueFactory<>("numWinners"));
		benColm.setCellValueFactory(new PropertyValueFactory<>("numBenefits"));

		table.getItems().addAll(RaffleLogic.getAllRaffles());
		
		
	}

	@FXML
	void add(ActionEvent event) {
		ViewLogic.newWindow(ViewLogic.class.getResource("addRaffle.fxml"), new Stage(), false, "update", false);
	}

	@FXML
	void goBack(ActionEvent event) {
		
		Stage stage = (Stage) table.getScene().getWindow();
		stage.close();
		ViewLogic.WorkerMenu();
	}

	@FXML
	void remove(ActionEvent event) {
		Raffle raf  = table.getSelectionModel().getSelectedItem();

		if(raf != null) {
			RaffleLogic.removeRaffle(raf);
			table.getItems().remove(raf);
			
		}
	}

	@FXML
	void update(MouseEvent event) {
		Raffle raf  = table.getSelectionModel().getSelectedItem();
		
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if(raf != null) {
					toUpdate = raf;
					ViewLogic.newWindow(ViewLogic.class.getResource("UpdateRaffle.fxml"), new Stage(), false, "update", false);
					
					
					
					
				}
			
			
			
			
			
			
			
			
			
			
			}
		}

	}

}
