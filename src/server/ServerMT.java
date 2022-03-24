package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMT extends Thread{
    private int nbrClt=0;
    private boolean isActive=true;
	public static void main(String[] args) {
	   new ServerMT().start();	

	}
   @Override
    public void run() {
	   try {
		ServerSocket ss = new ServerSocket(1234);
	    System.out.println("Demarrage du serveur...");
		while(isActive) {
			Socket s = ss.accept();
			++nbrClt;
			new conversation (s,nbrClt).start();
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
   class conversation extends Thread{
	   private Socket s;
       private int numClt;
	   public conversation(Socket s,int numClt) {
		this.s=s;
		this.numClt=numClt;
	}
	   @Override
	   public void run() {
         try {
			InputStream is = s.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr); 
			OutputStream os = s.getOutputStream();
			PrintWriter pw = new PrintWriter(os,true);
			String IP = s.getRemoteSocketAddress().toString();
			System.out.println("Connexion du client numero: "+numClt+ "  et adresse IP de ce client est: "+IP);
			pw.println("Bien venue vous etes le client numero: "+numClt);
			while(true) {
				String req = br.readLine();
				System.out.println("Le client: "+IP+"a envoye une requete: "+req);
				pw.println(req.length());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   }
}
