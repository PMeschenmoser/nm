package nm01;

import java.io.*;

/**
 * Created by P. Meschenmoser on 08.11.2017.
 */
public class QueryProcessor {

    public static void run(BufferedReader buffered, File out, int[] queryfields, String[] checkvalues, int[] outfields, int maxlines) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(out));
            String line;
            int bw_count = 0;
            int bw_max = 100;
            int count = 0;
            String v_statement;
            Tuple t;
            while ((line = buffered.readLine()) != null && count < maxlines) {
                int v_index = line.indexOf("VALUES");
                if (v_index > -1) {
                    v_statement = line.substring(v_index + 7, line.length()); //value statement
                    String[] rows = v_statement.split("\\),\\(");
                    for (int i = 0; i < rows.length; i++) {
                        t = new Tuple(rows[i]);
                        if (t.fieldCheck(queryfields, checkvalues)) {
                            bw.append(t.getFields(outfields));
                            bw_count++;
                            if (bw_count > bw_max) {
                                bw.flush();
                                bw_count = 0;
                            }
                        }
                    }
                } else {
                    System.out.println(line);
                }
                count++;
            }
            bw.flush();
            bw.close();
            buffered.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


