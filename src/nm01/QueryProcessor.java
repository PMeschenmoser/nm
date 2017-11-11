package nm01;

import java.io.*;

/**
 * Network Modeling, Assignment 01
 * Authors: P. Meschenmoser, L. Shkoza
 * Date: 10.11.2017.
 */

public class QueryProcessor {
    public static void run(BufferedReader buffered, File out, int[] queryfields, String[] checkvalues, int[] outfields, int maxlines) {
        /*
            Called by QueryWrapper.
            queryfields: indices of fields to check
            checkvalues: too which string value should the i-th field equal?
            outfields: indices of fields to project to 'out'
        */
        try {
            //prepare reading and writing:
            BufferedWriter bw = new BufferedWriter(new FileWriter(out));
            String line;
            int bw_count = 0; //flush writer when bw_count has reached bw_max
            int bw_max = 100;
            int count = 0;
            String v_statement;
            Tuple t; //'proper' row processing
            while ((line = buffered.readLine()) != null && count < maxlines) {
                int v_index = line.indexOf("VALUES");
                if (v_index > -1) { //process only rows which contain "[INSERT INTO xy ]VALUES"
                    v_statement = line.substring(v_index + 7, line.length()); //value statement
                    String[] rows = v_statement.split("\\),\\("); //actual rows
                    for (int i = 0; i < rows.length; i++) {
                        t = new Tuple(rows[i]);
                        if (t.fieldCheck(queryfields, checkvalues)) { //match
                            bw.append(t.getFields(outfields));
                            bw_count++;
                            if (bw_count > bw_max) { //flush, write to file
                                bw.flush();
                                bw_count = 0;
                            }
                        }
                    }
                } else { //no VALUES row
                    System.out.println(line);
                }
                count++;
            }
            //finalize write/read
            bw.flush();
            bw.close();
            buffered.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


