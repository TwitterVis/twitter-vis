package core;

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

public class ReadXMLFile {

    public String[][] returnTweets(String url) throws ParserConfigurationException, SAXException, IOException {
        File fXmlFile = new File(url); // Path to XML file.

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        
        ArrayList<ArrayList<String>> arrayList = new ArrayList();

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("document");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                ArrayList<String> innerList = new ArrayList();
                innerList.add((eElement.getElementsByTagName("title").item(0).getTextContent()));
                innerList.add((eElement.getElementsByTagName("url").item(0).getTextContent()));
                innerList.add((eElement.getElementsByTagName("snippet").item(0).getTextContent()));
                arrayList.add(innerList);
            }
        }
        
        int numDocs = arrayList.size();
        System.out.println("Number of documents: " + numDocs);
        
        String tweets[][] = new String[numDocs][3];
        
        for (int i = 0; i < numDocs; i++) {
            ArrayList<String> innerList = arrayList.get(i);
            tweets[i][0] = innerList.get(0);
            tweets[i][1] = innerList.get(1);
            tweets[i][2] = innerList.get(2);
        }
        return tweets;
    }
}
