package services;
import data.Feed;
import data.FeedMeta;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

public class Reader {

    private static String getFirst(String q) {
        return doc.getElementsByTagName(q).item(0).getTextContent();
    }
    private static Document doc;
    public static Feed readCNN() throws  Exception {
        System.out.print("Reading stories meta: [");
        URL url = new URL("http://rss.cnn.com/rss/edition_world.rss");
        StringBuilder sb = new StringBuilder(); String s;
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        doc = db.parse(url.openStream());
        int[] indecies = {0,1,2,4};
        String title = getFirst("title"),
        link = getFirst("link"), description = getFirst("description"),
        lang = getFirst("language"), copyright = getFirst("copyright"), pubDate = getFirst("pubDate");
        NodeList nl = doc.getElementsByTagName("item");
        ArrayList<FeedMeta> metas = new ArrayList<>();
        for(int i = 0 ; i < nl.getLength(); i ++) {
            System.out.print("*");
            Node n = nl.item(i);
            NodeList nll = n.getChildNodes();
            String[] data = new String[4];
            for(int j = 0 ; j < indecies.length; j+=1) {
                Node nn = nll.item(indecies[j]);
                if(nn!=null){
                    data[j] = nn.getTextContent();
                }
            }
            FeedMeta meta = new FeedMeta(data[0], data[1], data[2], data[3], null);
            metas.add(meta);
            System.out.print("*");
        }
        System.out.println("]");
        System.out.println("Finished reading stories meta");
        Feed feed = new Feed(title, link, description, lang, copyright, pubDate, metas);
        return  feed;
    }
}
