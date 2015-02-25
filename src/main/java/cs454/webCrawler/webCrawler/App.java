package cs454.webCrawler.webCrawler;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Crawl.parse("http://www.ven.lewebdev.com/");
    	 
    	
    	
    	
    	/*if(args[0]!= null)
    	{
    		int depth = Integer.parseInt(args[1]);
    		System.out.println( "Hello " + args[1] );
    		
    		
    		Crawler.walk(args[0],depth);
    	}*/
    }
}
