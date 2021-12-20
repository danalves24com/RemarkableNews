package services;
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

public class Reader {
    public static void readCNN() throws  Exception {
        URL url = new URL("http://rss.cnn.com/rss/edition_world.rss");
        StringBuilder sb = new StringBuilder(); String s;
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.parse(url.openStream());
        NodeList nl = doc.getElementsByTagName("item");
        for(int i = 0 ; i < nl.getLength(); i ++) {
            Node n = nl.item(i);
            NodeList nll = n.getChildNodes();
            System.out.println(nll.item(0).getTextContent());
        }
    }
}
