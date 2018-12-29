package Utils;

import java.net.URLDecoder;

public class Consts {
	
	protected static final String DB_FILEPATH = getDBPath();
	public static final String CONN_STR = "jdbc:ucanaccess://" + DB_FILEPATH + ";COLUMNORDER=DISPLAY";
	public static final String GET_ALL_NOT_CHOSEN = "SELECT * FROM tblTransaction WHERE BlockAddress IS NULL ";
	
	private static String getDBPath() {
		try {
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			if (decoded.contains(".jar")) {
				decoded = decoded.substring(0, decoded.lastIndexOf('/'));
				return decoded + "/entity/DataBase_HW3.accdb";
			} else {
				decoded = decoded.substring(0, decoded.lastIndexOf("/"));
				return decoded + "/entity/DataBase_HW3.accdb";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
