package finaltest;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class server {
    public static ServerSocket serverSocket;
    public static final int port =0123;
    private static String username ;
    private static String password ;
    Socket link;
    Scanner input ;
    PrintWriter output ;
    private static int num_msg;
    server()
    {
        username ="admin";
        password ="12345";
        link=null;
        input =null;
        output =null;
        num_msg=0;
    }
    protected String open_server()
    {
        System.out.println("opening the server : ");
        String s="";
        try {
            serverSocket = new ServerSocket(port);
            s="port opened successful ";
            return s;

        } catch (IOException ex) {
            s+="faild to connect to the port " + port+"\n";
            s+="please try another port \n";
            System.exit(1);
        }
        return s;
    }
    
    
    public void mail_valid_or_not()
    {
        try{
        link=serverSocket.accept();
        input =new Scanner(link.getInputStream());
        output =new PrintWriter(link.getOutputStream(),true);
        String username1=input.nextLine();
        String password1=input.nextLine();
        System.out.println(username1+password1);
        if(password1.equals(password) && username1.equals(username)) {
         output.println("sucessfull login");}
         else output.println("unvalid username or password");
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
    String receive_msg(String msg)
    {
        num_msg++;
        String s="";
        s+="message "+num_msg+"is recevid ";
        s+="message conntent is "+msg;
        output.println("message received successful ");
        return s;
    }
    
    
    String close_connection() 
    {
        
        String s="server is closed";
        try{
            
        link.close();
        }
        catch(Exception e)
        {
            s=e.toString();
        }
        return s;
    }
}
