package control;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import entity.Transaction;

public abstract class Communication {

	public static void sendXml() {
		try {

			ArrayList<Transaction> DBtrans = TransactionLogic.getAllexecuted();

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element transactions = doc.createElement("Transcation");
			doc.appendChild(transactions);

			for (Transaction temp : DBtrans) {
				// staff elements
				Element trans = doc.createElement("Transaction");
				Element id = doc.createElement("transId");
				id.appendChild(doc.createTextNode(temp.getID()));
				trans.appendChild(id);
				Element size = doc.createElement("size");
				size.appendChild(doc.createTextNode(Integer.toString(temp.getSize())));
				trans.appendChild(size);
				Element type = doc.createElement("type");
				type.appendChild(doc.createTextNode(temp.getType().toString()));
				trans.appendChild(type);
				Element comission = doc.createElement("comission");
				comission.appendChild(doc.createTextNode(Integer.toString(temp.getCommission())));
				trans.appendChild(comission);
				Element blockAddress = doc.createElement("blockAddress");
				blockAddress.appendChild(doc.createTextNode(temp.getAddress()));
				trans.appendChild(blockAddress);
				transactions.appendChild(trans);

			}

			

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("C:\\UzFlipCoin-Mining\\file.xml"));

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
}
