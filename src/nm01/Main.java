package nm01;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by P. Meschenmoser on 08.11.2017.
 */
public class Main {

    public static void main(String[] args){
        File pages = new File("D:\\onedrive2\\OneDrive\\WS1718\\NM\\01\\enwiki-20171001-page.sql.gz");
        File categorylinks = new File("D:\\onedrive2\\OneDrive\\WS1718\\NM\\01\\enwiki-20171001-categorylinks.sql.gz");


        File preview = new File("D:\\onedrive2\\OneDrive\\WS1718\\NM\\01\\preview.txt");
        File previewlinks = new File("D:\\onedrive2\\OneDrive\\WS1718\\NM\\01\\preview_links.txt");
        File articles = new File("D:\\onedrive2\\OneDrive\\WS1718\\NM\\01\\articles.csv");
        File categories = new File("D:\\onedrive2\\OneDrive\\WS1718\\NM\\01\\categories.csv");
        File good = new File("D:\\onedrive2\\OneDrive\\WS1718\\NM\\01\\good.csv");
        File featured = new File("D:\\onedrive2\\OneDrive\\WS1718\\NM\\01\\featured.csv");
        File merged = new File("D:\\onedrive2\\OneDrive\\WS1718\\NM\\01\\merged.csv");

        //get some preview on the gzips, extract 53 lines:
        // QueryWrapper.preview(pages, preview, 53);
        // QueryWrapper.preview(categorylinks, previewlinks, 45);






            //Exercise 1d)
            //QueryWrapper.gzipQuery(pages, articles, new int[]{1, 5}, new String[]{"0", "0"}, new int[]{0,2,11}, 2000000000);




            //Exercise 1e)
            //QueryWrapper.gzipQuery(pages, categories, new int[]{1}, new String[]{"14"}, new int[]{0,2}, 2000000000);
            //Good articles have page ID: 27529113
            //Featured articles (i.e. 'Wikipedia_featured_articles') have page ID: 1002085




            //Exercise 1f)
            //cache ID's to disk:

            //QueryWrapper.gzipQuery(categorylinks, good, new int[]{1}, new String[]{"'Good_articles'"}, new int[]{0}, 2000000000);
            //QueryWrapper.gzipQuery(categorylinks, featured, new int[]{1}, new String[]{"'Featured_articles'"}, new int[]{0}, 2000000000);
            Merger.run(articles, merged, ListImport.importFile(good), ListImport.importFile(featured));




    }
}
