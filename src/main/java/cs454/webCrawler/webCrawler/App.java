package cs454.webCrawler.webCrawler;


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
			new Crawler2().crawl("http://www.vfx.lewebdev.com/a.html",2);
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
    	
    	
    	
    	
    	if(args[0]!=null){
    		try
    		{
    			System.out.println(args[0]);
    	    	System.out.println(args[1]);
    			new Crawler2().crawl(args[0].trim(),Integer.parseInt(args[1]));
    		}
    		catch (Exception e)
    		{
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
}