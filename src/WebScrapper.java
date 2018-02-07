import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class WebScrapper {
    /**
     * Retrieve contents from a URL and return them as a string.
     *
     * @param url url to retrieve contents from
     * @return the contents from the url as a string, or an empty string on error
     */
    public static String urlToString(final String url) {
        Scanner urlScanner;
        try {
            urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
        } catch (IOException e) {
            return "";
        }
        String contents = urlScanner.useDelimiter("\\A").next();
        urlScanner.close();
        return contents;
    }

    public static int findWordCount(String url) {
        return urlToString(url).split(" ").length;
    }

    public static int findOccurrenceCount(String url, String word) {
        String[] document = urlToString(url).replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");
        int count = 0;
        for (int i = 0; i < document.length; i++) {
            if (document[i].equals(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }
    

    public static void main(String[] unused) {
        System.out.println(findWordCount("http://erdani.com/tdpl/hamlet.txt"));
        System.out.println(findOccurrenceCount("http://erdani.com/tdpl/hamlet.txt", "hither"));

    }
}
