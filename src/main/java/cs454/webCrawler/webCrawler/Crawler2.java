package cs454.webCrawler.webCrawler;


import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.Link;
import org.apache.tika.sax.LinkContentHandler;
import org.apache.tika.sax.TeeContentHandler;
import org.apache.tika.sax.ToHTMLContentHandler;
import org.xml.sax.ContentHandler;

public class Crawler2
{
	int count =0;
	List<String> LinksSet = new ArrayList<String>();
	int flag=0;
	String Dname="";
	ArrayList<String> domainName=new ArrayList<String>();
	int flag1=0;
	int flag2=0;
	String Dname1="";
	Map<String,Integer> domainCount = new HashMap<String, Integer>();
	static int i=0;
	
	@SuppressWarnings("static-access")
	public  void crawl(int depth,String url1)
	{
		
		try
		{

			if(flag==0)
			{
			//Dname="mit.edu";
			Dname = getDomainName(url1);
			flag=1;
			}
			
			System.out.println();
			System.out.println("Crawling: " + url1);
			
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
		
		    new Crawl().parse(url1);
		    
		    List<Link> links = linkHandler.getLinks();
		    
		    
		    if(i<300)
		    {
		    
		    if(links !=null && links.size()>0)
		    {
		    	//if(count<depth)
		    		for(Link l:links)
		    		{
		    		/*	System.out.println("link: " + l);
		    			System.out.println("Type: " + l.getType());
		    			System.out.println(l.getUri().startsWith("http"));
		    			System.out.println();*/	    			
		    			
		    			//String innerURLDomain = getDomainName(l.getRel());
		    			if(l.getUri().startsWith("http"))
		    			{
		    				i++;
		    				if(getDomainName(l.getUri()).equals(Dname))
		    				{		    				
		    					if(!LinksSet.contains(l.getUri()))
		    					{	
		    						
		    						/*if(flag2==0)
		    						{
		    							//domainCount.put(getDomainName(l.getUri()), i);
		    							flag2=1;
		    						}
		    						
		    						if(domainCount.containsKey(getDomainName(l.getUri())))
		    						{
		    							i= domainCount.get(getDomainName(l.getUri()));
		    							domainCount.remove(getDomainName(l.getUri()));
		    							i++;
		    							domainCount.put(getDomainName(l.getUri()), i);
		    						}
		    						else
		    							domainCount.put(getDomainName(l.getUri()), i);
		    						
		    						if(i==5)
		    						{
		    							return;
		    						}*/
		    						
		    						
		    						LinksSet.add(l.getUri());
		    						//System.out.println(l.getUri());
		    						crawl(depth,l.getUri());
		    					}
		    				}
		    				else
		    				{
		    					if(flag1==0)
		    					{
		    						Dname1= getDomainName(l.getUri());
		    						flag1=1;
		    					}	    					
		    					
		    					if(count<depth)
		    					{
		    						if(getDomainName(l.getUri()).equals(Dname1))
		    						{
		    							
		    							if(!LinksSet.contains(l.getUri()))
				    					{		
		    								count++;	    		
				    						LinksSet.add(l.getUri());
				    						System.out.println("Current Depth: " + count +"\n");
				    						crawl(depth,l.getUri());
				    					}
		    						}
		    					}
		    					else
		    					{
		    						flag1=0;
		    						count=0;
		    						continue;
		    					}
		    				}
		    			}
		    			else
		    				continue;		    			
		    		}
		    }
		    else
		    	return; 
		    
		}
		}
		catch(Exception e)
		{
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*try
		{
				if(flag==0){
				//Dname="mit.edu";
				Dname = getDomainName(url1);
				flag=1;
			}
			
			if(!domainName.contains(getDomainName(url1)))
			{
				domainName.add(getDomainName(url1));
			}
			
			
			//System.out.println("count: " + count);
			if(count>depth)
				return;
			
			if (LinksSet.contains(url1)) {
				return;
			}else{
				LinksSet.add(url1);
				java.net.URI objurl = new java.net.URI(url1);
				String dname = objurl.getHost();
				
				if(dname!=null){
					//dname="mit.edu";
				dname = getDomainName(dname);				
				
				if (!dname.equalsIgnoreCase(this.Dname)){
					count++;
					return;
				}
			
			
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
		
		    new Crawl().parse(url1);
		    
		    List<Link> links = linkHandler.getLinks();
		    
		    System.out.println("Current depth: " + count);
		    
		    for(Link l:links){
		    	
		    	System.out.println(l.getUri());
		    	crawl(l.getUri(),depth);
		    }
				}
			}
		
		
		
		
		
		}
		catch (  MalformedURLException e1) {		    
		    e1.printStackTrace();
		  }
		
		catch (Exception e){
			e.printStackTrace();
		}*/
	}
	
	private String getDomainName(String URL) {
		 String domainName = new String(URL);

		  int index = domainName.indexOf("://");

		  if (index != -1) {
		    // keep everything after the "://"
		    domainName = domainName.substring(index + 3);
		  }

		  index = domainName.indexOf('/');

		  if (index != -1) {
		    // keep everything before the '/'
		    domainName = domainName.substring(0, index);
		  }

		  // check for and remove a preceding 'www'
		  // followed by any sequence of characters (non-greedy)
		  // followed by a '.'
		  // from the beginning of the string
		  domainName = domainName.replaceFirst("^www.*?\\.", "");
		  domainName=new StringBuffer(domainName).reverse().toString();
		  String[] split = domainName.split("\\.");
		 //domainName=split[0]+split[1]+split[2];
		  ///System.out.println("im"+split[0]+"."+split[1]);
		  domainName=new StringBuffer(split[0]+"."+split[1]).reverse().toString();
		  //System.out.println("dn="+domainName);
		  return domainName;
		
	}

		
		
	
}
