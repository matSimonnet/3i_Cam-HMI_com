package camHMICom;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CamSenderSimulator  {

	/*
	 * example of cam sender for 3i HMI
	 * packets are sent on port 17002
	 * Nothing is received
	 */
	
	
	public static void main(String[] args) throws Exception{

		//Create Socket 
		DatagramSocket socket = new DatagramSocket();
		
		//Set IP
		InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
		
		//reading file line by line in Java using BufferedReader       
        FileInputStream fis = null;
        BufferedReader reader = null;
		
        //MAIN LOOP
	        while(true){
	        
				try {
					//create inpustream
					fis = new FileInputStream("./record2Replay.txt");
					reader = new BufferedReader(new InputStreamReader(fis));
					
					//create non null string
					String line = reader.readLine();
					
						//10 hertz frenquency loop to read line by line and send at 
						while(line != null){
							
							//fill data
							byte[] sendData = new byte[1024];
							sendData = line.getBytes();
							
							//send data
							DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 17002);
							socket.send(sendPacket);							
			                System.out.println(line);
			                
			                //read next line
			                line = reader.readLine();
			                
							//tempo
							Thread.sleep(100);			
							
			            }     
					
				} catch (FileNotFoundException ex) {
					 Logger.getLogger(CamSenderSimulator.class.getName()).log(Level.SEVERE, null, ex);
					 
				} catch (IOException ex) {
					 Logger.getLogger(CamSenderSimulator.class.getName()).log(Level.SEVERE, null, ex);
					 
				} finally {
					
		            try {
		                reader.close();
		                fis.close();
		            } catch (IOException ex) {
		                Logger.getLogger(CamSenderSimulator.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        
			}
			
		}
		//should be closed when exiting the loop
		//socket.close();
	}
	
}
