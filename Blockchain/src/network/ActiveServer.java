package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ActiveServer extends Thread{
	
	private InetAddress ipAddr;
	private long createdMillis;
	private boolean isOn;
	private int port;
	private NodeDetector ND;

	
	public ActiveServer(NodeDetector ND){
		try {
			ipAddr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.ND = ND;
		isOn = false;
	}
	
	public void startActiveServer(int port) {

		this.port = port;
		isOn = true;
		createdMillis = System.currentTimeMillis();
		this.start();
		
	}
		
	public void run()  {
		while(isOn){
			
			int seconds = this.getAgeInSeconds();
			if(seconds>=3)
			{
				try {
					ND.NodeDiscovery(ipAddr,port);
					createdMillis = System.currentTimeMillis();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("3 segons");
				
			}
			
		}		
	}
	
	public int getAgeInSeconds() {
	    long nowMillis = System.currentTimeMillis();
	    return (int)((nowMillis - this.createdMillis) / 1000);
	}
	


}
