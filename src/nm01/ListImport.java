package nm01;

import java.io.*;
import java.util.LinkedList;

/**
 * Network Modeling, Assignment 01
 * Authors: P. Meschenmoser, L. Shkoza
 * Date: 10.11.2017.
 */

public class ListImport {
    public static LinkedList importFile(File f) {

        /*
            Reads in a sequence of article IDs (separated by line breaks) and returns it as a linked list.
            for simplicity and better efficiency & modularity. Input for Join.run(...)
        */
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
