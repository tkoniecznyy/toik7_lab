
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

public class DocumentComponentImpl  {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentComponentImpl.class);

    public void createDocument( String fileDestination) {
        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileDestination));

            document.open();

            document.addTitle("resume ");
            document.addAuthor("tk");
            Paragraph preface = new Paragraph();
            Paragraph preface2 = new Paragraph();
            preface.add(new Paragraph("RESUME",new Font(Font.FontFamily.COURIER,20,Font.ITALIC)));
            addEmptyLine(preface,2);
            document.add(preface);
            PdfPTable table = new PdfPTable(4);
            table.setWidths(new int[]{1, 1, 2, 2});
            table.addCell(createCell("Imie", 2, Element.ALIGN_LEFT));
            table.addCell(createCell("Nazwisko", 2, Element.ALIGN_LEFT));
            table.addCell(createCell("Doswiadczenie", 2, Element.ALIGN_LEFT));
            table.addCell(createCell("Umiejetnosci", 2, Element.ALIGN_LEFT));

            table.addCell(createCell("Tomasz", 1, Element.ALIGN_LEFT));
            table.addCell(createCell("Konieczny", 1, Element.ALIGN_LEFT));
            table.addCell(createCell("example content", 1, Element.ALIGN_LEFT));
            table.addCell(createCell("example skills", 1, Element.ALIGN_LEFT));
            document.add(table);


            //setBackgroundAsGradient(document, writer);
            document.close();

        } catch (DocumentException | FileNotFoundException e) {
            LOGGER.error("i can't create document or file not exists");
        }
    }

    private PdfPCell createCell(String content, float borderWidth, int alignment) {
        final String FONT = "static/arial.ttf";

        Font font = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true);

        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setBorderWidth(borderWidth);
        cell.setHorizontalAlignment(alignment);
        cell.setPaddingTop(3);
        cell.setPaddingBottom(6);
        cell.setPaddingLeft(3);
        cell.setPaddingRight(3);
        return cell;
    }

    private void setBackgroundAsGradient(Document document, PdfWriter writer) {
        Rectangle pageSize = document.getPageSize();
        PdfShading axial = PdfShading.simpleAxial(writer,
                pageSize.getLeft(pageSize.getWidth()/10), pageSize.getBottom(),
                pageSize.getRight(pageSize.getWidth()/10), pageSize.getBottom(),
                new BaseColor(100, 140, 160),
                new BaseColor(180, 160, 152), true, true);
        PdfContentByte canvas = writer.getDirectContentUnder();
        canvas.paintShading(axial);
    }

    private void addEmptyLine(Paragraph paragraph, int number){
        for(int i=0;i<number;i++){
            paragraph.add(new Paragraph(" "));
        }
    }

}
