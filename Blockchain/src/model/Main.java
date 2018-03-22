package model;

import java.io.IOException;

import javax.swing.SwingUtilities;

import network.ActiveServer;
import network.NodeDetector;
import network.PasiveServer;


/**
 * This is the main method, called to compile the program
 * @version 1.0
 * @author Guillem Garcia Sabate
 */
public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				PasiveServer pas = new PasiveServer();
				try {
					pas.setConexionDetails(666);
					pas.startPasiveServer();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				NodeDetector ND = new NodeDetector(pas);
				
				ActiveServer cli = new ActiveServer(ND);				
				cli.startActiveServer(666);
	
				
			}
		});
	}
}
