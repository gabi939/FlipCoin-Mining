package boundry;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import control.RaffleLogic;
import control.RiddleLogic;
import entity.Benefit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WorkerBenefitMang implements Initializable {

	@FXML
	private TableView<Benefit> table;
	@FXML
	private TableColumn<Benefit, Integer> idColm;

	@FXML
	private TableColumn<Benefit, String> descpColm;

	private static String answer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		settable();
	}

	private void settable() {
		idColm.setCellValueFactory(new PropertyValueFactory<>("benefitId"));
		descpColm.setCellValueFactory(new PropertyValueFactory<>("description"));

		table.getItems().addAll(RaffleLogic.getAllBenefits());

	}

	@FXML
	void add(ActionEvent event) {

		popUp("Add benefit");
		if (answer != null && !answer.equals("")) {

			if (RaffleLogic.addBenefit(answer))
				table.getItems().setAll(RaffleLogic.getAllBenefits());

			answer = null;
		}

	}

	@FXML
	void goBack(ActionEvent event) {
		Stage stage = (Stage) table.getScene().getWindow();
		stage.close();
		ViewLogic.WorkerMenu();

	}

	@FXML
	void remove(ActionEvent event) {

		Benefit ben = table.getSelectionModel().getSelectedItem();
		if (ben != null) {

			RaffleLogic.removeBenefit(ben);
			table.getItems().remove(ben);
			table.refresh();

		}
	}

	@FXML
	void update(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				Benefit ben = table.getSelectionModel().getSelectedItem();
				if (ben != null) {

					popUp("Update benefit");

					if (answer != null && !answer.equals("")) {
						ben.setDescription(answer);
						table.refresh();
						table.getSelectionModel().clearSelection();

					}
				}
			}
		}

	}

	private void popUp(String str) {
		final Stage dialog = new Stage();

		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner((Stage) table.getScene().getWindow());
		VBox dialogVbox = new VBox(20);
		dialogVbox.setAlignment(Pos.CENTER);
		dialogVbox.getChildren().add(new Text(str));

		TextField text = new TextField();
		dialogVbox.getChildren().add(text);

		JFXButton but = new JFXButton("Confirm");
		but.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				if (text.getText() != null && !text.getText().equals("")) {
					answer = text.getText();
					dialog.close();
				} else
					text.setText("Please enter description");
			}
		});

		dialogVbox.getChildren().add(but);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		dialog.setScene(dialogScene);
		dialog.showAndWait();

	}

}