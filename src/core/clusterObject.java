package core;


import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
    public void printCluster()
    {
        System.out.println("Cluster Topic: "+clusterTopic+" Number Docs: "+numDocs);
    }
    
}
