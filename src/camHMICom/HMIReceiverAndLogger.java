package camHMICom;

import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HMIReceiverAndLogger  {

	/*
	 * example of HMI receiver for 3i HMI
	 * packets are received on port 17002
	 * sentences are written in the file ./HMIReceiverLog.txt
	 */
	
	public static void main(String[] args) throws Exception{
		
	//Create Socket 
	DatagramSocket socket = new DatagramSocket(17002);
	
	//Set IP
	InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
	
	// to write a file
	PrintWriter writer = new PrintWriter("HMIReceiverLog.txt","UTF-8");

		
		//MAIN LOOP		
		while(true){
			
			//To receive
			byte[] receiveData = new byte[1024];
			
			//Example of attempted data
			/*
			footprint_update,
			48,1176805765743_-4,18382284345162_0\
			48,2493931292703_-4,06568193276624_0\
			48,0315172962663_-4,37383220184628_0\
			48,3564293596268_-4,56855051332869_0\
			48,2493931292703_-4,5658978086772_0,
			over
			*/
			
			//Receive Response
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);       
			socket.receive(receivePacket);
			
			//Encode bytes to characters
			String fromCam = new String(receivePacket.getData());
			
			//write in the log file
			writer.println(fromCam + "");
			writer.flush();
			
			//display received packet
			System.out.println("FROM CAM: " + fromCam);
	
		}

	//should be closed when exiting the loop
	//socket.close();
	//writer.close();
		
	}
	
}
