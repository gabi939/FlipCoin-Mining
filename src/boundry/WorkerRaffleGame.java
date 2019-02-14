package boundry;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import control.RaffleLogic;
import entity.Raffle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WorkerRaffleGame implements Initializable {

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
	
	public static Raffle raffle;
	public static WorkerRaffleGame workerRaffleGame;

	@FXML
	void goBack(ActionEvent event) {
		
		Stage stage = (Stage) table.getScene().getWindow();
		stage.close();
		ViewLogic.WorkerMenu();
	}
	private void setTable() {
		
		idColm.setCellValueFactory(new PropertyValueFactory<>("RaffleId"));
		dateColm.setCellValueFactory(new PropertyValueFactory<>("raffleDate"));
		minersColm.setCellValueFactory(new PropertyValueFactory<>("maxMiners"));
		winColm.setCellValueFactory(new PropertyValueFactory<>("numWinners"));
		benColm.setCellValueFactory(new PropertyValueFactory<>("numBenefits"));

		table.getItems().addAll(RaffleLogic.getAllNotPlayedRaffles());
		
		
	}

    @FXML
    void playIt(MouseEvent event) {
	Raffle raf  = table.getSelectionModel().getSelectedItem();
		
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if(raf != null) {
					raffle = raf;
					ViewLogic.newWindow(ViewLogic.class.getResource("PlayRaffle.fxml"), new Stage(), false, "update", false);
					
					
					
				}
			
		
			
			}
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTable();
		workerRaffleGame = this;
	}
	
	
	

}