import java.net.*;
import java.io.*;
import java.lang.*;

public class client1{

        public static void main(String[] Args){
                System.out.println(Args[0]);
                if(Args.length>1){
                    
                    System.out.println("Connecting to " + Args[0] + " on " + Args[1]);
                    connect(Args[0],Integer.parseInt(Args[1]));
                }
                else{connect(Args[0],80);}
        }

        protected static void connect(String URL,Integer PORT){
                try{
                        Socket s = new Socket(URL,PORT);
                        BufferedReader br;
                        PrintStream ps;
                        
                        br = new BufferedReader(
                                new InputStreamReader(s.getInputStream()));
                        ps = new PrintStream(s.getOutputStream());
                        
                        //Send Request
                        
                        ps.print("GET /~caw724/index.html HTTP/1.0\r\n");
                        ps.print("Accept:text/HTML\r\n");
                        ps.print("\r\n\r\n");

                        String data = br.readLine();
                        
                        while(data != null){
                                System.out.println(data);
                                data = br.readLine();
                        }
                }
                catch(Exception e){
                        System.out.println(e);
                }
               
        }//End connect

}//End Client1