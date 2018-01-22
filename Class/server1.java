// server in class Jan 10


import java.io.*;
import java.net.*;

public class server1{
        
    public static void main(String[] Args){
        serve();
    }


    public static void serve(){
        try{
            BufferedReader br;
            PrintStream ps;
            Socket s;
            
            ServerSocket server = new ServerSocket(1994);

            while(true){
                s = server.accept();
                
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                ps = new PrintStream(s.getOutputStream() );

                ps.println("/HTTP/1.0 200 OK");
                ps.println("ContentType: text/html");
                ps.println("");
                ps.println("<HTML><BODY><P>HELLO</P><p>\"<a href=\"http://www.iana.org/domains/example\">More information...</a></P></BODY></HTML>");
                
                s.close();
            }

        }
        catch(Exception e){ System.out.println(e); }

        }//end serve

} //end server1