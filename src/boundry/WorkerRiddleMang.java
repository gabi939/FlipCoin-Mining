package boundry;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import Utils.RiddleStatus;
import control.RiddleLogic;
import entity.Riddle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WorkerRiddleMang implements Initializable {

	@FXML
	private TableView<Riddle> table;

	@FXML
	private TableColumn<Riddle, Integer> idColm;

	@FXML
	private TableColumn<Riddle, Date> publishDateColm;

	@FXML
	private TableColumn<Riddle, String> decpColm;

	@FXML
	private TableColumn<Riddle, Date> finishColm;

	@FXML
	private TableColumn<Riddle, RiddleStatus> statusColm;

	@FXML
	private TableColumn<Riddle, String> lvlColm;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTable();
	}

	private void setTable() {
		idColm.setCellValueFactory(new PropertyValueFactory<>("riddleId"));
		publishDateColm.setCellValueFactory(new PropertyValueFactory<>("publishDate"));
		decpColm.setCellValueFactory(new PropertyValueFactory<>("description"));
		finishColm.setCellValueFactory(new PropertyValueFactory<>("solutionFinishTime"));
		statusColm.setCellValueFactory(new PropertyValueFactory<>("status"));
		lvlColm.setCellValueFactory(new PropertyValueFactory<>("level"));

		table.getItems().addAll(RiddleLogic.getAllRiddle());

	}

	@FXML
	void add(ActionEvent event) {

		ViewLogic.newWindow(ViewLogic.class.getResource("addRiddle.fxml"), new Stage(), false, "Add Riddle", false);
	}

	@FXML
	void goBack(ActionEvent event) {
		close();
		ViewLogic.WorkerMenu();
	}

	@FXML
	void remove(ActionEvent event) {
		Riddle rid = table.getSelectionModel().getSelectedItem();
		
		if(rid != null) {
			RiddleLogic.removeRiddle(rid);
			table.getItems().remove(rid);	
		}

	}

	@FXML
	void update(MouseEvent event) {

		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {

				
				
				
				
				
				
			}
		}
	}

	private void close() {
		Stage stage = (Stage) table.getScene().getWindow();
		stage.close();
	}

}
