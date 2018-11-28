package com.framework.utilities;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
public class SendReports

{
	 static List<String> filesListInDir = new ArrayList<String>();
	
		 	File filename=null;
		 	ZIPFile Zip = new ZIPFile();
		 	//ZIPFile Zip1 = new ZIPFile();
	

		public void zipAutomationReports() throws InterruptedException, ParserConfigurationException, IOException, SAXException{
			 filename = new File(System.getProperty("user.dir")+"/Reports");
			 Zip.zipDirectory(filename, "AutomationReports.zip");
			 System.out.println("Zipping of AutomationReports successfully");
			 Thread.sleep(3000);

		}

		public void sendmail() throws ParserConfigurationException, SAXException, IOException
	
		{
			String username=XmlReader.getDataFromXml("MailUsername");
			String password=XmlReader.getDataFromXml("MailPassword");
			String toadd=XmlReader.getDataFromXml("MailTo");
			String ccadd=XmlReader.getDataFromXml("MailCc");
			String bccadd=XmlReader.getDataFromXml("MailBcc");
			String Subject=XmlReader.getDataFromXml("MailSubject");
			
			
			
				  String[] to={toadd};
				  String[] cc={ccadd};
	              String[] bcc={bccadd};
	              SMTPUtility.sendMail(username,
	            		  password,
	              		            "smtp.gmail.com",
	              		            "465",
	              		            "true",
	              		            "true",
	              		            true,
	              		            "javax.net.ssl.SSLSocketFactory",
	              		            "false",
	              		            to,   
	              		            cc,
	              		            bcc,
	              		          Subject,                		            
	              		            "PFA",System.getProperty("user.dir")+"/AutomationReports.zip","AutomationReports.zip");
	            
	              System.out.println("Report has been sent through mail Successfully");
	
				}

		      public void Sharereports() {
		    	  try{
		    		  zipAutomationReports();
		  			sendmail();
		 
		    	  }
		    	  catch(Throwable t) 
		  	    {	
		  		      Error e1 = new Error(t.getMessage()); 
		  		      e1.setStackTrace(t.getStackTrace()); 
		  		      throw e1;
		  	   }	
		    	  
		      }
      
      
      
     
			}
