package Crawler;

import gnu.getopt.Getopt;

class test {
	
	
	
	 public static void main(String[] args) {  // test the class
		 Getopt g = new Getopt("testprog", args, "ab:c::d");
		 //
		 int c;
		 String arg;
		 while ((c = g.getopt()) != -1)
		   {
		     switch(c)
		       {
		          case 'a':
		          case 'd':
		            System.out.print("You picked " + (char)c + "\n");
		            break;
		            //
		          case 'b':
		          case 'c':
		            arg = g.getOptarg();
		            System.out.print("You picked " + (char)c + 
		                             " with an argument of " +
		                             ((arg != null) ? arg : "null") + "\n");
		            break;
		            //
		          case '?':
		            break; // getopt() already printed an error
		            //
		          default:
		            System.out.print("getopt() returned " + c + "\n");
		       }
		   }
		 
	    }
	
}