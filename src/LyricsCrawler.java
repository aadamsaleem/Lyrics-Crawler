
/**
 *
 * @author Aadam
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LyricsCrawler {

    public static void main(String[] args) {

        Document doc;
        int count = 116577;
        try {
            for (int i = 116577; i <= 2147483647; i++) {
                // connecting to the url
                String url = "http://www.lyricscrawler.com/song/" + i + ".html";
                String song, lyric;
                song = "";
                lyric = "";
                doc = Jsoup.connect(url).get();

                //get tables and rows
                Element elementsByTag;
                elementsByTag = doc.getElementsByTag("table").get(6);
                Elements rows = elementsByTag.getElementsByTag("tr");

                for (int j = 0; j < rows.size(); j++) {
                    if (j == 2) {
                        //get song name
                        Element row = rows.get(j);
                        song = row.getElementsByTag("td").get(0).text();
                        song = song.replace(" Lyrics", "");
                        song = song.replaceAll("[:\\\\/*?|<>]", "_");
                        System.out.println("\nSong #" + count + " : " + song);
                    } else if (j == 4) {
                        //get lyrics
                        Element row = rows.get(j);
                        lyric = row.getElementsByTag("td").get(0).text();
                        System.out.println("Lyrics : " + lyric);
                    }
                }
 /*               try {
                    // write to a file
                    File lyricsfile = new File("C:\\Users\\Aadam\\Documents\\NetBeansProjects\\Lyrics Crawler\\Lyrics\\" + song + ".txt");
                    FileOutputStream fos = new FileOutputStream(lyricsfile);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    try (Writer w = new BufferedWriter(osw)) {
                        w.write(lyric);
                    }
                    count++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
 */           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
