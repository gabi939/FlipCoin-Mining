package boundry;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import control.MinerLogic;
import control.Sys;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;

public class menuController implements Initializable {

	@FXML
	private Label minersCountLabel;

	@FXML
	private Label companysCountLabel;

	@FXML
	private JFXTextField MInerSearch;

	@FXML
	private JFXTreeTableView<Miner> MinerTable;

	@FXML
	private JFXTextField CompanySearch;

	@FXML
	private JFXTreeTableView<Company> CompanyTable;

	@FXML
	private JFXButton addBtn;

	@FXML
	private Label MinerNameLabel;

	@FXML
	private Label MinerDigtaProfit;

	@FXML
	private Label ContactLabel;

	@FXML
	private Label ContactNameLabel;

	@FXML
	private Label ContactPhoneLabel;

	@FXML
	private Label ContactMailLabel;

	@FXML
	private Label ContactNameLabelUp;

	@FXML
	private Label ContactPhoneLabelUp;

	@FXML
	private Label ContactMailLabelUp;

	/**
	 * 
	 * A class to display the miner data in the table
	 * 
	 * @author Gabi Malin
	 *
	 */
	private final class Miner extends RecursiveTreeObject<Miner> {
		final StringProperty Name;
		final StringProperty Email;
		final StringProperty Profit;

		Miner(String minerName, String minerEmail, Double minerDigitalProfit) {
			this.Name = new SimpleStringProperty(minerName);
			this.Email = new SimpleStringProperty(minerEmail);
			this.Profit = new SimpleStringProperty(minerDigitalProfit.toString());
		}
	}

	/**
	 * A class to display company details
	 * 
	 * @author Gabi Malin
	 *
	 */
	private final class Company extends RecursiveTreeObject<Company> {
		final StringProperty Name;
		final StringProperty Email;
		final StringProperty Profit;
		final StringProperty contactName;
		final StringProperty phone;
		final StringProperty contactEmail;

		Company(String minerName, String minerEmail, Double minerDigitalProfit, String firstName, String lastName,
				String phone, String email) {
			this.Name = new SimpleStringProperty(minerName);
			this.Email = new SimpleStringProperty(minerEmail);
			this.Profit = new SimpleStringProperty(minerDigitalProfit.toString());
			this.contactName = new SimpleStringProperty(firstName + " " + lastName);
			this.phone = new SimpleStringProperty(phone);
			this.contactEmail = new SimpleStringProperty(email);
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setMinerTable();
		setCompanyTable();
		setLabels();

	}

	/**
	 * sets the labels of the window
	 */
	private void setLabels() {
		entity.Miner user = Sys.user;

		MinerNameLabel.setText(user.getName());
		MinerDigtaProfit.setText(Double.toString(user.getDigitalProfit()));

		if (user instanceof entity.Company) {

			ContactMailLabel.setText(((entity.Company) user).getContactEmail());
			ContactNameLabel.setText(((entity.Company) user).getContactFirstName() + " "
					+ ((entity.Company) user).getContactFamilyName());
			ContactPhoneLabel.setText(((entity.Company) user).getContactPhone());

		} else {
			ContactMailLabel.setVisible(false);
			ContactMailLabelUp.setVisible(false);
			ContactNameLabel.setVisible(false);
			ContactNameLabelUp.setVisible(false);
			ContactPhoneLabel.setVisible(false);
			ContactPhoneLabelUp.setVisible(false);
			ContactLabel.setVisible(false);
		}

	}

	/**
	 * this function sets the company table
	 */
	@SuppressWarnings("unchecked")
	private void setCompanyTable() {

		JFXTreeTableColumn<Company, String> nameColumn = new JFXTreeTableColumn<>("Name");
		nameColumn.setPrefWidth(150);
		nameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Company, String> param) -> {
			if (nameColumn.validateValue(param))
				return param.getValue().getValue().Name;
			else
				return nameColumn.getComputedValue(param);
		});

