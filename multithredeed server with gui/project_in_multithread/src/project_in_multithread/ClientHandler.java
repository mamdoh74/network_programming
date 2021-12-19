/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_in_multithread;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author CompuHome
 */
public class ClientHandler extends Thread {
    private Socket client;
    private Scanner input;
    private PrintWriter output;
    server_v1 server1=new server_v1();
    serverform s1=new serverform();
    public ClientHandler(Socket socket)
    {
        client=socket;
        try{
            input=new Scanner(client.getInputStream());
            output=new PrintWriter(client.getOutputStream(),true);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public boolean login(String username ,String password)
    {
        boolean login = false;
        File loginf = new File("E:\\login.txt");
                try{
                    Scanner read = new Scanner(loginf);
                    
                    do{
                        
                           String user = read.next();
                           String pass = read.next();
                           
                           System.out.println(user+" ==>"+pass);
                           if(username.equals(user) && password.equals(pass)){
                              login = true;
                              break;                 
                           }
                        }
                    while(read.hasNext());
 
    }
                catch(Exception ex)
                {
                    
                }
                
                return login;
    }
    public void run()
    {
        String username=input.nextLine();
        String password=input.nextLine();
        if(login(username,password))
        output.println("sucessfull login");
        else output.println("not sucessfull login");
        if(login(username,password))
        {
        String received="";
        String send="";
        do {
      received=input.nextLine();
            server1.write_msg(received);
            System.out.println("ECHO: " + received);
            send="ECHO: " + received;
            output.println(send);
            s1.txt_server.setText(server1.getall());
            } while (!received.equals("QUIT"));
       
        try {
            if (client != null) {
            System.out.println("Closing down connection…");
            server1.write_msg("Closing down connection…");
            client.close();
            }
            } 
        catch (IOException ioEx) {
            System.out.println("Unable to disconnect!");
            server1.write_msg("Unable to disconnect!");
                }
        }
        else {
            output.println("invalid username or password");
        }
       
    }
    
    public void close_connection()
    {
        try{
            if(client!=null)
            {
                System.out.println("Closing down connection");
            }
            client.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
