package boundry;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import Exceptions.RaffleException;
import control.RaffleLogic;
import entity.Raffle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WorkerAddRaffle implements Initializable {

	@FXML
	private Label idLabel;

	@FXML
	private JFXDatePicker datePick;

	@FXML
	private JFXTextField minersText;

	@FXML
	private JFXTextField winText;

	@FXML
	private JFXTextField benText;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		datePick.setValue(LocalDate.now());
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

						if (miners <= 0)
							throw new RaffleException("Max miners must be positive amount");
						if (winners <= 0)
							throw new RaffleException("number of Winners must be positive amount");
						if (bene <= 0)
							throw new RaffleException("number of Benefits must be positive amount");

						
						Raffle raf = new Raffle(0, toAdd, miners, winners, bene);
					WorkerRaffleMang.WorkerRaffleMang.table.getItems().add(RaffleLogic.addRaffle(raf));
						

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