		JFXTreeTableColumn<Company, String> EmailColumn = new JFXTreeTableColumn<>("Company email");
		EmailColumn.setPrefWidth(150);
		EmailColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Company, String> param) -> {
			if (EmailColumn.validateValue(param))
				return param.getValue().getValue().Email;
			else
				return EmailColumn.getComputedValue(param);
		});

		JFXTreeTableColumn<Company, String> ProfitColumn = new JFXTreeTableColumn<>("Profit");
		ProfitColumn.setPrefWidth(150);
		ProfitColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Company, String> param) -> {
			if (ProfitColumn.validateValue(param))
				return param.getValue().getValue().Profit;
			else
				return ProfitColumn.getComputedValue(param);
		});

		JFXTreeTableColumn<Company, String> contactNameColumn = new JFXTreeTableColumn<>("contact name");
		contactNameColumn.setPrefWidth(150);
		contactNameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Company, String> param) -> {
			if (contactNameColumn.validateValue(param))
				return param.getValue().getValue().contactName;
			else
				return contactNameColumn.getComputedValue(param);
		});

		JFXTreeTableColumn<Company, String> phoneColumn = new JFXTreeTableColumn<>("phone");
		phoneColumn.setPrefWidth(150);
		phoneColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Company, String> param) -> {
			if (phoneColumn.validateValue(param))
				return param.getValue().getValue().phone;
			else
				return phoneColumn.getComputedValue(param);
		});

		JFXTreeTableColumn<Company, String> contactEmailColumn = new JFXTreeTableColumn<>("contact email");
		contactEmailColumn.setPrefWidth(150);
		contactEmailColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<Company, String> param) -> {
			if (contactEmailColumn.validateValue(param))
				return param.getValue().getValue().contactEmail;
			else
				return contactEmailColumn.getComputedValue(param);
		});

		ObservableList<Company> company = FXCollections.observableArrayList();
		ArrayList<entity.Company> company2 = MinerLogic.getAllCompanys();
		company2.remove(Sys.user);

		for (entity.Company temp : company2)
			company.add(
					new Company(temp.getName(), temp.getEmail(), temp.getDigitalProfit(), temp.getContactFirstName(),
							temp.getContactFamilyName(), temp.getContactPhone(), temp.getContactEmail()));

		final TreeItem<Company> root = new RecursiveTreeItem<Company>(company, RecursiveTreeObject::getChildren);

		CompanyTable.getColumns().setAll(nameColumn, EmailColumn, ProfitColumn, contactNameColumn, phoneColumn,
				contactEmailColumn);
		CompanyTable.setRoot(root);
		CompanyTable.setShowRoot(false);
		CompanyTable.setEditable(false);

		CompanySearch.textProperty().addListener((o, oldVal, newVal) -> {
			CompanyTable.setPredicate(user -> user.getValue().Name.get().contains(newVal));

		});

		companysCountLabel.textProperty()
				.bind(Bindings.createStringBinding(() -> "(" + CompanyTable.getCurrentItemsCount() + ") companies",
						CompanyTable.currentItemsCountProperty()));

	}

	/**
	 * 
	 * this function sets the miners table
	 * 
	 * copy paste pretty much from the Internet
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void setMinerTable() {

		JFXTreeTableColumn<Miner, String> name = new JFXTreeTableColumn<Miner, String>("Name");
		name.setPrefWidth(100);

		name.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<Miner, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Miner, String> param) {
						return param.getValue().getValue().Name;
					}
				});

		JFXTreeTableColumn<Miner, String> age = new JFXTreeTableColumn<Miner, String>("Email");
		age.setPrefWidth(100);

		age.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<Miner, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Miner, String> param) {
						return param.getValue().getValue().Email;
					}
				});

		JFXTreeTableColumn<Miner, String> address = new JFXTreeTableColumn<Miner, String>("Digital Profit");
		address.setPrefWidth(100);

		address.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<Miner, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Miner, String> param) {
						return param.getValue().getValue().Profit;
					}
				});

		ObservableList<Miner> miners = FXCollections.observableArrayList();
		ArrayList<entity.Miner> miners2 = MinerLogic.getAllMiners();
		miners2.remove(Sys.user);

		for (entity.Miner temp : miners2)
			miners.add(new Miner(temp.getName(), temp.getEmail(), temp.getDigitalProfit()));

		final TreeItem<Miner> root = new RecursiveTreeItem<Miner>(miners, RecursiveTreeObject::getChildren);
		MinerTable.getColumns().setAll(name, age, address);
		MinerTable.setRoot(root);
		MinerTable.setShowRoot(false);

		MInerSearch.textProperty().addListener((o, oldVal, newVal) -> {
			MinerTable.setPredicate(user -> user.getValue().Name.get().contains(newVal));
		});

		minersCountLabel.textProperty()
				.bind(Bindings.createStringBinding(() -> "(" + MinerTable.getCurrentItemsCount() + ") Private miners",
						MinerTable.currentItemsCountProperty()));

	}

	/**
	 * takes you import export of transactions
	 * 
	 * @param event
	 */
	@FXML
	private void ieWindow(ActionEvent event) {
		Stage stage = (Stage) addBtn.getScene().getWindow();
		stage.close();
		ViewLogic.newWindow(ViewLogic.class.getResource("WorkerImportExport.fxml"), stage, false,
				"Import Export Testing", false);

	}

	/**
	 * takes you to Mining window
	 * 
	 * @param event
	 */
	@FXML
	private void addTransWindow(ActionEvent event) {
		Stage stage = (Stage) addBtn.getScene().getWindow();
		stage.close();
		ViewLogic.newWindow(ViewLogic.class.getResource("MinerMinning.fxml"), stage, false, "Block Management", false);
	}

	@FXML
	void goToRiddleSolving(ActionEvent event) {
		Stage stage = (Stage) addBtn.getScene().getWindow();
		stage.close();
		ViewLogic.newWindow(ViewLogic.class.getResource("MinerRiddleSolving.fxml"), stage, false, "Riddle Solving",
				false);

	}

	@FXML
	void goToRaffles(ActionEvent event) {
		Stage stage = (Stage) addBtn.getScene().getWindow();
		stage.close();
		
		ViewLogic.newWindow(ViewLogic.class.getResource("MinerRaffleGame.fxml"), stage, false, "Raffles",
				false);		
	}

	@FXML
	private void exit(ActionEvent event) {
		Stage stage = (Stage) addBtn.getScene().getWindow();
		stage.close();
	}
}
