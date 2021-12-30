import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class tcp_server {
    public static ServerSocket serverSocket;
    public static final int port =0123;
    private static String username ="admin";
    private static String password ="12345";
    public static void main(String[] args) {

        System.out.println("opening the server : ");
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("port opened successful ");

        } catch (IOException ex) {
            System.out.println("faild to connect to the port " + port);
            System.out.println("please try another port ");
            System.exit(1);
        }
        do {
            handel_connection();
        }
        while (true);
    }
    private static void handel_connection() {
        Socket link=null;
        try{
            link=serverSocket.accept();
            Scanner input =new Scanner(link.getInputStream());
            PrintWriter output =new PrintWriter(link.getOutputStream(),true);
            int num_msg=0;
            String client_user_name;
            String client_password;
            do{
                client_user_name=input.nextLine();
                client_password=input.nextLine();
                if(client_password.equals(password) && client_user_name.equals(username)) {
                    output.println("sucessfull login");
                    break;
                }
                else output.println("unvalid username or password");
            }
            while(client_password!=password|| client_user_name!=username);
            String client_msg=input.nextLine();
            while (!client_msg.equals("***close***")){
                num_msg++;
                System.out.println("message "+num_msg+"is recevid ");
                System.out.println("message conntent is "+client_msg);
                output.println("message received successful ");
                client_msg=input.nextLine();
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        finally {
            try {
                System.out.println("closing the connection ");
                link.close();
                System.exit(1);
            }
            catch (Exception e)
            {
                System.out.println(e);
                System.out.println("unable to disconnect");
                System.exit(1);
            }
        }


    }




}
