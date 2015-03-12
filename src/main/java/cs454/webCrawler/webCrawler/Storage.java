package cs454.webCrawler.webCrawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tika.sax.Link;
import org.apache.tika.sax.ToHTMLContentHandler;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.simple.JSONArray;

public class Storage
{
	static JSONArray jsonArray = new JSONArray();
	
	public static void save(Map<String, Object> metadata, File file1) {
		
		FileWriter fstream;
	    BufferedWriter out;

	    // create your filewriter and bufferedreader
	    try
		{	    	
	    	Map<String,Object> metadata1 = new HashMap<String, Object>();
	    	
	    	metadata1.put("title", metadata.get("title"));
	    	metadata1.put("type", metadata.get("type"));
	    	metadata1.put("met", metadata.get("met"));
	    	metadata1.put("url", metadata.get("url"));
		    metadata1.put("last pulled", metadata.get("last pulled"));
		    metadata1.put("localpath", metadata.get("localpath"));	    	
	    	
		   // System.out.println(metadata1.get("localpath"));
		    
	    	List<Link> links =(List<Link>) metadata.get("links");
	    	Map<Integer,Object> linksToPut = new HashMap<Integer, Object>();
	    	
	    	
	    	/*ArrayList<String> linksToPut = new ArrayList<String>();	  */  	
	    	int i=0;
	    	
	    	for(Link l : links){
	    		i++;
				JSONObject linkObj = new JSONObject();
				
				linkObj.put("Anchor", l.getText());
				linkObj.put("targetURL", l.getUri());
				/*
				System.out.println("Anchor: " + linkObj.get("Anchor"));
				System.out.println("targetURL: "+ linkObj.get("targetURL"));*/
				
				Gson prettyGson1 = new GsonBuilder().setPrettyPrinting().create();
				Gson uglyJson1 = new Gson();
				String pretJson1 = prettyGson1.toJson(linkObj);
				
				linksToPut.put(i,pretJson1);
	    	}
	    	
	    	metadata1.put("links", linksToPut);
	    	
			JSONObject obj = new JSONObject();
			
			for (Map.Entry<String, Object> entry : metadata1.entrySet()) {
					obj.put(entry.getKey(), entry.getValue());
			}
			
			
			
			
			/*http://examples.javacodegeeks.com/core-java/gson/gsonbuilder/enable-pretty-print-json-output-using-gson-example/
*/			
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			Gson uglyJson = new Gson();
			String pretJson = prettyGson.toJson(obj);
			
			jsonArray.add(obj);
			
			//return obj;
		    
			/*String path = file1.getAbsolutePath();
			
			FileWriter file = new FileWriter(path,true);
			file.write(pretJson);
			file.write("\n\n");
			file.flush();
			file.close();
			
			
			System.out.println("Done");*/
	    }
	    catch(Exception e)
	    {
	    	
	    }
	}
	
	
	public static void writeFile(File file1)
	{
		try
		{
			String path = file1.getAbsolutePath();
			
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			Gson uglyJson = new Gson();
			String pretJson = prettyGson.toJson(jsonArray);
			
			
			FileWriter file = new FileWriter(path,true);
			file.write(pretJson.toString());
			file.write("\n\n");
			file.flush();
			file.close();
			
			
			System.out.println("Done");
		}

		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static boolean saveContent(ToHTMLContentHandler toHTMLHandler, File file) {
		
		String path = file.getAbsolutePath();
		
		try
		{
			FileWriter file1 = new FileWriter(path);
		
			file1.write(toHTMLHandler.toString());		
			file1.write("\n\n");
			file1.flush();
			file1.close();
			return true;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println("saving content failed");
			return false;
		}
	}

}
