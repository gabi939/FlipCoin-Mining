package control;

import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Utils.Consts;
import Utils.Type;
import entity.Riddle;
import entity.Transaction;

public class Communication {

	/**
	 * sends executed transactions to flipCoin Transfer via XML
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static void sendXml() throws UnsupportedEncodingException {
		try {

			ArrayList<Transaction> DBtrans = TransactionLogic.getAllexecuted();

			for (Transaction temp : DBtrans)
				updateToExported(temp);

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element transactions = doc.createElement("Transcation");
			doc.appendChild(transactions);

			/**
			 * adding elements to transactions array that will be sent
			 * 
			 * 1) create root element of transaction
			 * 
			 * 2) add its attributes
			 *
			 * 3) add transaction to the array
			 */
			for (Transaction temp : DBtrans) {

				// 1)
				Element trans = doc.createElement("Transaction");

				// 2)
				Element id = doc.createElement("transId");
				id.appendChild(doc.createTextNode(temp.getID()));
				trans.appendChild(id);

				Element size = doc.createElement("size");
				size.appendChild(doc.createTextNode(Double.toString(temp.getSize())));
				trans.appendChild(size);

				Element type = doc.createElement("type");
				type.appendChild(doc.createTextNode(temp.getType().toString()));
				trans.appendChild(type);

				Element comission = doc.createElement("comission");
				comission.appendChild(doc.createTextNode(Double.toString(temp.getCommission())));
				trans.appendChild(comission);

				Element blockAddress = doc.createElement("blockAddress");
				blockAddress.appendChild(doc.createTextNode(temp.getBlockAddress()));
				trans.appendChild(blockAddress);

				// 3)
				transactions.appendChild(trans);

			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			if (decoded.contains(".jar"))
				decoded = decoded.substring(0, decoded.lastIndexOf("/"));
			else
				decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));

			StreamResult result = new StreamResult(new File(decoded + "\file.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	/**
	 * reads json from flipCoin transfer
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static void receiveJSON() throws UnsupportedEncodingException {
		ArrayList<Transaction> results = new ArrayList<>();
		JSONParser parser = new JSONParser();

		try {

			// gets the json and puts it inside OBJECT
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			if (decoded.contains(".jar"))
				decoded = decoded.substring(0, decoded.lastIndexOf("/"));
			else
				decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
			Object obj = parser.parse(new FileReader(decoded + "\\JSON.txt"));

			// an array of json object was sent from flip coin transfer
			JSONArray jsonObjectArray = (JSONArray) obj;

			@SuppressWarnings("unchecked")
			Iterator<JSONObject> it = jsonObjectArray.iterator();

			// translate the json array to transaction array
			while (it.hasNext()) {
				JSONObject jsonObject = (JSONObject) it.next();
				results.add(new Transaction((String) jsonObject.get("ID"), (Double) jsonObject.get("Size"),
						Type.valueOf((String) jsonObject.get("Type")), (Double) jsonObject.get("Comission"), null));

			}

			// adds transactions to DB
			TransactionLogic.insertNewTransactions(results);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void receiveRiddles() {
		try {
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			if (decoded.contains(".jar"))
				decoded = decoded.substring(0, decoded.lastIndexOf("/"));
			else
				decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));

			File fXmlFile = new File(decoded + "/RiddlesForMining.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("Riddle");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					RiddleLogic.addRiddle(new Riddle(
							Integer.parseInt(eElement.getElementsByTagName("RiddleNum").item(0).getTextContent()), null,
							eElement.getElementsByTagName("Description").item(0).getTextContent(), null, null, null));

				}
			}

			nList = doc.getElementsByTagName("Solution");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					RiddleLogic.addSoution(
							Integer.parseInt(eElement.getElementsByTagName("RiddleNum").item(0).getTextContent()),
							Integer.parseInt(eElement.getElementsByTagName("SolutionNum").item(0).getTextContent()),
							eElement.getElementsByTagName("Result").item(0).getTextContent());

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void updateToExported(Transaction trans) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			try {
				Connection conn = DriverManager.getConnection(Consts.CONN_STR);
				CallableStatement stmt = conn
						.prepareCall("UPDATE tblTransaction SET exported = true WHERE TranscationId = ?");

				stmt.setString(1, trans.getID());
				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
