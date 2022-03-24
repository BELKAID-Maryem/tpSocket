package Client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {
  public static void main(String[] args) throws Exception {
    System.out.println("je me conecte au server .");
    Socket s = new Socket("localhost",1234);
    InputStream is =s.getInputStream();
    OutputStream os = s.getOutputStream();
    //saisir un nbr
    
    Scanner scanner = new Scanner(System.in);
    System.out.print("Donner un nombre: ");
    int nbr=scanner.nextInt();
    
    System.out.println("J'envoie le nombre "+nbr+" au server");
    os.write(nbr);
    System.out.println("J'attend la reponse du server...");
    int rep=is.read();
    System.out.println("la reponse du server est: "+rep);
    
    
    
}
}