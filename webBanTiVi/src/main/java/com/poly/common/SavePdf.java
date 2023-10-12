package com.poly.common;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
public class SavePdf {


    public void savePdf(HttpServletResponse response) throws IOException {
        // Tạo đối tượng tài liệu
        // Tạo một tệp PDF mới
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=example.pdf");
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(response.getOutputStream()));

        // Tạo một trang và tài liệu
        Document document = new Document(pdfDoc);

        // Thêm nội dung vào tài liệu
        document.add(new Paragraph("Hello, this is a sample PDF document created with iText."));

        // Đóng tài liệu
        document.close();
    }

//    private void addTableHeader(PdfPTable table) {
//        Stream.of("column header 1", "column header 2", "column header 3")
//                .forEach(columnTitle -> {
//                    PdfPCell header = new PdfPCell();
//                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
//                    header.setBorderWidth(2);
//                    header.setPhrase(new Phrase(columnTitle));
//                    table.addCell(header);
//                });
//    }
//
//    private void addRows(PdfPTable table) {
//        table.addCell("Phạm đức Oanh");
//        table.addCell("Bùi Huyền");
//        table.addCell("row 1, col 3");
//    }


}

