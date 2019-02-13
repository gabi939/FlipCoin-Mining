package boundry;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.sql.Date;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import Exceptions.RaffleException;
import entity.Raffle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UpdateRaffle implements Initializable {

	@FXML
	private JFXDatePicker datePick;

	@FXML
	private JFXTextField minersText;

	@FXML
	private JFXTextField winText;

	@FXML
	private JFXTextField benText;

	@FXML
	private Label idLabel;

	private Raffle toUpdate;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		toUpdate = WorkerRaffleMang.WorkerRaffleMang.toUpdate;
		idLabel.setText("Update Raffle :" + toUpdate.getRaffleId());
		Date check = new Date(toUpdate.getRaffleDate().getTime());
		LocalDate date = check.toLocalDate();
		datePick.setValue(date);
		minersText.setText("" + toUpdate.getMaxMiners());
		winText.setText("" + toUpdate.getNumWinners());
		benText.setText("" + toUpdate.getNumBenefits());
	}

	@FXML
	void confirm(ActionEvent event) {

		LocalDate date = datePick.getValue();
		java.util.Date toAdd = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

		try {

			int miners = Integer.parseInt(minersText.getText());
			try {
				int winners = Integer.parseInt(winText.getText());

				try {

					int bene = Integer.parseInt(benText.getText());

					try {
						toUpdate.setMaxMiners(miners);
						toUpdate.setNumBenefits(bene);
						toUpdate.setNumWinners(winners);
						toUpdate.setRaffleDate(toAdd);
						toUpdate = null;
						
						WorkerRaffleMang.WorkerRaffleMang.table.refresh();

						Stage stage = (Stage) idLabel.getScene().getWindow();
						stage.close();

					} catch (RaffleException e) {

						switch (e.getMessage()) {
						case "Max miners must be positive amount":
							minersText.setText(e.getMessage());

							break;
						case "Raffle date cant be empty":
							minersText.setText(e.getMessage());
							break;
						case "number of Benefits must be positive amount":
							benText.setText(e.getMessage());
							break;
						case "number of Winners must be positive amount":
							winText.setText(e.getMessage());
							break;

						default:
							break;
						}

					}

				} catch (NumberFormatException e) {

					benText.setText("Invalid input");

				}

			} catch (NumberFormatException e) {

				winText.setText("Invalid input");

			}

		} catch (NumberFormatException e) {

			minersText.setText("Invalid input");

		}

	}

}