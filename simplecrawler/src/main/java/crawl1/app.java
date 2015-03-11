package crawl1;

public class app {
public static void main(String[] args){

	BasicCrawler a1=new BasicCrawler();
	BasicCrawlController a2=new BasicCrawlController();
	
	
	try {
		
		a2.a1("http://www.vfx.lewebdev.com/a.html",6);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}