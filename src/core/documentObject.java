package core;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sean
 */
public class documentObject {
    public String url;
    public String snippet;
    
    public documentObject(String u, String s)
    {
        url=u;
        snippet=s;
    }
    public String getUrl()
    {
       return url; 
    }
    
     public String getSnippet()
    {
       return snippet; 
    }
     public void printDocument()
     {
         System.out.println("Document Topic: "+snippet+" Url: "+url);
     }
    
}
