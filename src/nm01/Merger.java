package nm01;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by P. Meschenmoser on 10.11.2017.
 */
public class Merger {

    public static void run(File infile, File outfile, LinkedList good, LinkedList featured){
        try {
            InputStreamReader r = new InputStreamReader(new FileInputStream(infile));
            BufferedReader br = new BufferedReader(r);

            Iterator iter_good = good.iterator();
            Iterator iter_featured = featured.iterator();
            String line;
            Tuple tuple;
            Integer good_id = -1;
            Integer featured_id = -1;
            boolean is_good;
            boolean is_featured;

            BufferedWriter bw = new BufferedWriter(new FileWriter(outfile));
            int bw_count = 0;
            int bw_max = 100;

            int maxlines = 6000000;
            int count = 0;
            while ((line = br.readLine()) != null && count < maxlines){
                tuple = new Tuple(line);

                is_good = false;
                while (iter_good.hasNext()){
                    if (good_id < (tuple.getID())){
                        good_id = (Integer) iter_good.next();
                    } else if (good_id.equals(tuple.getID())){
                        is_good = true;
                        break;
                    } else {
                        break;
                    }
                }
                tuple.setGood(is_good);

                is_featured = false;
                while (iter_featured.hasNext()){
                    if (featured_id < (tuple.getID())){
                        featured_id = (Integer) iter_featured.next();
                    } else if (featured_id.equals(tuple.getID())){
                        is_featured = true;
                        break;
                    } else {
                        break;
                    }
                }
                tuple.setFeatured(is_featured);

                bw.append(tuple.getAllFieldsExtended());
                bw_count++;
                count++;
                if (bw_count > bw_max) {
                    bw.flush();
                    bw_count = 0;
                }
            }
            bw.flush();
            bw.close();
            br.close();
            r.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
