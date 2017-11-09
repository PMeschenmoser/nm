package nm01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by P. Meschenmoser on 08.11.2017.
 */
public class QueryProcessor {

    public static void run(BufferedReader buffered, File out, int[] queryfields, String[] checkvalues, int[] outfields, int maxlines) {
        try {
            FileOutputStream os = new FileOutputStream(out, true);

            String line;
            String insert_statement;
            StringBuilder sb = new StringBuilder(); //cache some rows to avoid too many write operations
            int sb_count = 0; // how many rows are currently in the string builder?
            int sb_max = 1000; //write to file, when this threshold is reached.
            int count = 0;
            while ((line = buffered.readLine()) != null && count < maxlines) {
                int i_index = line.indexOf("INSERT");
                if (i_index > -1) {
                    insert_statement = line.substring(i_index + 6, line.indexOf(";"));
                    String[] rows = insert_statement.split("VALUES \\(")[1].split("\\),\\(");
                    Tuple t;
                    for (int i = 0; i < rows.length; i++) {
                        t = new Tuple(rows[i]);
                        if (t.fieldCheck(queryfields, checkvalues)) {
                            sb.append(t.getFields(outfields));
                            sb_count++;
                            if (sb_count > sb_max) {
                                os.write(sb.toString().getBytes());
                                sb = new StringBuilder();
                                sb_count = 0;
                            }
                        }
                    }
                    os.write(sb.toString().getBytes()); //final round
                }
                count++;
            }
            os.close();
            buffered.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


