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
    	//Crawler.walk("http://www.vfx.lewebdev.com/a.html",2);
    	
    	/*try
		{
			new Crawler2().crawl("http://www.calstatela.edu/",2);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	/*try
		{
			new Crawler2().crawl(args[0],Integer.parseInt(args[1]));
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	
    	//Crawl.parse("http://nlp.stanford.edu/IR-book/pdf/irbookonlinereading.pdf");
    	/*if(args[0]!= null)
    	{
    		int depth = Integer.parseInt(args[1]);
    		System.out.println( "Hello " + args[1] );
    		
    		
    		Crawler.walk(args[0],depth);
    	}*/
    	
    	
    	
    	
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