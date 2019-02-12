package boundry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import Utils.Consts;
import control.MinerLogic;
import control.Sys;
import entity.Company;
import entity.Miner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignUpLoginController {

	@FXML
	private VBox LoginBox;

	@FXML
	private JFXTextField loginUserNameTextField;

	@FXML
	private JFXPasswordField loginPasswordTextField;

	@FXML
	private JFXButton loginBtn;

	@FXML
	private Label loginError;

	@FXML
	private JFXTextField MinerNameTextField;

	@FXML
	private JFXPasswordField MnerPass1TextField;

	@FXML
	private JFXPasswordField MnerPass2TextField;

	@FXML
	private JFXTextField MnerEmailTextField;

	@FXML
	private ToggleGroup companyOrPrivate;

	@FXML
	private JFXRadioButton radioBtnPrivate;

	@FXML
	private JFXRadioButton radioBtnCompany;

	@FXML
	private VBox ContactDetails;

	@FXML
	private JFXTextField ContactFirstNameTextField;

	@FXML
	private JFXTextField ContactLastNameTextField;

	@FXML
	private JFXTextField ContactPhoneTextField;

	@FXML
	private JFXTextField ContactEmailTextField;

	@FXML
	private JFXButton signUpBtn;

	@FXML
	private Label signUpError;

	
	@FXML
	boolean Login(ActionEvent event) {
	
		
		// checking of fields parameters are valid
		if (loginUserNameTextField.getText().equals(null) || loginUserNameTextField.getText().equals("")) {// valid name
			loginError.setText("No UserName");
			return false;
		}
		if (loginPasswordTextField.getText().equals(null) || loginPasswordTextField.getText().equals("")) {// valid password
			loginError.setText("No Password");
			return false;
		}
		
		
	if(loginPasswordTextField.getText().equals("Admin") && loginUserNameTextField.getText().equals("Admin")) {	
		closeWindow();
		ViewLogic.WorkerMenu();
		return true;
		
	}else {
		int amountToCheck = 0;
		Miner miner = null; 
		
		// checking if entity exists
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn
						.prepareStatement("SELECT Count(*) FROM tblMiner WHERE MinerName = ? AND Password = ? ");
				stmt.setString(1, loginUserNameTextField.getText());
				stmt.setString(2, loginPasswordTextField.getText());
				ResultSet rs = stmt.executeQuery();
				rs.next();
				amountToCheck = rs.getInt(1); // the amount in DB
				

			} catch (SQLException e) {
				e.printStackTrace();
				return false;

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;

		}
		
		
		if(amountToCheck != 1) {// case there is more or less then 1 entity in the system 
			loginError.setText("No such user");
			return false;
		}
		
		
		// case exists get the entity from DB 
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM tblMiner WHERE MinerName = ? AND Password = ? ");
				stmt.setString(1, loginUserNameTextField.getText());
				stmt.setString(2, loginPasswordTextField.getText());
				ResultSet rs = stmt.executeQuery();
				rs.next();
				 miner = new Miner(rs.getString(1), rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getDouble(5)); // getting the entity from the DB
				

			} catch (SQLException e) {
				e.printStackTrace();
				return false;

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;

		}
		
		
		
		
		
		Sys.user = miner;// the miner is logged on 
		
		
		
		// checking if the entity exists in company table
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				PreparedStatement stmt = conn
						.prepareStatement("SELECT Count(*) FROM tblCompany WHERE Address = ?  ");
				stmt.setString(1,miner.getAddress());
				ResultSet rs = stmt.executeQuery();
				rs.next();
				amountToCheck = rs.getInt(1); // getting the amount of entity's  in the DB
				

			} catch (SQLException e) {
				e.printStackTrace();
				return false;

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;

		}
		
		
		if(amountToCheck ==1) {// case the miner is also a company 
			
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				try {
					Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn
							.prepareStatement("SELECT * FROM tblCompany WHERE Address = ?  ");
					stmt.setString(1,miner.getAddress());
					ResultSet rs = stmt.executeQuery();
					rs.next();
					Sys.user = new Company(miner.getAddress(), miner.getName(), miner.getPassword(), miner.getEmail(),
							miner.getDigitalProfit(), rs.getString(2),
							rs.getString(3), rs.getString(4), rs.getString(5)); // getting the amount of entity's  in the DB
					

				} catch (SQLException e) {
					e.printStackTrace();
					return false;

				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return false;

			}
			
			
			
		
			ViewLogic.mainMenu();
			closeWindow();
			return true;
		}
		
		
		
		
		ViewLogic.mainMenu();
		closeWindow();
		return true;
	}
	}

	@FXML
	boolean SignUp(ActionEvent event) {
		if (MinerNameTextField.getText().equals(null) || MinerNameTextField.getText().equals("")) {
			signUpError.setText("Please enter username");
			return false;
		}

		if (MinerLogic.checkIfMinerExists(MinerNameTextField.getText())) {
			signUpError.setText("username exists , please Choose a different Username");
			return false;
		}

		if (MnerPass1TextField.getText().isEmpty() || MnerPass1TextField.getText().equals("")) {
			signUpError.setText("please fill Password field");
			return false;
		}
		if (MnerPass2TextField.getText().equals(null) || MnerPass2TextField.getText().equals("")) {
			signUpError.setText("Password Validation is empty");
			return false;
		}
		if (!MnerPass1TextField.getText().equals(MnerPass2TextField.getText())) {
			signUpError.setText("Not matching passwords");
			return false;
		}
		if (MnerEmailTextField.getText().equals(null) || MnerEmailTextField.getText().equals("")) {
			signUpError.setText("Please provide an Email address");
			return false;
		}

		if (!emailCheck(MnerEmailTextField.getText())) {
			signUpError.setText("Please enter a valid email");
			return false;
		}

		/**
		 * adding company in this case we need to get all parameters for contact
		 */

		if (radioBtnCompany.isSelected()) {

			if (!validateName(ContactFirstNameTextField.getText())) {
				signUpError.setText("Invalid contact first name");
				return false;
			}

			if (!validateName(ContactLastNameTextField.getText())) {
				signUpError.setText("Invalid contact last name");
				return false;
			}

			if (!validatePhone(ContactPhoneTextField.getText())) {
				signUpError.setText("Invalid contact phone number");
				return false;
			}
			if (!validateEmail(ContactEmailTextField.getText())) {
				signUpError.setText("Invalid contact email");
				return false;
			}

			Company company = new Company(RandomStringUtils.randomAlphanumeric(7), MinerNameTextField.getText(),
					MnerPass1TextField.getText(), MnerEmailTextField.getText(), 0, ContactFirstNameTextField.getText(),
					ContactLastNameTextField.getText(), ContactPhoneTextField.getText(),
					ContactEmailTextField.getText());

			MinerLogic.addingCompany(company);
			clearSignUpFields();

			return true;

		}

		/**
		 * adding a Private miner
		 */
		else {
			Miner miner = new Miner(RandomStringUtils.randomAlphanumeric(7), MinerNameTextField.getText(),
					MnerPass1TextField.getText(), MnerEmailTextField.getText(), 0);
			MinerLogic.addingMiner(miner);
			clearSignUpFields();
			return true;

		}

	}

	@FXML
	void companySelected(ActionEvent event) {
		ContactDetails.setVisible(true);// case the user needs to fill the contact Fields
		ContactDetails.setPrefHeight(90);

	}

	@FXML
	void privateSelected(ActionEvent event) {
		ContactDetails.setVisible(false);// case the user doesn't need to fill Contact details
		ContactDetails.setPrefHeight(0);

	}

	/**
	 * Email VALIDATION PATTERN
	 *
	 * @return
	 */
	private boolean emailCheck(String email) {
		if (validateEmail(email) == false) {
			signUpError.setText("Invalid email address");
			return false;
		} else {
			return true;
		}
	}

	private final Pattern VALIDEMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	private boolean validateEmail(String emailStr) {
		Matcher matcher = VALIDEMAIL.matcher(emailStr);
		return matcher.find();

	}

	/**
	 * validates names
	 * 
	 * @param txt
	 * @return
	 */
	private boolean validateName(String txt) {

		String regx = "^[\\p{L} .'-]+$";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(txt);
		return matcher.find();

	}

	/**
	 * 
	 * validate phone
	 * 
	 * @param phone
	 * @return
	 */
	private boolean validatePhone(String phone) {
		return phone.matches("^((\\+|00)?972\\-?|0)(([23489]|[57]\\d)\\-?\\d{7})$");
	}

	/**
	 * clears all fields
	 */
	private void clearSignUpFields() {
		MinerNameTextField.clear();
		MnerPass1TextField.clear();
		MnerPass2TextField.clear();
		MnerEmailTextField.clear();
		ContactFirstNameTextField.clear();
		ContactLastNameTextField.clear();
		ContactEmailTextField.clear();
		ContactPhoneTextField.clear();
	}

	
	/**
	 * close window 
	 */
	private void closeWindow() {
		Stage stage = (Stage) radioBtnCompany.getScene().getWindow();
		stage.close();
	}
	
	
	
	
}
