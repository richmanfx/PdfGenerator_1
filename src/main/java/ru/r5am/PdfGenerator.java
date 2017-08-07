package ru.r5am;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class PdfGenerator {

    private static final String PDF_FILE = "HelloDmitri.pdf";
    private static int FONT_SIZE_SMALL = 16;

    public static void main(String[] args) throws IOException {

        // Параметры для вывода в PDF-файле
        Map<String, String> parameters = new HashMap<>();

        parameters.put("Фамилия", "Баев");
        parameters.put("Имя", "Василий");
        parameters.put("Отчество", "Алибабаевич");
        parameters.put("Страна", "Россия");
        parameters.put("Город", "Тьмутараканск");
        parameters.put("Улица", "Лесная");
        parameters.put("Дом", "42");
        parameters.put("Квартира", "7");

        // Создать PDF-файл
        new PdfGenerator().createPdf(PDF_FILE, parameters);

    }

    private void createPdf(String destination, Map<String, String> parameters) throws IOException {

        FileOutputStream fos = new FileOutputStream(PDF_FILE);
        PdfWriter writer = new PdfWriter(fos);

        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf);

//        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        PdfFont russianFont = PdfFontFactory.createFont("src/main/resources/FreeSans/FreeSans.ttf", "cp1251", true);
        document.setFont(russianFont);

        document.add(new Paragraph("Привет, Дима!").setFontSize(FONT_SIZE_SMALL).setFont(russianFont));



        Table table = new Table(2);
        Cell cell;

        // Заголовки
        cell = new Cell().add(new Paragraph("Параметр").setFont(russianFont).setFontColor(Color.BLACK));
            cell.setTextAlignment(TextAlignment.CENTER);
            table.addCell(cell);

        cell = new Cell().add(new Paragraph("Значение параметра").setFont(russianFont).setFontColor(Color.BLACK));
            cell.setTextAlignment(TextAlignment.CENTER);
            table.addCell(cell);


        // Данные
        for (Map.Entry<String, String> entry : parameters.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            cell = new Cell().add(new Paragraph(" " + key).setFont(russianFont).setFontColor(Color.BLACK));
            cell.setBackgroundColor(Color.GREEN);
//            cell.setBorder(Border.NO_BORDER);
            cell.setTextAlignment(TextAlignment.LEFT);
            table.addCell(cell);

            cell = new Cell().add(new Paragraph(value).setFont(russianFont).setFontColor(Color.WHITE));
            cell.setBackgroundColor(Color.RED);
            cell.setTextAlignment(TextAlignment.CENTER);
            table.addCell(cell);


        }
        document.add(table);

        document.close();
    }
}
