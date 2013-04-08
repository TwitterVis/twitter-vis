
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.carrot2.clustering.lingo.LingoClusteringAlgorithm;
import org.carrot2.clustering.stc.STCClusteringAlgorithm;
import org.carrot2.clustering.synthetic.ByUrlClusteringAlgorithm;
import org.carrot2.core.Cluster;
import org.carrot2.core.Controller;
import org.carrot2.core.ControllerFactory;
import org.carrot2.core.Document;
import org.carrot2.core.ProcessingResult;
import org.xml.sax.SAXException;

public class test implements IResource {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
    {
        {
            ReadXMLFile r = new ReadXMLFile();
            String[][] data = r.returnTeewts();
            System.out.println("Tweets converted");

            final String[][] data2 = new String[][]
            {
                {
                    "http://en.wikipedia.org/wiki/Data_mining",
                    "Data mining - Wikipedia, the free encyclopedia",
                    "Article about knowledge-discovery in databases (KDD), the practice of automatically searching large stores of data for patterns."
                },
                {
                    "http://www.ccsu.edu/datamining/resources.html",
                    "CCSU - Data Mining",
                    "A collection of Data Mining links edited by the Central Connecticut State University ... Graduate Certificate Program. Data Mining Resources. Resources. Groups ..."
                },
                {
                    "http://www.kdnuggets.com/",
                    "KDnuggets: Data Mining, Web Mining, and Knowledge Discovery",
                    "Newsletter on the data mining and knowledge industries, offering information on data mining, knowledge discovery, text mining, and web mining software, courses, jobs, publications, and meetings."
                },
                {
                    "http://en.wikipedia.org/wiki/Data-mining",
                    "Data mining - Wikipedia, the free encyclopedia",
                    "Data mining is considered a subfield within the Computer Science field of knowledge discovery. ... claim to perform \"data mining\" by automating the creation ..."
                },
                {
                    "http://www.anderson.ucla.edu/faculty/jason.frand/teacher/technologies/palace/datamining.htm",
                    "Data Mining: What is Data Mining?",
                    "Outlines what knowledge discovery, the process of analyzing data from different perspectives and summarizing it into useful information, can do and how it works."
                },
            };

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
                    documents, "", LingoClusteringAlgorithm.class);
            final List<Cluster> clustersByTopic = byTopicClusters.getClusters();

            /*
             * Perform clustering by domain. In this case query is not useful,
             * hence it is null.
             */
            System.out.println("clustering");
            //final ProcessingResult byDomainClusters = controller.process(
            //documents, null, ByUrlClusteringAlgorithm.class);
            //final List<Cluster> clustersByDomain = byDomainClusters
            //.getClusters();
            // [[[end:clustering-document-list]]]

            ConsoleFormatter.displayClusters(clustersByTopic);
            //ConsoleFormatter.displayClusters(clustersByDomain);
        }
    }
}
