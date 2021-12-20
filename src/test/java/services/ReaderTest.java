package services;

import data.Feed;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    @Test
    void reader() throws  Exception{
        Reader.readCNN().writeStories();
    }

    @Test void crawler() throws  Exception {
        Feed f = Reader.readCNN();
        Feed ff = Crawler.crawlCNNfeed(f);
        ff.write();
        ff.writeStories();
        SystemCommand.run("rclone copy ./news remote:News");

    }
}