package Utils;

import java.net.URLDecoder;

public class Consts {
	
	protected static final String DB_FILEPATH = getDBPath();
	public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILEPATH + ";COLUMNORDER=DISPLAY";

	
	//=========================================== Transactions Query =================================================
	
	public static final String GET_ALL_NOT_CHOSEN = "SELECT * FROM tblTransaction WHERE BlockAddress IS NULL ";
	public static final String SQL_UPD_TRANSACTION = "{ call updateTransaction(?,?,?) }";
	public static final String SQL_UPD_TRANSACTION1 = "{ call updateTransaction1(?,?,?) }";
	public static final String SQL_GET_EXECUTED_TRANSACTIONS = "SELECT tblTransaction.TranscationId, tblTransaction.Size , tblTransaction.Type,  tblTransaction.Comission,tblTransaction.BlockAddress\r\n" + 
			"FROM tblTransaction\r\n" + 
			"WHERE (([BlockAddress] Is Not Null) AND ((\"BlockAddress\") Is Not Null))";
	
	public static final String SQL_INSET_NEW_TRANSACTIONS = "{ call newTransaction(?,?,?,?,?) }";
	public static final String SQL_INSET_NEW_TRANSACTIONS1 = "{ call newTransaction1(?,?,?,?,?) }";
	public static final String SQL_GET_PAIRS = "SELECT tblTransaction.TranscationId, tblTransaction1.TranscationId, Sum(tblTransaction.Comission+tblTransaction1.Comission) AS TotalComission, Sum(tblTransaction.Size+tblTransaction1.Size) AS TotalSize\r\n" + 
			"FROM tblTransaction, tblTransaction1\r\n" + 
			"WHERE (((tblTransaction.TranscationId)<tblTransaction1.TranscationId)) AND tblTransaction.blockAddress IS NULL AND tblTransaction1.blockAddress IS NULL\r\n" + 
			"GROUP BY tblTransaction.TranscationId, tblTransaction1.TranscationId\r\n" + 
			"HAVING (((Sum(tblTransaction.Size + tblTransaction1.Size))<=?))\r\n" + 
			"ORDER BY Sum(tblTransaction.Comission+tblTransaction1.Comission) DESC;\r\n" + 
			"";

	
	//=========================================== Block Query ===========================================================
	public static final String ADD_BLOCK = "{ call addBlock(?,?) }";
	public static final String SQL_UPDATE_BLOCK = "{ call updateBlock(?,?) }";
	
	// =========================================== miner ===========================================
	public static final String UPDATE_PROFIT = " { call updateProfit(?,?) } ";

	
	
	private static String getDBPath() {
		try {
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
		
			if (decoded.contains(".jar")) {
				decoded = decoded.substring(0, decoded.lastIndexOf("/"));
				return decoded + "/src/entity/DataBase_HW3.accdb";
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
