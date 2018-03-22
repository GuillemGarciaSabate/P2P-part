package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

public class NodeDetector{
	
	private MulticastSocket udpSock;
	private PasiveServer pas;
	private ArrayList<InetAddress> nodes;
	
	public NodeDetector(PasiveServer pas){
		
		nodes = new ArrayList<InetAddress>();
		this.pas = pas;
		
	}
	
	/**
	  * This method submits the node network details to the active LAN peers
	  * @return void
	  * @param ipAddr ip address of the node
	  * @param port port this node will be listening to
	  */
	public void NodeDiscovery(InetAddress ipAddr, Integer port) throws IOException, InterruptedException{
		
		pas.pause();
		
		udpSock = new MulticastSocket(port);
		udpSock.setBroadcast(true);
		
		udpSock.joinGroup(InetAddress.getByName("230.0.0.1"));
				
		byte[] buf = ("getregisterednodes--"+ipAddr.toString()).getBytes();
		DatagramPacket packet = new DatagramPacket(buf,buf.length,InetAddress.getByName("230.0.0.1"),666);
		System.out.println("nodeDetector");		
		udpSock.send(packet);
		
		udpSock.leaveGroup(InetAddress.getByName("230.0.0.1"));
		udpSock.close();
		
		pas.restart();
		
	}

	public ArrayList<InetAddress> getNodes(){
		return nodes;
	}
}










