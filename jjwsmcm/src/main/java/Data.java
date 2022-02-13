import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Scanner;

public class Data {

    public static final String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; " +
            "Linux x86_64; rv:96.0) Gecko/20100101 Firefox/96.0";

    public static void main(String[] args) throws Exception  {
        Scanner sc = new Scanner(System.in);

        System.out.print("Search Keyword: ");
        String query = sc.next();



        Document page = Jsoup.connect("https://www.google.com/search?q=" +
                URLEncoder.encode(query, "UTF-8")).userAgent(USER_AGENT).get();

        final PrintWriter out = new PrintWriter("results.txt");

        for (Element searchResult : page.select("a")) {

            final String title = searchResult.text();
            final String url = searchResult.attr("href");
            System.out.println(title + " -> " + url + "\n");
            out.write(title + " -> " + url + "\n");
        }

        out.close();
    }
}