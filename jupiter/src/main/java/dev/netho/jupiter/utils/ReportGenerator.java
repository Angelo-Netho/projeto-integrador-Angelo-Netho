package dev.netho.jupiter.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dev.netho.jupiter.models.Diary;
import dev.netho.jupiter.models.Patient;
import dev.netho.jupiter.models.Psychologist;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class ReportGenerator {

    private final Psychologist psychologist;

    public ReportGenerator(Psychologist psychologist) {
        this.psychologist = psychologist;
    }

    public void generateReport(String fileName) throws Exception {
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(fileName));

        document.open();

        document.add(new Chunk());
        PdfPTable table = new PdfPTable(4);

        String[] header = {"Nome", "Email", "Registros", "Último registro"};
        for(String string:header) {
            PdfPCell cell = new PdfPCell();
            cell.addElement(new Paragraph(string));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorderWidth(2);
            table.addCell(cell);
        }

        for(Patient patient: psychologist.getPatients()) {
            if(!patient.getDiaries().isEmpty()) {
                table.addCell(patient.getName());
                table.addCell(patient.getEmail());
                table.addCell(patient.getDiaries().size() + "");
                table.addCell(generateTime(patient));
            }

        }

        document.add(table);
        document.close();
    }

    private String generateTime(Patient patient) {

        int size = patient.getDiaries().size();
        Diary diary = patient.getDiaries().get(size-1);
        LocalDateTime time = diary.getReceive();

        Locale BRAZIL = new Locale("pt", "BR");

        String month = time.getMonth().getDisplayName(TextStyle.SHORT, BRAZIL);

        String minute;
        String hour;

        if(time.getMinute() == 0) {
            minute = "00";
        }else {
            minute = time.getMinute() + "";
        }

        if(time.getHour() == 0) {
            hour = "00";
        }else {
            hour = time.getHour() + "";
        }

        String hourMinute = hour + ":" + minute;

        String received = time.getDayOfMonth() + " de " + month + " de " + time.getYear() + " " +
                hourMinute + " (" + diary + ")";

        return received;
    }
}
