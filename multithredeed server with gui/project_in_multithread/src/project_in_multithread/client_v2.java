
package project_in_multithread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author CompuHome
 */
public class client_v2 {
    private static InetAddress host;
    private static final int PORT = 4444;
    private static Socket socket = null;
    private static FileWriter fw;
    private static Scanner networkInput;
    private static PrintWriter networkOutput;
    private static String response;
    private static String username;
    private static String password;
    
    public void initial_a_client()
    {
        try{
            host=InetAddress.getLocalHost();
            
            System.out.println("hello");
            socket = new Socket(host, PORT);
            networkInput =new Scanner(socket.getInputStream());
            networkOutput = new PrintWriter(socket.getOutputStream(),true);
            System.out.println("end");
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public void login(String username,String password){
        try{
            networkOutput.println(username);
            networkOutput.println(password);
            write_msg(networkInput.nextLine());
        }
        catch(Exception ex)
        {
            System.out.println("error in login");
        }
    }
    
    public static void sendMessages(String msg) {
        try{
        System.out.println("1");
            networkOutput.println(msg);
            System.out.println("2");
            networkInput =new Scanner(socket.getInputStream());
            response=networkInput.nextLine().toString();
            System.out.println("3");
            write_msg("\nSERVER> " + response);
            System.out.println("\nSERVER> " + response);
        }
        catch(Exception ex)
        {
            System.out.println("error");
            
        }
    }
    
    public void close_connection()
    {
        try {
            System.out.println( "\nClosing connection…");
            write_msg("\nClosing connection…");
            socket.close();
             } catch (IOException ioEx) {
                System.out.println("Unable to disconnect!");
                write_msg("Unable to disconnect!");
                System.exit(1);
 }
    }
    
     public static void openfile()
    {
        try{
            fw=new FileWriter("E:\\output2.txt",true);
        }
        catch(Exception ex)
        {
            System.out.println("unable to open file");
        }
    }
    
    public static void write_msg(String msg)
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
            fr = new FileReader("E:\\output2.txt");
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
