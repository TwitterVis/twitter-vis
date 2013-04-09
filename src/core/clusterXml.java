
/*
 * Perform clustering by topic using the algorithim
 * Reads xml file and parses to 2d string array
 * Returns arraylist of clusters containing the tweets
*/

package core;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.carrot2.clustering.stc.STCClusteringAlgorithm;
import org.carrot2.core.Cluster;
import org.carrot2.core.Controller;
import org.carrot2.core.ControllerFactory;
import org.carrot2.core.Document;
import org.carrot2.core.ProcessingResult;
import org.xml.sax.SAXException;

public class clusterXml implements IResource {
    
    public clusterXml()
    {
     
    }
        public  ArrayList <clusterObject> clusterXmlFile(String url) throws IOException, ParserConfigurationException, SAXException
        {
            ReadXMLFile r = new ReadXMLFile();
            String[][] data = r.returnTeewts(url);
            System.out.println("Tweets converted");


            /* Prepare Carrot2 documents */
            final ArrayList<Document> documents = new ArrayList<Document>();
            
            for (String[] row : data)
            {
                documents.add(new Document(row[1], row[2], row[0]));
            }
            
            System.out.println("tweets prepared");
            
            /* A controller to manage the processing pipeline. */
            final Controller controller = ControllerFactory.createSimple();

            /*
             * Perform clustering by topic using the Lingo algorithm. Lingo can
             * take advantage of the original query, so we provide it along with
             * the documents.
             */
            final ProcessingResult byTopicClusters = controller.process(
                    documents, "  ", STCClusteringAlgorithm.class);
            final List<Cluster> clustersByTopic = byTopicClusters.getClusters();
            
             ArrayList <clusterObject> listClusters = new ArrayList <clusterObject>();//array to hold clusters
             //get cluster
             //get cluster details
             //get all docs of that cluster
             //for each doc get details
             for(int i=0; i<clustersByTopic.size(); i++)
             {
                Cluster c = clustersByTopic.get(i);
                String clusterLabel= c.getLabel();
                List<Document> docs =clustersByTopic.get(i).getDocuments();
                int numDocs = docs.size();
                
               ArrayList <documentObject> listDocuments = new ArrayList <documentObject>();//array to hold tweets
                 for(int j=0; j<numDocs; j++)
                 {
                    Document d = docs.get(j);
                    String docTitle = d.getTitle();
                    String docSummary = d.getSummary();
                    documentObject docObj= new documentObject(docTitle,docSummary);
                    listDocuments.add(docObj);
                 }
                 clusterObject clusObj = new clusterObject(clusterLabel,numDocs,listDocuments);
                 listClusters.add(clusObj);
               
             }

            /*
             * Perform clustering by domain. In this case query is not useful,
             * hence it is null.
             */
            System.out.println("clustering");
            //displays clsuters to console
           // ConsoleFormatter.displayClusters(clustersByTopic);
            return listClusters;
        }  
        }


