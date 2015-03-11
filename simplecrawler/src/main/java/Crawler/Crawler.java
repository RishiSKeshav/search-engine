package Crawler;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Crawler
{
	static int count=0;
	static String furl;
	public static void main(String[] args) throws  IOException{
		another("http://www.calstatela.edu");
		
	}
	
	
	public static void another(String url1) throws IOException{
		final ArrayList<String> result = new ArrayList<String>();
		furl=getUrlDomainName(url1);
		processlinks(url1);
		
		
	}
	
	


	
	public static String getUrlDomainName(String url) {
		  String domainName = new String(url);

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
	
	
	public static void processlinks(String url) throws  IOException {
		 
		Document doc=null;
		try
		{
			doc = Jsoup.connect(url).userAgent("Mozilla").get();
               
               
			String title = doc.title();
			System.out.println(title);
			System.out.println(getUrlDomainName(url));
			Elements links = doc.select("a[href]");
			
			
			for(Element link: links)
			{
				if(furl.equals(getUrlDomainName(link.attr("abs:href")))) {
					
					//processlinks(link.attr("abs:href")); 
					
				} 
				else {
					
				count++;	
				}
				System.out.println(link.attr("abs:href"));
				processlinks((link.attr("abs:href")).toString()); 
				
				
				System.out.println(link.attr("abs:href"));
			//System.out.println(count);
				//System.out.println(getUrlDomainName(anchor.attr("abs:href")));
		//		if(anchor.attr("abs:href")!=null || anchor.attr("abs:href")!="#") {
					//System.out.println("in="+anchor.attr("abs:href"));
		//if(anchor.attr("href").contains(getUrlDomainName(anchor.attr("abs:href")))) {
			
					}
			//}
					//System.out.println(anchor.attr("abs:href"));
			//}
			//result.add(anchor.attr("abs:href"));
			//}
		
			
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
		
	}


