import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ReadXMLFile{
	
	public ReadXMLFile()
	{
		
	}

	public String[][] returnTeewts() throws ParserConfigurationException, SAXException, IOException
	{
			File fXmlFile = new File("src//twitter.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			String tweets[][] = new String[4000][3];
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("document");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					//						tweets.get(temp).add(eElement.getElementsByTagName("title").item(0).getTextContent());
					//						tweets.get(temp).add(eElement.getElementsByTagName("url").item(0).getTextContent());
					//						tweets.get(temp).add(eElement.getElementsByTagName("snippet").item(0).getTextContent());
					tweets[temp][0]=(eElement.getElementsByTagName("title").item(0).getTextContent());
					tweets[temp][1]=(eElement.getElementsByTagName("url").item(0).getTextContent());
					tweets[temp][2]=(eElement.getElementsByTagName("snippet").item(0).getTextContent());
				}
			}
			//				System.out.println(tweets[0][0]);
			//				System.out.println(tweets[0][1]);
			//				System.out.println(tweets[0][2]);

		return tweets;
	}
}

