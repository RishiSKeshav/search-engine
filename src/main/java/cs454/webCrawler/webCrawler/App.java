package cs454.webCrawler.webCrawler;

import javax.net.ssl.SSLHandshakeException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//Crawler.walk("http://www.vfx.lewebdev.com/a.html",2);
    	
    	/*try
		{
			new Crawler1().getCawling("http://web.mit.edu/");
		}
		catch (SSLHandshakeException e)
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
    	
    	
    	if(args[0]!=null && args[1]!=null){
    		try
    		{
    			new Crawler1().getCawling(args[0],Integer.parseInt(args[1]));
    		}
    		catch (SSLHandshakeException e)
    		{
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
}