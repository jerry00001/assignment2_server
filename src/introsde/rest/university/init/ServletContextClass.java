package introsde.rest.university.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextClass implements ServletContextListener{
	

       public void contextInitialized(ServletContextEvent arg0){
    	   	
				DatabaseINIT.initializeDB();
			
       
       }//end contextInitialized method


       public void contextDestroyed(ServletContextEvent arg0){
    	   
       }//end constextDestroyed method

}
