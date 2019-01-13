package control;

import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;

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

import Utils.Consts;
import Utils.Type;
import entity.Transaction;

public abstract class Communication {

	
	/**
	 * sends executed transactions to flipCoin Transfer via XML  
	 * @throws UnsupportedEncodingException 
	 */
	public static void sendXml() throws UnsupportedEncodingException {
		try {

			ArrayList<Transaction> DBtrans = TransactionLogic.getAllexecuted();

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
				
				//1)
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
			
				//3)
				transactions.appendChild(trans);

			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
			StreamResult result = new StreamResult(new File(decoded + "\\file.xml"));

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
	 */
	public static void receiveJSON() {
		ArrayList<Transaction> results = new ArrayList<>();
		JSONParser parser = new JSONParser();

		try {
			
			//gets the json and puts it inside OBJECT 
			Object obj = parser.parse(new FileReader(
                    "JSON.txt"));

			// an array of json object was sent from flip coin transfer 
			JSONArray jsonObjectArray = (JSONArray) obj;
			
			
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> it = jsonObjectArray.iterator();
			
			//translate the json array to transaction array 
			while (it.hasNext()) {
				JSONObject jsonObject = (JSONObject) it.next();
				results.add(new Transaction((String)jsonObject.get("ID"),(Double) jsonObject.get("Size"),Type.valueOf((String) jsonObject.get("Type")), (Double)jsonObject.get("Comission"), null));
				
			}
			
			//adds transactions to DB
			TransactionLogic.insertNewTransactions(results);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
