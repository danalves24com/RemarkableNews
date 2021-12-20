package services;

import data.Feed;
import data.FeedMeta;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Crawler {

    public static Document request(String url) {
        try {
            Connection con = Jsoup.connect(url);
            Document doc = con.get();
            if(con.response().statusCode() == 200) {
                return  doc;
            }
            return  null;
        }catch (Exception e) {
            return null;
        }
    }

    public static Feed crawlCNNfeed(Feed feed) {
        ArrayList<FeedMeta> fd = feed.entries();
        for(int i = 0 ; i < fd.size(); i += 1) {
            FeedMeta meta = fd.get(i);
            String link = meta.link();
            Document doc = request(link);
            StringBuilder data = new StringBuilder();
            for (Element element : doc.selectXpath("//div[contains(@class, 'zn-body')]")) {
                data.append(element.text());
            }
            if(data.length()==0) {
                for (Element element : doc.selectXpath("//div[contains(@class, 'Paragraph__component')]")) {
                    data.append(element.text());
                }
            }
            if(data.length()>0){
                fd.set(i, new FeedMeta(meta.title(), meta.description(), meta.link(), meta.date(), data.toString()));
            }
            System.out.println(fd.get(i).contents().length());
        }
        return feed;
    }
}
