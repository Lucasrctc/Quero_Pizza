package otello;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Rascunho
{
    public static void main(String [] args) throws FileNotFoundException, IOException
    {
        File f;
        f = new File("C:\\Users\\Luan Cardoso\\Documents\\NetBeansProjects\\Otello\\input.txt");
        
        FileReader fr= new FileReader(f);
        
        System.out.print((char)fr.read());
        
    }
}