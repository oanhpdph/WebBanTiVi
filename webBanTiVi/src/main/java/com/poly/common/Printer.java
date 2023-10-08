package com.poly.common;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

public class Printer {

    public void savePdf() {
        Document document = new Document();

        // Sử dụng JFileChooser để cho phép người dùng chọn nơi lưu tệp PDF
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("File (*.pdf)", "pdf"));
        int userChoice = fileChooser.showSaveDialog(null);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn và tên tệp mà người dùng đã chọn
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getName();
            String pdfFilePath=null;
            if (!fileName.endsWith(".pdf")) {
                 pdfFilePath = selectedFile.getAbsolutePath() + ".pdf";
            } else {
                 pdfFilePath = selectedFile.getAbsolutePath();
            }
            try {
                // Tạo một đối tượng PdfWriter để viết tài liệu vào tệp PDF
                PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));

                // Mở tài liệu để viết
                document.open();
                PdfPTable table = new PdfPTable(3);
                addTableHeader(table);
                addRows(table);
                document.add(table);
                // Đóng tài liệu
                document.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (userChoice == JFileChooser.CANCEL_OPTION) {
            System.out.println("Người dùng đã hủy lựa chọn.");
        }
    }
    private void addTableHeader(PdfPTable table) {
        Stream.of("column header 1", "column header 2", "column header 3")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }
    private void addRows(PdfPTable table) {
        table.addCell("Phạm đức Oanh");
        table.addCell("Bùi Huyền");
        table.addCell("row 1, col 3");
    }

    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.savePdf();
    }
}

