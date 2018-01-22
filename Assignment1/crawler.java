/*
Assignment 1
Crawler
Chad A. Woitas
CAW724 11137533
Purpose: Take a few arguments and find all url on webpage
*/
import java.net.*;
import java.io.*;
import java.util.regex.*;
import java.lang.*;
import java.util.ArrayList;


public class crawler{


    public static void main(String[] Args){

        String CurrentSite = new String(Args[0]);
        
        ArrayList<String> PendingSites = new ArrayList<String>();
        ArrayList<String> NewSites = new ArrayList<String>();

        ArrayList<String> visited = new ArrayList<String>();

        Integer CountDown = Integer.parseInt(Args[1]);
        Integer it = 0;
    
        while(CountDown<=0){
            visited.add(CurrentSite);
            
            NewSites.addAll(crawl(CurrentSite));
            
            PendingSites.addAll(NewSites);
            if(it<PendingSites.size()){
                CurrentSite = PendingSites.get(it);
                it++;
            }
            CountDown++;
        }


        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println(PendingSites);
        System.out.println("Sites found:" + PendingSites.size());
        System.out.println("Sites Visited: ");
        System.out.println(visited);

    }

    /*
    Purpose: Scanned website for <a HREF=""> tags
             Print to standard output
    Parameter: URL - site to connect to
    */
    private static ArrayList<String> crawl(String url){
        System.out.println("");
        System.out.println("Connecting to " + url);
        System.out.println("");

        //TODO: Change temp and temp2
        //Parse <A HREF Tags
        String[] temp = {""};
        //Remove closing line ">
        String[] temp2 = {""};

        //HTTPS not supported? 
        Integer PORT = 80; 
        String[] urlSplit = {""};

        //Remove HTTP tag
        String[] htmlSplit=(url.toLowerCase()).split("//");
        //Split string for host and page
        if(htmlSplit.length>1){
            urlSplit=(htmlSplit[1].toLowerCase()).split("/");
        }
        else{
            urlSplit=(htmlSplit[0].toLowerCase()).split("/");
        }
    
        String page = "/";
        String host = "";

        //Parse pages
        if(urlSplit.length>1){
            host = urlSplit[0];
            for(Integer i = 1;i<urlSplit.length;i++){
                page = page+urlSplit[i]+"/";
            } 
        }
        else{
            host = url;
            page = "/";
        }


        System.out.println("Host>>>>" + host);
        System.out.println("Page>>>>" + page);

        //Save the websites to return
        ArrayList<String> lists = new ArrayList<String>();

        
           
        //Initiate Connection and crawl
        try{
            //Open Socket
            Socket s = new Socket(host,PORT);
            
            //Open Read/Write Streams for socket s
            BufferedReader br;
            PrintStream ps;                       
            br = new BufferedReader(
                 new InputStreamReader(s.getInputStream()));
            ps = new PrintStream(s.getOutputStream());
                        
            //Send Request 
            ps.print("GET " + page + " HTTP/1.0\r\n");
            ps.print("Accept:html/text\r\n");
            ps.print("Host:" + host + "\r\n");
            ps.print("\r\n\r\n");

            //Receive Data
            String data = br.readLine();
            data = data.toUpperCase();
            
            //todo: Rename Pattern and Matcher
            //Setup regular expression for searching for links
            Pattern p = Pattern.compile(".*?<A HREF=.*?");
            Matcher m = p.matcher("");
            
            //Scan Data Recieved
            while(data != null){  
                data = data.toUpperCase();           
                m=p.matcher(data);
                //System.out.println(data);
                if (m.matches()){
                    temp = data.split("<A HREF=\"");
                    temp2 = temp[1].split("\">");
           
                    //Display links found
                    //System.out.println(temp[1]);
                    System.out.println(temp2[0]);
                    if((lists.indexOf(temp2[0]))== -1){ 
                        lists.add(temp2[0]);
                    }
                }
                
                //System.out.println(data);
                //Scan next received input
                data = br.readLine();
            
            }//End Data Receive
        }
        catch(Exception E){System.out.println(E);}
    
        return lists;
    }//end crawl
}//end class

