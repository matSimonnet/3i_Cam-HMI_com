package camHMICom;

import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CamReceiverAndLogger  {

	/*
	 * example of Cam receiver for 3i HMI
	 * packets are received on port 17001
	 * sentences are written in the file ./CamReceiverLog.txt
	 */
	
	public static void main(String[] args) throws Exception{
		
	//Create Socket 
	DatagramSocket socket = new DatagramSocket(17001);
	
	//Set IP
	InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
	
	// to write a file
	PrintWriter writer = new PrintWriter("CamReceiverLog.txt","UTF-8");

		
		//MAIN LOOP		
		while(true){
			
			//To receive
			byte[] receiveData = new byte[1024];
			
			
			//Receive Response
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);       
			socket.receive(receivePacket);
			
			//Encode bytes to characters
			String fromHMI = new String(receivePacket.getData());
			
			//writer.println("Answer : ");
			writer.println(fromHMI + "");
			writer.flush();
			
			//display receive packet
			System.out.println("FROM HMI: " + fromHMI);
	
		}

	//should be closed when exiting the loop
	//socket.close();
	//writer.close();
		
	}
	
}
