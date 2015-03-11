package Crawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
 
import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;





import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class FileCrawler {
	static String furl;
	static int count=0;
	public static void main(String[] args) throws IOException ,HttpStatusException {
 
		File dir = new File(".");
		String loc = dir.getCanonicalPath() + File.separator + "record.txt";
		FileWriter fstream = new FileWriter(loc, true);
		BufferedWriter out = new BufferedWriter(fstream);
		out.newLine();
		out.close();
		a1("http://www.vfx.lewebdev.com/a.html");
		
		File file = new File(loc);
 
		if (file.delete()) {
 
		}
	}
	
	public static void a1(String url) {
		furl=getUrlDomainName(url);	
		try {
			processPage(url);
		} catch (HttpStatusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	public static String getUrlDomainName(String url) {
		  String domainName = new String(url);

		  int index = domainName.indexOf("://");
		  try {

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
		  System.out.println("dn="+domainName);
		  
		  }
		  catch(ArrayIndexOutOfBoundsException e ){
			  
		  }
		  return domainName; 
		}
	
	
	// givn a String, and a File
	// return if the String is contained in the File
	public static boolean checkExist(String s, File fin) throws IOException {
 
		FileInputStream fis = new FileInputStream(fin);
		// //Construct the BufferedReader object
		BufferedReader in = new BufferedReader(new InputStreamReader(fis));
 
		String aLine = null;
		while ((aLine = in.readLine()) != null) {
			// //Process each line
			if (aLine.trim().contains(s)) {
				//System.out.println("contains " + s);
				in.close();
				fis.close();
				return true;
			}
		}
 
		// do not forget to close the buffer reader
		in.close();
		fis.close();
 
		return false;
	}
 
	
	
	
	
	
	public static void processPage(String URL) throws IOException ,HttpStatusException{
		//Response response = Jsoup.connect(URL).followRedirects(true).execute();
		File dir = new File(".");
		String loc = dir.getCanonicalPath() + File.separator + "record.txt";
		//Response response = Jsoup.connect(URL).followRedirects(true).execute();
		// invalid link
		if (URL.contains(".pdf") || URL.contains("@") 
				|| URL.contains("adfad") || URL.contains(":80")
				|| URL.contains("fdafd") || URL.contains(".jpg")
				|| URL.contains(".pdf") || URL.contains(".jpg"))
			return;
 
		// process the url first
		/*
		if (URL.contains("lewebdev.com") && !URL.endsWith("/")) {
 
		} else if(URL.contains("lewebdev.com") && URL.endsWith("/")){
			URL = URL.substring(0, URL.length()-1);
		}else{
			// url of other site -> do nothing
			return;
		}
 */
		if(!furl.equals(getUrlDomainName(URL))){
		count++	;
			
		}
		
	
		
		File file = new File(loc);
 
		// check existance
		boolean e = checkExist(URL, file);
		if (!e) {
			System.out.println("------ :  " + URL);
			// insert to file
			FileWriter fstream = new FileWriter(loc, true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(URL);
			out.newLine();
			out.close();
 
			Document doc = null;
			try {
				doc = Jsoup.connect(URL).get();
				//doc = response.parse();
			} catch (IOException e1) {
				e1.printStackTrace();
				return;
			}
 
			if (doc.text().contains("PhD")) {
				//System.out.println(URL);
			}
 
			Elements questions = doc.select("a[href]");
			for (Element link : questions) {
				/*
				if(!furl.equals(getUrlDomainName(link.attr("abs:href")))) {
					System.out.println("furl="+furl);
					System.out.println("nurl="+getUrlDomainName(link.attr("abs:href")));
					System.out.println("count="+count);
					count++;
				
				}
				*/
				//System.out.println(response.statusCode());
				processPage((link.attr("abs:href")).toString());
				System.out.println(count);
			}
		} else {
			// do nothing
			return;
		}
 
	}
}