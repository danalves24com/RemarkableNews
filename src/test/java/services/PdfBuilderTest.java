package services;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;

public class PdfBuilderTest {


    
    @Test
    void createWithMargins() throws  Exception{
        String text = "They bought a cheap house in italy. Heres what they learned";
        FileOutputStream fos = new FileOutputStream("./test.pdf");
        Document doc = new Document();
        doc.setMargins(100,20,20,20);
        PdfWriter writer = PdfWriter.getInstance(doc, fos);
        doc.open();
        doc.add(new Paragraph(text));
        doc.close();;
        writer.close();
    }
}
