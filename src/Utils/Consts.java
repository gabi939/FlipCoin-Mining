package Utils;

import java.net.URLDecoder;

public class Consts {
	
	protected static final String DB_FILEPATH = getDBPath();
	public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILEPATH + ";COLUMNORDER=DISPLAY";

	
	//=========================================== Transactions Query =================================================
	public static final String GET_ALL_NOT_CHOSEN = "SELECT * FROM tblTransaction WHERE BlockAddress IS NULL ";
	public static final String SQL_UPD_TRANSACTION = "{ call updateTransaction(?,?) }";
	public static final String SQL_GET_EXECUTED_TRANSACTIONS = "SELECT tblTransaction.TranscationId, tblTransaction.Size , tblTransaction.Type,  tblTransaction.Comission,tblTransaction.BlockAddress\r\n" + 
			"FROM tblTransaction\r\n" + 
			"WHERE (([BlockAddress] Is Not Null) AND ((\"BlockAddress\") Is Not Null))";
	
	public static final String SQL_INSET_NEW_TRANSACTIONS = "{ call newTransaction(?,?,?,?,?) }";
	//=========================================== Block Query =========================================================
	public static final String ADD_BLOCK = "{ call addBlock(?,?) }";
	
	
	private static String getDBPath() {
		try {
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			if (decoded.contains(".jar")) {
				decoded = decoded.substring(0, decoded.lastIndexOf('/'));
				return decoded + "/entity/DataBase_HW3.accdb";
			} else {
				decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
				return decoded + "src/entity/DataBase_HW3.accdb";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
