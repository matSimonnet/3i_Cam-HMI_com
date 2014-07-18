package camHMICom;

import java.io.*; 
import java.net.*;

public class HMISender  {

	/*
	 * example of HMI sender for 3i HMI
	 * packets are sent on port 17001
	 * by keyboard entry
	 * @param args
	 * @throws Exception
	 */
	
	
	public static void main(String[] args) throws Exception{
		
	//To catch keyboard input
	BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	
	//Create Socket 
	DatagramSocket socket = new DatagramSocket(); 
	
	//Set IP
	InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
	
		
		//MAIN LOOP
		while(true){
				
			//To send
			byte[] sendData = new byte[1024];
				
			//Catch keyboard input
			String sentence = keyboard.readLine();
	
				//usefuls commands
		
				//Initialize communication (footprint begins to be sent on port 17002)
				// "registration,hello,over 
		
				//Set a point to geolock at 48.0000N and 4.0000W
				// "geolock_setpoint,48_-4_0,over
				
				//close communication (footprint stops to be sent on port 17002)
				// "registration,hello,over 
			
			//Encode characters to bytes
			sendData = sentence.getBytes();
			
			//Send packet
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 17001);
			socket.send(sendPacket);
			
		}
	
		//should be closed
		//socket.close();
		//writer.close();
	}
	
}
