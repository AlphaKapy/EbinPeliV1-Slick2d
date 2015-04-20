
package Solttu;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Lukija {
    
    public static List<String> Lue(String f){
        List<String> l = new ArrayList<String>();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String s ="";
            while((s=br.readLine())!=null)
            {
                l.add(s);
            }
        }catch(Exception e){System.out.println(""+e.getMessage());}
        return l;
    }
    
}
