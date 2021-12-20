import data.Feed;
import services.Crawler;
import services.Reader;

public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println("___________________          _-_\n" +
                "\\==============_=_/ ____.---'---`---.____\n" +
                "            \\_ \\    \\----._________.----/\n" +
                "              \\ \\   /  /    `-_-'\n" +
                "          __,--`.`-'..'-_\n" +
                "         /____          ||\n" +
                "              `--.____,-'");
        Feed f = Reader.readCNN();
        Feed ff = Crawler.crawlCNNfeed(f);
        ff.write();
        ff.writeStories();
    }
}
