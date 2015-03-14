package cs454.webCrawler.webCrawler;

import java.io.File;


/**
 * Hello world!
 *
 */
public class App 
{
	public static void write(File file)
	{
		Storage.writeFile(file);
	}
	
	
    public static void main( String[] args )
    {
    	//Crawl.parse("http://www.apple.com/legal/procurement/docs/CLVIF.pdf");
    	
    	
    	//Crawler.walk("http://www.vfx.lewebdev.com/a.html",2);
    	
    	/*try
		{
			new Crawler2().crawl("http://www.apple.com/",2);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	Storage.writeFile(Crawl.file1);*/
    	
    	if(args[1]!=null && args[3]!=null){
    		try
    		{
    			System.out.println("Crawling started with " + args[1]);
    			
    			new Crawler2().crawl(args[1].trim(),Integer.parseInt(args[3]));
    		}
    		catch (Exception e)
    		{
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	
    	Storage.writeFile(Crawl.file1);
    }
}