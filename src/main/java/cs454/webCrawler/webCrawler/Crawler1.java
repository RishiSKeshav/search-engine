package cs454.webCrawler.webCrawler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLHandshakeException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler1
{
	int count =0;
	List<String> LinksSet = new ArrayList<String>();
	int flag=0;
	String Dname="";
	
	void getCawling(String URL) throws SSLHandshakeException {
		try {
			//uuid=UUID.randomUUID();
			/*if(URL.toLowerCase().contains(".pdf"))
			{
				System.out.println("PDF "+URL.toLowerCase());
			URL website = new URL(URL);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(".\\CrawlerStorage\\"+uuid.toString()+".pdf");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			return;
			}*/
			if(flag==0){
				
				Dname = getDomainName(URL);
				flag=1;
			}
			
			if(count>2)
				return;
			
			if (LinksSet.contains(URL)) {
				return;
			}else{
				LinksSet.add(URL);
				java.net.URI objurl = new java.net.URI(URL);
				String dname = objurl.getHost();
				dname = getDomainName(dname);
				if (!dname.equalsIgnoreCase(this.Dname))
					return;
				Document doc = Jsoup.connect(URL.toString()).get();
				//System.out.println(URL);
				//count++;
				System.out.println(URL);
				
				new Crawl().parse(URL);
				Elements questions = doc.select("a[href]");
				System.out.println("----------------------------------------------------------------------");
				/*for(Element meta : doc.select("meta")) {
				    System.out.println("Name: " + meta.attr("name") + " - Content: " + meta.attr("content"));
				}*/
				for (Element link : questions) {
						getCawling(link.attr("abs:href"));
						
				}
				count++;
			}
		//printUrl(LinksSet);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
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
