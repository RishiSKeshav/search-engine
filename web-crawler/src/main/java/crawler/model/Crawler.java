package cs454.webCrawler.webCrawler;

import java.io.IOException;
import java.net.URL;
import java.security.DomainCombiner;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.nutch.util.domain.DomainSuffixes;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Crawler
{
	
	static int flag=0;
	static URL baseURL;
	private static Pattern IP_PATTERN = Pattern
		      .compile("(\\d{1,3}\\.){3}(\\d{1,3})");

	static int count=0;
	public static void walk(String url,int depth) {
		
		 
		/*http://jsoup.org/cookbook/extracting-data/example-list-links  */		
		try
		{		
			Map<String,Object> metadata =new HashMap<String, Object>();
			
			Document doc = Jsoup.connect(url).get();
	        Elements links = doc.select("a[href]");
	        Elements meta = doc.select("meta");		
			
	        System.out.println("\n");
	        System.out.println(url);
	        
	        
	        /*for(Element link: links){
	        	System.out.println(link.attr("abs:href"));
	        }*/
	        
	        
	        for(Element m : meta)
	        {
	        	System.out.println("Name: " + m.attr("name") + " Content: " + m.attr("content"));
	        }
	        
	        
			if(flag==0){		
				baseURL =new URL(doc.baseUri());
				flag=1;
			}
			
			for(Element link:links){
	        	URL innerURL = new URL(link.attr("abs:href"));
	        	

	        	if(baseURL.getHost().substring(baseURL.getHost().indexOf('.')+1)
	        			.equals(innerURL.getHost().substring(innerURL.getHost().indexOf('.')+1))){
	        		if(count<depth){
	        			count++;
	        			walk(innerURL.toString(),depth);
	        			count--;
	        		}    			
	        	}
			}
			
	        /*for(Element link: links){
	        	System.out.println(link.attr("abs:href"));
	        }
	        
	        for(Element m : meta){
	        	System.out.println("Name: " + m.attr("name") + " Content: " + m.attr("content"));
	        }
	        
	        
	        Elements images = doc.select("[src]");
	        Elements imports = doc.select("link[href]");
	        
	        metadata.put("innerLinks", links);
	    	        
	        
	        Storage.save(metadata);*/	        		
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
		  /**
		   * Returns the domain name of the url. The domain name of a url is the
		   * substring of the url's hostname, w/o subdomain names. As an example <br>
		   * <code>
		   *  getDomainName(conf, new URL(http://lucene.apache.org/))
		   *  </code><br>
		   * will return <br>
		   * <code> apache.org</code>
		   * */
		  public static String getDomainName(URL url) {
		    DomainSuffixes tlds = DomainSuffixes.getInstance();
		    String host = url.getHost();
		    // it seems that java returns hostnames ending with .
		    if (host.endsWith("."))
		      host = host.substring(0, host.length() - 1);
		    if (IP_PATTERN.matcher(host).matches())
		      return host;

		    int index = 0;
		    String candidate = host;
		    for (; index >= 0;) {
		      index = candidate.indexOf('.');
		      String subCandidate = candidate.substring(index + 1);
		      if (tlds.isDomainSuffix(subCandidate)) {
		        return candidate;
		      }
		      candidate = subCandidate;
		    }
		    return candidate;
		  }
	
	private static Map<String, Object> parse(String url) {
		Map<String,Object> metadata =new HashMap<String, Object>();
		
		Document doc;
		try
		{
		doc = Jsoup.connect(url).get();
		
        Elements links = doc.select("a[href]");
        Elements meta = doc.select("meta");
        
        for(Element link: links){
	        System.out.println(link.attr("abs:href"));
	    }
	        
	    for(Element m : meta){
	       	System.out.println("Name: " + m.attr("name") + " Content: " + m.attr("content"));
	    }
	        
	    metadata.put("innerLinks", links);
        
        
        /*print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }        
*/		
		return metadata;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
	
   
}
