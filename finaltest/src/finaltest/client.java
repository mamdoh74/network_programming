
package finaltest;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class client {
    private static InetAddress host;
    private static final int port =0123;
    Socket link;
    Scanner input ;
    PrintWriter output ;
    public static String s1;
    server s=new server();
    client()
    {
        host=null;
        
        link=null;
        input =null;
        output =null;
        s1="";
    }
    public void open_client()
    {
        try {
            host=InetAddress.getLocalHost();

        }
        catch (Exception ex){
            System.out.println(ex);
            System.exit(1);

        }
    }
    
    String mail_valid_or_no(String username , String password){
        String response="";
        try{
        link = new Socket(host, port);
        output = new PrintWriter(link.getOutputStream(), true);
        output.println(username);
        output.println(password);
        s.mail_valid_or_not(); 
        input = new Scanner(link.getInputStream());
        response = input.nextLine();
        System.out.println(response);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return response;
    }
    
    
    String send_msg(String msg)
    {
        output.println(msg);
        s1=s.receive_msg(msg);
        String response = input.nextLine();
        return "<Server> : " + response;
    }
    
    String close_connection()
    {
        String s="client  is closed";
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
