package nm01;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by P. Meschenmoser on 10.11.2017.
 */
public class ListImport {

    public static LinkedList importFile(File f) {
       LinkedList<Integer> l = new LinkedList<Integer>();
        try {
            InputStreamReader r = new InputStreamReader(new FileInputStream(f));
            BufferedReader br = new BufferedReader(r);
            String line;
            while ((line = br.readLine()) != null){
                if (line.length()>0) l.add(Integer.parseInt(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return l;
    };
}
