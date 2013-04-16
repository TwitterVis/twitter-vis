package core;


import java.util.ArrayList;

/*
 *wrapper class for clsuters of tweets 
 */

/**
 *
 * @author Sean
 */
public class clusterObject {
    public String clusterTopic;
    public int numDocs;
    public ArrayList<documentObject> documents;
    
    public clusterObject(String c, int n, ArrayList<documentObject>d)
    {
       clusterTopic=c;
       numDocs=n;
       documents=d;  
    }
    
    public String getTopic()
    {
        return clusterTopic;
    }
    public int getNumDocuments()
    {
        return numDocs;
    }
     public int getNumberRetweets()
    {
        int result=0;
        for (int i = 0; i < documents.size(); i++) {
            result+=Integer.parseInt(documents.get(i).url);
        }
       return result;
    }
    public void printCluster()
    {
        System.out.println("Cluster Topic: "+clusterTopic+" Number Docs: "+numDocs);
    }
    public ArrayList<documentObject> getDocumentArray()
    {
        return documents;
    }
    
}
