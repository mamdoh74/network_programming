import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
public class tcp_client {
    private static InetAddress host;
    private static final int port =0123;
    public static void main(String[] args) {
        try {
            host=InetAddress.getLocalHost();

        }
        catch (Exception ex){
            System.out.println(ex);
            System.exit(1);

        }
        acess_server();
    }
    private static void acess_server()
    {
        Socket link=null;
        try {
            link = new Socket(host, port);
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            Scanner userinput = new Scanner(System.in);
            String msg, response;

            do {
                System.out.println("please enter the user name :");
                String username = userinput.nextLine();
                System.out.println("please enter the password of the user name " + username);
                String password = userinput.nextLine();
                output.println(username);
                output.println(password);
                response = input.nextLine();
                System.out.println(response);
            }
            while(response.equals("unvalid username or password"));
                do {

                    System.out.println("enter your msg");
                    msg = userinput.nextLine();
                    output.println(msg);
                    response = input.nextLine();
                    System.out.println("<Server> : " + response);

                } while (msg != "***close***");


        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        finally {
            try {
                System.out.println("clossing connection ");
                link.close();
            }
            catch (Exception e)
            {
                System.out.println("unable to close the connection "+e);
                System.exit(1);
            }
            }
        }
    }
