/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package url.section;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CompuHome
 */
public class UrlSection {
public static FileWriter fw;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input=new Scanner(System.in);
        System.out.println("enter the URL ...");
        String url=input.next();
        accesswebsite(url);
    }
    public static void accesswebsite(String url)
    {
        BufferedReader br=null;
        try
        {
            System.out.println("1");
            
            URL my_url=new URL(url);
            System.out.println("2");
            URLConnection urlconnection=my_url.openConnection();
            System.out.println("3");
            InputStreamReader isr=new InputStreamReader(urlconnection.getInputStream());
            br=new BufferedReader(isr);
            openfile_first();
            String line;
            while((line=br.readLine())!=null)
            {
                stringin(line);
            }
            
        }
        catch(Exception ex)
        {
            System.out.println("cannot access the URL !!");
            System.exit(1);
        }
        finally{
            System.out.println("CLosing Connection ....");
            try{
                br.close();
                System.exit(1);
            }
            catch(Exception ex)
            {
                System.out.println("error");
            }
        }
    }
    public static void openfile()
    {
        try {
            fw=new FileWriter("F:\\college\\4th level\\network programming\\test.html",true);
        } catch (IOException ex) {
            Logger.getLogger(UrlSection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void stringin(String s)
    {
        try{
            openfile();
            fw.append(s);
            fw.append("\n");
            fw.close();
        }
        catch(Exception ex)
        {
            System.out.println("errOR");
        }
    }
    public static void openfile_first()
    {
        try
        {
            fw=new FileWriter("F:\\college\\4th level\\network programming\\test.html");
        }
        catch(Exception ex)
        {
            
        }
    }
    
    
}
