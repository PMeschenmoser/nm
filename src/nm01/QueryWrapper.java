package nm01;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * Created by P. Meschenmoser on 08.11.2017.
 */
public class QueryWrapper {

    public static void preview(File in, File out, int maxlines) {
        try {
            GZIPInputStream is = new GZIPInputStream(new FileInputStream(in));
            InputStreamReader r = new InputStreamReader(is);
            BufferedReader buffered = new BufferedReader(r);
            FileOutputStream os = new FileOutputStream(out, true);

            String line;
            int count = 0;
            while ((line = buffered.readLine()) != null && count < maxlines) {
                os.write((line + "\n").getBytes());
                count++;
            }
            os.close();
            is.close();
            r.close();
            buffered.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void gzipQuery(File in, File out, int[] queryfields, String[] checkvalues, int[] outfields, int maxlines) {
        try {
            GZIPInputStream is = new GZIPInputStream(new FileInputStream(in));
            InputStreamReader r = new InputStreamReader(is);
            QueryProcessor.run(new BufferedReader(r), out,  queryfields,  checkvalues,  outfields,  maxlines);
            is.close();
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}