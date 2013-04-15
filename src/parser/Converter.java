import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Converter {
	
	static File[] listOfFiles;
	public static StringBuilder b = new StringBuilder();
	static int counter = 0;
	
	public static void main(String args[]) throws IOException {
		File folder = new File("C:\\Users\\Matt\\Desktop\\Assignment Files\\sanders-twitter-0.2\\rawdata");
		listOfFiles = folder.listFiles();
		JSONObject[] output = new JSONObject[listOfFiles.length];
		int i=0;
		
		for( File file : listOfFiles) {
			if(file.isFile()) {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				StringBuffer sb = new StringBuffer();
				String eachLine = br.readLine();

				while (eachLine != null) {
				   sb.append(eachLine);
				   eachLine = br.readLine();
				}

				String readFile = sb.toString();
				output[i] = new JSONObject(readFile);
				i++;
			}
		}
		
		for(int k=0; k<listOfFiles.length; k++) {
			parseJSON(output[k].toString());
		}
		writeToXML();
		b.append("</xml>");
	}
	
	public static String JSONtoXML(String text, int followers, int retweet, int counter) {
		
		String encoding = "<?xml version='1.0' encoding='UTF-8'?>";
		
		String docStart = "<document id=\"";
		String docMid = "\">";
		String docEnd = "</document>";
		
		String titleStart = "<title>";
		String titleEnd = "</title>";
		
		String snippetStart = "<snippet>";
		String snippetEnd = "</snippet>";
		
		String urlStart = "<url>";
		String urlEnd = "</url>";
		
		if(counter == 0) {
			b.append(encoding);
		}
		
		b.append(docStart);
		b.append(counter);
		b.append(docMid);
		b.append("\n");
		b.append(titleStart);
		b.append(followers);
		b.append(titleEnd);
		b.append("\n");
		b.append(urlStart);
		b.append(retweet);
		b.append(urlEnd);
		b.append("\n");
		b.append(snippetStart);
		b.append(text);
		b.append(snippetEnd);
		b.append("\n");
		b.append(docEnd);
		b.append("\n");
		
		return b.toString();
	}
	
	public static void parseJSON(String j) {
		JSONObject inputArray = new JSONObject(j);
		
		String text = inputArray.getString("text");
		
		JSONObject arrayOfTests = (JSONObject) inputArray.get("user");
		int followersCount = arrayOfTests.getInt("followers_count");
		
		int retweetCount = inputArray.getInt("retweet_count");
		
		JSONtoXML(text, followersCount, retweetCount, counter);
		counter++;
	}
	
	public static void writeToXML() {
		try { 
			FileWriter fstream = new FileWriter("C:\\Users\\Matt\\Desktop\\Assignment Files\\sanders-twitter-0.2\\master.xml");
			BufferedWriter out = new BufferedWriter(fstream);
			
			out.write(b.toString());
			out.close();
		}
		
		catch(Exception e) {
			System.out.println("Error Occurred");
		}
		
		System.out.println("Done!");
	}
}
