package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class PasiveServer extends Thread{

	private boolean isOn;
	private int port;
	private MulticastSocket listenSocket;
	private byte[] buf;
	private DatagramPacket msgPacket;
	
	public PasiveServer(){
		
	}
	
	public void startPasiveServer() throws IOException {
		isOn = true;
		this.start();
	}
	
	public void stopPasiveServer() {
		isOn = false;
		this.interrupt();
	}
	
	public void setConexionDetails(int port) throws IOException{
		
		this.port = port;
		
		listenSocket = new MulticastSocket(port);
		listenSocket.setBroadcast(true);
		listenSocket.joinGroup(InetAddress.getByName("230.0.0.1"));
		
		buf = new byte[256];
	}
	
	public void run()  {
		
		while (isOn) {
			
			msgPacket = new DatagramPacket(buf, buf.length);
			try {
				listenSocket.receive(msgPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}

			 String msg = new String(buf, 0, buf.length);
			 System.out.println("Socket 1 received msg: " + msg);

				
		}
	}	
    
	public synchronized void restart() throws InterruptedException{
		this.notify();
	}
	
	public synchronized void pause() throws InterruptedException{
		this.wait();
	}


}
