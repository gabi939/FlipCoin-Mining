package boundry;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import control.RaffleLogic;
import entity.Miner;
import entity.PartData;
import entity.Raffle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WorkerPlayRaffle implements Initializable {

    @FXML
    private Label errorLabel;
	
	@FXML
	private JFXTextField idText;

	@FXML
	private JFXTextField minerText;

	@FXML
	private JFXDatePicker pick;

	@FXML
	private JFXTextField winText;

	@FXML
	private JFXTextField benText;

	@FXML
	private TableView<PartData> table;

	@FXML
	private TableColumn<PartData, String> addColm;

	@FXML
	private TableColumn<PartData, String> nameColm;

	@FXML
	private TableColumn<PartData, Boolean> winnerColm;

	@FXML
	void rewardWinner(ActionEvent event) {
		ArrayList<Miner> winners = new ArrayList<>();
		int winner = WorkerRaffleGame.raffle.getNumWinners();

		for (PartData temp : table.getItems())
			if (temp.getWin())
				winners.add(new Miner(temp.getAddress()));
		if (winners.size() <= winner) {

			RaffleLogic.addToWinners(WorkerRaffleGame.raffle, winners);
			WorkerRaffleGame.workerRaffleGame.table.getItems().remove(WorkerRaffleGame.raffle);

			WorkerRaffleGame.raffle = null;
			Stage stage = (Stage) table.getScene().getWindow();
			stage.close();

		}else {
			errorLabel.setText("To much winners");
			
			
		}

	}

	@FXML
	void updateWinner(MouseEvent event) {
		PartData data = table.getSelectionModel().getSelectedItem();

		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if (data != null) {
					data.setWin(!data.getWin());
					table.refresh();

				}
			}
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Raffle rif = WorkerRaffleGame.raffle;
		addColm.setCellValueFactory(new PropertyValueFactory<>("address"));
		winnerColm.setCellValueFactory(new PropertyValueFactory<>("win"));

		table.getItems().addAll(RaffleLogic.getAllParticipants(rif));
		idText.setText(rif.getRaffleId() + "");
		minerText.setText(rif.getMaxMiners() + "");
		winText.setText(rif.getNumWinners() + "");
		benText.setText(rif.getNumBenefits() + "");

		pick.setValue(new Date(rif.getRaffleDate().getTime()).toLocalDate());

	}

}
