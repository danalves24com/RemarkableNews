package data;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import javax.imageio.stream.FileCacheImageOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public record Feed(String title, String link, String description, String language, String copyright, String pubDate, ArrayList<FeedMeta> entries) {
    public void writeStories() throws  Exception{
        System.out.print("Writing files: [");
        String root = System.getProperty("user.home");
        for(FeedMeta m : entries) {
            if(m.contents()!=null&&m.contents().length()>10) {
                System.out.print("*");
                FileOutputStream fos = new FileOutputStream(root+"/News/stories/"+m.title()+".pdf");
                Document doc = new Document();
                doc.setMargins(90,90,20,20);
                PdfWriter writer = PdfWriter.getInstance(doc, fos);
                doc.open();

                Paragraph title = (new Paragraph(m.title(), new Font(Font.FontFamily.UNDEFINED, 50)));
                    title.setAlignment(1);

                Paragraph date = new Paragraph(pubDate);
                    date.setSpacingBefore(10);

                String data = m.contents();
                    data = data==null?"no data":data;


                Paragraph contents = new Paragraph(data);
                    contents.setSpacingBefore(40);
                    contents.setAlignment(Element.ALIGN_JUSTIFIED);

                doc.add(title); doc.add(date); doc.add(contents); doc.close();
                writer.close();
            }else {

            }
        }
        System.out.println("]\nAll files written");
    }
    public void write() throws Exception{
        String root = System.getProperty("user.home");
        FileOutputStream fos = new FileOutputStream(root+"/News/overview.pdf");
        Document doc = new Document();
        doc.setMargins(90,90,20,20);
        PdfWriter writer = PdfWriter.getInstance(doc, fos);
        doc.open();
        Paragraph ttl = new Paragraph("Remarkable CNN feed", new Font(Font.FontFamily.UNDEFINED, 20));
        ttl.setAlignment(1);
        doc.add(ttl);
        Paragraph date = new Paragraph(pubDate, new Font(Font.FontFamily.UNDEFINED, 9));
        date.setAlignment(1);
        doc.add(date);
        for(FeedMeta m : entries) {
            Paragraph tc = (new Paragraph(m.title(), new Font(Font.FontFamily.UNDEFINED, 15)));
            tc.setPaddingTop(40);
            tc.setSpacingBefore(20);
            doc.add(tc);
            doc.add(new Paragraph(m.date(), new Font(Font.FontFamily.UNDEFINED, 9)));
            doc.add(new Paragraph(m.description()));
        }
        doc.close();
        writer.close();
    }
}
