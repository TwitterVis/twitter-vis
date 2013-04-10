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

    public ReadXMLFile() {
    }

    public String[][] returnTweets(String url) throws ParserConfigurationException, SAXException, IOException {
        File fXmlFile = new File("src//core//twitter.xml");
        //File fXmlFile = new File(url);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("document");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                ArrayList<String> innerList = new ArrayList<String>();
                innerList.add((eElement.getElementsByTagName("title").item(0).getTextContent()));
                innerList.add((eElement.getElementsByTagName("url").item(0).getTextContent()));
                innerList.add((eElement.getElementsByTagName("snippet").item(0).getTextContent()));
                arrayList.add(innerList);
            }
        }
        int size1 = arrayList.size();
        System.out.println("array size: " + size1);
        String tweets[][] = new String[size1][3];
        for (int i = 0; i < size1; i++) {
            ArrayList<String> innerList = arrayList.get(i);
            tweets[i][0] = innerList.get(0);
            tweets[i][1] = innerList.get(1);
            tweets[i][2] = innerList.get(2);

        }
        return tweets;
    }
}
