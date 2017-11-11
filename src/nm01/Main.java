package nm01;

import java.io.File;

/**
 * Network Modeling, Assignment 01
 * Authors: P. Meschenmoser, L. Shkoza
 * Date: 10.11.2017.
 */

public class Main {
    public static void main(String[] args){
            //You only need to adapt the following path. Then, uncomment some of the below methods.
            String bigdatafolder = "A:\\onedrive-a\\OneDrive\\WS1718\\NM\\01\\";

            //initial input files
            File pages = new File(bigdatafolder + "enwiki-20171001-page.sql.gz");
            File categorylinks = new File(bigdatafolder + "enwiki-20171001-categorylinks.sql.gz");

            //output files
            File preview = new File("d\\preview.txt");
            File previewlinks = new File("d\\preview_links.txt");
            File articles = new File("d\\articles.csv");
            File categories = new File("d\\categories.csv");
            File good = new File("d\\good.csv");
            File featured = new File("d\\featured.csv");
            File merged = new File("d\\merged.csv"); //final results

            /*
                At first, get some preview on the gzips, extract 53 lines:
            */
            //QueryWrapper.preview(pages, preview, 53);
            // QueryWrapper.preview(categorylinks, previewlinks, 53);



            /*
                Exercise 1d)
                Extract id, title, length for each page in namespace 0 and discard redirects.
             */
            // QueryWrapper.gzipQuery(pages, articles, new int[]{1, 5}, new String[]{"0", "0"}, new int[]{0,2,11}, 2000000000);


            /*
                Exercise 1e)
                Extract ID and title for each category (i.e. namespace 14).
                Good articles category ID: 27,529,113.
                Featured articles category ID: 8,966,938, resp. 8,966,941.
             */
            //QueryWrapper.gzipQuery(pages, categories, new int[]{1}, new String[]{"14"}, new int[]{0,2}, 2000000000);


            /*
                Exercise 1f)
                For better structure, cache ID's of good and featured articles on disk.
                Import them as LinkedList, perform the join and put the 5-column table to 'merged'
            */
            //QueryWrapper.gzipQuery(categorylinks, good, new int[]{1}, new String[]{"'Good_articles'"}, new int[]{0}, 2000000000);
            //QueryWrapper.gzipQuery(categorylinks, featured, new int[]{1}, new String[]{"'Featured_articles'"}, new int[]{0}, 2000000000);
            //Join.run(articles, merged, ListImport.importFile(good), ListImport.importFile(featured));
    }
}
