/*
Assignment #1 part 2
Server
Chad A. Woitas
CMPT 350

Take an argument that points to files pointing to a webpage
//TODO Copy homepage to local directory for testing
*/

import java.io.*;
import java.net.*;
public class server{

    public static void main(String[] Args){
        
        //TODO parse input
        //Initiate server
        serve(Args[0]);
    }
    
    public static void serve(String directory){

        Integer port = 1994;
        System.out.println("Opening connection to: " + directory + "on port " + port);
        System.out.println("");

        try{

            //open streams
            BufferedReader br;
            PrintStream ps;
            Socket s;
    
            //Iniate socket
            ServerSocket server = new ServerSocket(port);
            
            //Iniate Communication
            while(true){
 
                //Open server
                s = server.accept();
                //Open Streams
                br = new BufferedReader(
                        new InputStreamReader(
                            s.getInputStream()
                        )
                    );
                ps = new PrintStream(s.getOutputStream() );
            
                
                //TODO initiate redirection
               
                //TODO change if file not found
                ps.print("HTTP/1.0 200 OK\r\n");
                ps.print("Content-Type:text/html\r\n");
                ps.print("/r/n/r/n");

                //Initiate File connection
                File fileParser = new File(directory);
                File fileReader;
                fileParser.setReadOnly();
                if(fileParser.isDirectory()){
                    File[] files = fileParser.listFiles();
                    fileReader = files[0];
                }
                else{
                    fileReader = fileParser;
                }
                
                FileInputStream fis = new FileInputStream(fileReader);
                
                ps.print(fis);
    
               //Close Server
                s.close();
            }

        }
        catch(Exception E){
            System.out.println(E);
        }
        

    }//end serve




}//End class