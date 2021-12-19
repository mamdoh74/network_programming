/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_in_multithread;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author CompuHome
 */
public class server_v1 {
    private static ServerSocket serverSocket;
    private static final int PORT = 4444;
    private static FileWriter fw;
    public void open_server()
    {
        setport();
        accept_incomming_connections();
    }
    
    public void setport()
    {
        try {
          serverSocket = new ServerSocket(PORT);
            } 
        catch (IOException ioEx) {
            System.out.println("\nUnable to set up port!");
            write_msg("Unable to set up port!");
            System.exit(1);
 }
    }
    public void accept_incomming_connections()
    {
        Socket client=null;
        try{
        do {
            
            client = serverSocket.accept();
            write_msg("\nNew client accepted.");
            System.out.println("\nNew client accepted.\n");
            ClientHandler handler =new ClientHandler(client);
            System.out.println("ClientHandler handler =new ClientHandler(client);");
            handler.start();
            System.out.println("handler.start();");
            } while (true);
        }
        catch(Exception ex)
        {
            System.out.println("unable to accept incomming connections");
            write_msg("unable to accept incomming connections");
            System.out.println(ex);
        }
    }
    
    public void openfile()
    {
        try{
            fw=new FileWriter("E:\\output.txt",true);
        }
        catch(Exception ex)
        {
            System.out.println("un able to open file");
        }
    }
    
    public void write_msg(String msg)
    {
        try{
            openfile();
            fw.append(msg);
            fw.append("\n");
            fw.close();
        }
        catch(Exception ex)
        {
            System.out.println("unable to write a msg");
        }
    }
    
    public static String getall()
    {
        String s="";
        int ch;
  
        // check if File exists or not
        FileReader fr=null;
        try
        {
            fr = new FileReader("E:\\output.txt");
        }
        catch (FileNotFoundException fe)
        {
            System.out.println("File not found");
        }
  try{
        // read from FileReader till the end of file
        while ((ch=fr.read())!=-1)
            s+=(char)ch;
  
        // close the file
        fr.close();
  }
  catch(Exception ex)
  {
      
    }
  return s;
    }
    
}
