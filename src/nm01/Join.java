package nm01;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Network Modeling, Assignment 01
 * Authors: P. Meschenmoser, L. Shkoza
 * Date: 10.11.2017.
 */

public class Join {
    public static void run(File infile, File outfile, LinkedList good, LinkedList featured){
        try {
            // prepare reading article csv
            InputStreamReader r = new InputStreamReader(new FileInputStream(infile));
            BufferedReader br = new BufferedReader(r);

            //prepare iteration over lists for good and featured IDs
            Iterator iter_good = good.iterator();
            Iterator iter_featured = featured.iterator();
            String line;
            Tuple tuple;
            Integer good_id = -1;
            Integer featured_id = -1;
            boolean is_good;
            boolean is_featured;

            //flush extended rows to outfile, whenever bw_count has reached the threshold below
            BufferedWriter bw = new BufferedWriter(new FileWriter(outfile));
            int bw_count = 0;
            int bw_max = 100;

            int maxlines = 6000000; //current dump has about 5.9 mio rows
            int count = 0;

            //iterate over articles:
            while ((line = br.readLine()) != null && count < maxlines){
                tuple = new Tuple(line);

                /*
                    Iterate over good ID list, until good_ID >= article_ID.
                    Joining the data in this way is possible since IDs are ordered.
                    Simple iteration over three lists, linear runtime -> execution
                    within a few seconds. Clearly, something like list/set.contains()
                    does not scale at all.
                 */

                is_good = false;
                while (iter_good.hasNext()){
                    if (good_id < (tuple.getID())){ //ignore that ID
                        good_id = (Integer) iter_good.next();
                    } else if (good_id.equals(tuple.getID())){
                        is_good = true; //match
                        break;
                    } else { //good_id is greater than article_id, wait for outer iteration
                        break;
                    }
                }
                tuple.setGood(is_good); //in case of no match above: false.

                //analogous for featured articles:
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

                //append extended fields and flush after bw_max rows.
                bw.append(tuple.getAllFieldsExtended());
                bw_count++;
                count++;
                if (bw_count > bw_max) {
                    bw.flush();
                    bw_count = 0;
                }
            }
            //finally:
            bw.flush();
            bw.close();
            br.close();
            r.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
