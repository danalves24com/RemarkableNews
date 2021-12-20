package data;

import java.util.ArrayList;

public record Feed(String title, String link, String description, String language, String copyright, String pubDate, ArrayList<FeedMeta> enteries) {
}
