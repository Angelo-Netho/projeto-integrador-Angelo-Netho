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

        return new TimeStampGenerator().generateString(time, TimeStampStyle.LONG);
    }
}
