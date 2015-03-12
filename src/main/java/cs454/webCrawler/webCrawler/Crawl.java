package cs454.webCrawler.webCrawler;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.Link;
import org.apache.tika.sax.LinkContentHandler;
import org.apache.tika.sax.TeeContentHandler;
import org.apache.tika.sax.ToHTMLContentHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xml.sax.ContentHandler;

public class Crawl
{
	static File file1;
	@SuppressWarnings("deprecation")
	public static void parse(String url1)
	{
		
		
		try
		{
			Map<String,Object> metadata = new HashMap<String, Object>();
			
			Tika tika = new Tika();
			tika.setMaxStringLength(10*1024*1024); 
			Metadata met=new Metadata();
			URL url = new URL(url1);
			
			ToHTMLContentHandler toHTMLHandler=new ToHTMLContentHandler();
			ParseContext parseContext=new ParseContext();
			LinkContentHandler linkHandler = new LinkContentHandler();
			ContentHandler textHandler = new BodyContentHandler(10*1024*1024);
			TeeContentHandler teeHandler = new TeeContentHandler(linkHandler, textHandler, toHTMLHandler);		
			
			AutoDetectParser parser=new AutoDetectParser();
		    parser.parse(url.openStream(),teeHandler,met,parseContext);
		    
		    String title = met.get(Metadata.TITLE);
		    String type = met.get(Metadata.CONTENT_TYPE);
		    
		    System.out.println(type);
		    /*System.out.println(FilenameUtils.getBaseName(url1));
		    System.out.println(FilenameUtils.getExtension(url1));*/
		    
		    /*for(Metadata m : met){
		    	System.out.println(m);
		    }*/
		    
		    /*System.out.println(met.get(Metadata.TITLE));
		    System.out.println(met.get(Metadata.CONTENT_TYPE));
		    System.out.println(url);*/
		    
		    Date date = new Date();
		    
		    /*System.out.println(date);*/
		    
		    UUID uuid = UUID.randomUUID();
		    /*System.out.println(uuid);
		    
		    System.out.println("document");
		    System.out.println(toHTMLHandler);		    
		    System.out.println("Links");*/
		    
		    List<Link> links = linkHandler.getLinks();
		    
		   /* for(Link l: links){
		    	System.out.println("Anchor: " + l.getText());
		    	System.out.println("target URL: " + l.getUri());
		    }*/
		    
		    metadata.put("title",title);
		    metadata.put("type", type);
		    metadata.put("met", met);
		    metadata.put("url", url);
		    metadata.put("last pulled", date);		    
		    metadata.put("links", links);
		    
		    String fileName1 = uuid + ".json";
		    /*String localpath1 = "I:\\books\\CS454(information Retrieval)\\data\\Crawler\\"+ fileName1;*/
		    String localpath1 = "I:\\books\\CS454(information Retrieval)\\data\\Crawler\\Crawler.json";		    
		    file1 = new File(localpath1);
		    
		    String fileName2 = uuid + ".txt";
		    String localpath2 = "I:\\books\\CS454(information Retrieval)\\data\\Content\\"+ fileName2;		    
		    File file2 = new File(localpath2);
		    		    
		    String fileName="";
		    String fileType="";		    
		    
		    if(type.equals("application/vnd.ms-powerpoint"))
		    {
		    	fileType = ".ppt";
		    	fileName= uuid+fileType;
			    String localpath = "I:\\books\\CS454(information Retrieval)\\data\\Content\\"+ fileName;		    
			    File file = new File(localpath); 
			    
			    metadata.put("localpath", file.getAbsolutePath());
		    	boolean flag = Download.downloadFile(url1, localpath);
		    	if(flag==true)
		    		Storage.save(metadata,file1);
		    	
		    }
		    
		    if(type.equals("application/vnd.xml"))
		    {
		    	fileType = ".xml";
		    	fileName= uuid+fileType;
			    String localpath = "I:\\books\\CS454(information Retrieval)\\data\\Content\\"+ fileName;		    
			    File file = new File(localpath);  
			    
			    metadata.put("localpath", file.getAbsolutePath());
		    	boolean flag = Download.downloadFile(url1, localpath);
		    	if(flag==true)
		    		Storage.save(metadata,file1);
		    }
		    
		    if(type.equals("application/vnd.ms-excel"))
		    {
		    	fileType = ".xlsx";
		    	fileName= uuid+fileType;
			    String localpath = "I:\\books\\CS454(information Retrieval)\\data\\Content\\"+ fileName;		    
			    File file = new File(localpath);  
			    
			    metadata.put("localpath", file.getAbsolutePath());
		    	boolean flag = Download.downloadFile(url1, localpath);
		    	if(flag==true)
		    		Storage.save(metadata,file1);
		    }
		    if(type.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
		    {
		    	fileType = ".doc";
		    	fileType = ".xlsx";
		    	fileName= uuid+fileType;
			    String localpath = "I:\\books\\CS454(information Retrieval)\\data\\Content\\"+ fileName;		    
			    File file = new File(localpath);  
		    	
		    	metadata.put("localpath", file.getAbsolutePath());
		    	boolean flag = Download.downloadFile(url1, localpath);
		    	if(flag==true)
		    		Storage.save(metadata,file1);
		    }
		    /*if(type.equals("application/vnd.ms-xpsdocument"))
		    {
		    	fileType = ".ppt";
		    }*/
		    if(type.equals("image/gif"))
		    {
		    	fileType = ".gif";
		    	fileName= uuid+fileType;
			    String localpath = "I:\\books\\CS454(information Retrieval)\\data\\Content\\"+ fileName;		    
			    File file = new File(localpath);  
			    
			    metadata.put("localpath", file.getAbsolutePath());
		    	boolean flag = Download.downloadFile(url1, localpath);
		    	if(flag==true)
		    		Storage.save(metadata,file1);
		    }
		    if(type.equals("image/png"))
		    {
		    	fileType = ".png";
		    	fileName= uuid+fileType;
			    String localpath = "I:\\books\\CS454(information Retrieval)\\data\\Content\\"+ fileName;		    
			    File file = new File(localpath);
			    
			    metadata.put("localpath", file.getAbsolutePath());
		    	boolean flag = Download.downloadFile(url1, localpath);
		    	if(flag==true)
		    		Storage.save(metadata,file1);
		    }
		    
		    if(type.equals("image/jpeg"))
		    {
		    	fileType = ".jpg";
		    	fileName= uuid+fileType;
			    String localpath = "I:\\books\\CS454(information Retrieval)\\data\\Content\\"+ fileName;		    
			    File file = new File(localpath);  
			    
			    metadata.put("localpath", file.getAbsolutePath());
		    	boolean flag = Download.downloadFile(url1, localpath);
		    	if(flag==true)
		    		Storage.save(metadata,file1);
		    }
		    if(type.equals("application/octet-stream"))
		    {
		    	fileType = ".pdf";
		    	fileName= uuid+fileType;
			    String localpath = "I:\\books\\CS454(information Retrieval)\\data\\Content\\"+ fileName;		    
			    File file = new File(localpath);  
			    
			    metadata.put("localpath", file.getAbsolutePath());
		    	boolean flag = Download.downloadFile(url1, localpath);
		    	if(flag==true)
		    		Storage.save(metadata,file1);
		    }
		    if(type.equals("application/pdf"))
		    {
		    	fileType = ".pdf";
		    	fileName= uuid+fileType;
			    String localpath = "I:\\books\\CS454(information Retrieval)\\data\\Content\\"+ fileName;		    
			    File file = new File(localpath);  
			    
			    metadata.put("localpath", file.getAbsolutePath());
		    	boolean flag = Download.downloadFile(url1, localpath);
		    	if(flag==true)
		    		Storage.save(metadata,file1);
		    }
		  
		    
		    if(type.contains("text/html")){
		    	metadata.put("localpath", file2.getAbsolutePath());		    		    	
		    	boolean flag = Storage.saveContent(toHTMLHandler,file2);
		    	if(flag==true)
		    		Storage.save(metadata,file1);	
		    }
		    			
		}
		catch (  MalformedURLException e1) {		    
		    System.out.println("Malformed URl");
		  }
		
		catch (Exception e){
			System.out.println("");
		}
	}
	
	
	public void parse1(String URL)
	{
		try
		{		
			Map<String,Object> metadata =new HashMap<String, Object>();
			
			Document doc = Jsoup.connect(URL).get();
			System.out.println(Jsoup.parse(doc.toString()).text());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public File getFile()
	{
		return file1;
	}

}
