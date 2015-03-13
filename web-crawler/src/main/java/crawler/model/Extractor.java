package cs454.webCrawler.webCrawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Extractor
{
	public static JSONArray jsonArrayToPrint = new JSONArray();
	
	
	public static void main(String[] args)
	{
		File file = new File("I:\\books\\CS454(information Retrieval)\\data\\Crawler\\Extracter.json");
		
		//extract("I:\\books\\CS454(information Retrieval)\\data\\Crawler\\Crawler.json");
		
		String path = "I:\\books\\CS454(information Retrieval)\\data\\Crawler\\" + args[1];
		
		extract(path);
		
		writeFile(file);
	}
	
	@SuppressWarnings("unchecked")
	public static void extract(String path)
	{
		
		
		JSONParser parser = new JSONParser();
		Object object;
		
		
		try
		{
			object = parser.parse(new FileReader(path));
			JSONArray jsonArray = (JSONArray) object;
			
			for(Object o : jsonArray)
			{
				Map<String,Object> meta = new HashMap<String, Object>();
				
				JSONObject jsonObject = (JSONObject) o;
				/*System.out.println("------------------------------------------------------------------");
				System.out.println(jsonObject.get("title"));
				System.out.println(jsonObject.get("last pulled"));*/
				//System.out.println(jsonObject.get("Content-Type"));
				
				JSONObject met =(JSONObject) jsonObject.get("met");
				JSONObject metadata =(JSONObject) met.get("metadata");
				
				//System.out.println(metadata.get("keywords"));
				
				/*System.out.println(metadata.get("keywords"));*/
				
				
				meta.put("title",jsonObject.get("title"));
				meta.put("last pulled",jsonObject.get("last pulled"));
				meta.put("url",jsonObject.get("url"));
				meta.put("localpath",jsonObject.get("localpath"));
				
				String type = metadata.get("Content-Type").toString();
				
				if(type.equals("[\"image\\/jpeg\"]") || type.equals("[\"image\\/png\"]") || type.equals("[\"image\\/gif\"]"))
				{
					meta.put("Content-Type", metadata.get("Content-Type"));
					meta.put("Image Height",metadata.get("Image Height"));
					meta.put("Image Width",metadata.get("Image Width"));
					
					//System.out.println(metadata.get("Image Height"));
				}
				else if(type.equals("[\"application\\/pdf\"]"))
				{
					meta.put("Content-Type", metadata.get("Content-Type"));
					meta.put("date",metadata.get("date"));
					meta.put("Author",metadata.get("Author"));
					meta.put("producer",metadata.get("producer"));
					meta.put("creator",metadata.get("creator"));
				}
				else
				{
					meta.put("Content-Type", metadata.get("Content-Type"));
					meta.put("keywords",metadata.get("keywords"));
				}
				
				jsonArrayToPrint.add(meta);		
			}
			
			
			
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void writeFile(File file1)
	{
		try
		{
			String path = file1.getAbsolutePath();
			
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			Gson uglyJson = new Gson();
			String pretJson = prettyGson.toJson(jsonArrayToPrint);
			
			
			FileWriter file = new FileWriter(path,true);
			file.write(pretJson.toString());
			file.write("\n\n");
			file.flush();
			file.close();
			
			
			System.out.println("Done");
		}
		catch(IOException e)
		{
			
		}
	
	}
}
