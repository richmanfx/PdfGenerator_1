package ru.r5am;


import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileOutputStream;
import java.io.IOException;



public class PdfGenerator {

    private static final String PDF_FILE = "HelloDmitri.pdf";
    private static int FONT_SIZE_SMALL = 16;
    private static int FONT_SIZE_BIG = 32;
    private static int OFFSET = 40;

    public static void main(String[] args) throws IOException {

        new PdfGenerator().createPdf(PDF_FILE);

    }

    private void createPdf(String destination) throws IOException {
        FileOutputStream fos = new FileOutputStream(PDF_FILE);
        PdfWriter writer = new PdfWriter(fos);

        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf);

        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN, "UTF-8", true);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
//        Font font = FontFactory.getFont(FONT, "Cp1251", BaseFont.EMBEDDED);

        document.add(new Paragraph("Privet! Привет, Дима!").setFont(font));

        document.close();
    }
}
