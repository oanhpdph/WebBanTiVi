package com.poly.common;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
//
//        String path = "invoice.pdf";
//        PdfWriter pdfWriter = new PdfWriter(path);
//        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
//        pdfDocument.setDefaultPageSize(PageSize.A4);
//        Document document = new Document(pdfDocument);
//        float fiveCol = 114f;
//        float threecol = 190f;
//        float twocol = 285f;
//        float twocol150 = twocol + 150f;
//        float twocolumnWidth[] = {twocol150, twocol};
//        float fullWidth[] = {threecol * 3};
//        float fiveColWidth[] = {fiveCol, fiveCol, fiveCol, fiveCol, fiveCol};
//        Paragraph onesp = new Paragraph("\n");
//        PdfFont font = PdfFontFactory.createFont("src/main/resources/static/admin/assets/vendor/fonts/arial.ttf", PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);
//
//
//        Table table = new Table(twocolumnWidth);
//        table.addCell(new Cell().add(new Paragraph("Hóa đơn").setFontSize(30).setFont(font)).setBorder(Border.NO_BORDER).setBold());
//        Table nestedtabe = new Table(new float[]{twocol / 2, twocol / 2});
//        nestedtabe.addCell(getHeaderTextCellValue("Mã hóa đơn"));
//        nestedtabe.addCell(getHeaderTextCellValue("HDE8R1MU8Q6U"));
//        nestedtabe.addCell(getHeaderTextCellValue("Ngày tạo"));
//        nestedtabe.addCell(getHeaderTextCellValue("2023/12/12"));
//
//        table.addCell(new Cell().add(nestedtabe).setBorder(Border.NO_BORDER));
//        Border gb = new SolidBorder(GRAY, 2f);
//        Table divider = new Table(fullWidth);
//        divider.setBorder(gb);
//
//        document.add(table);
//        document.add(onesp);
//        document.add(divider);
//        document.add(onesp);
//
//        Table twoColTable0 = new Table(twocolumnWidth);
//        twoColTable0.addCell(getBillingandShippingCell("Thông tin Cửa hàng"));
//        twoColTable0.addCell(getBillingandShippingCell(""));
//        document.add(twoColTable0.setMarginBottom(12f));
//        document.add(new Paragraph("Cửa hàng: Cửa hàng Điện tử Big6 Store.").setFont(font));
//        document.add(new Paragraph("Địa chỉ: 109 P. Đồng Giao, Bắc Sơn, Tam Điệp, Ninh Bình.").setFont(font));
//        document.add(new Paragraph("Số điện thoại: 0945643297.").setFont(font));
//
//
//        Table twoColTable = new Table(twocolumnWidth);
//        twoColTable.addCell(getBillingandShippingCell("Thông tin hóa đơn"));
//        twoColTable.addCell(getBillingandShippingCell(""));
//
//        document.add(twoColTable.setMarginBottom(12f));
//
//        Table twoColTable2 = new Table(twocolumnWidth);
//        twoColTable2.addCell(getCell10fLeft("Người nhận", true));
//        twoColTable2.addCell(getCell10fLeft("SĐT nhận hàng", true));
//        twoColTable2.addCell(getCell10fLeft("Phạm đức oanh", false));
//        twoColTable2.addCell(getCell10fLeft("0369921455", false));
//
//        twoColTable2.addCell(getCell10fLeft("Tổng tiền phải trả", true));
//        twoColTable2.addCell(getCell10fLeft("Voucher", true));
//        twoColTable2.addCell(getCell10fLeft("3999999 VNĐ", false));
//        twoColTable2.addCell(getCell10fLeft("0 VNĐ", false));
//
//
//        twoColTable2.addCell(getCell10fLeft("Email mua hàng", true));
//        twoColTable2.addCell(getCell10fLeft("Địa chỉ nhận", true));
//        twoColTable2.addCell(getCell10fLeft("oanhpdph25707@gmail.com", false));
//        twoColTable2.addCell(getCell10fLeft("Liên trì 1, xã yên hòa", false));
//
//
//        twoColTable2.addCell(getCell10fLeft("Phương thức thanh toán", true));
//        twoColTable2.addCell(getCell10fLeft("Tình trạng", true));
//        twoColTable2.addCell(getCell10fLeft("Tiền mặt", false));
//        twoColTable2.addCell(getCell10fLeft("Đã thanh toán", false));
//
//
//        document.add(twoColTable2.setMarginBottom(12f));
//
//        Border gb2 = new DashedBorder(GRAY, 0.5f);
//        Border gb3 = new SolidBorder(GRAY, 0.5f);
//
//        Table divider2 = new Table(fullWidth);
//
//        document.add(divider2.setBorder(gb2));
//
//        Paragraph productPara = new Paragraph("Thông tin sản phẩm").setFont(font);
//
//        document.add(productPara.setBold());
//        Table productTable = new Table(fiveColWidth);
//        productTable.setBackgroundColor(BLACK, 0.7f);
//
//        productTable.addCell(new Cell().add(new Paragraph("Sản phẩm").setFont(font).setFontColor(WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setBold()));
//        productTable.addCell(new Cell().add(new Paragraph("Giá gốc(VNĐ)").setFont(font).setFontColor(WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setBold()));
//        productTable.addCell(new Cell().add(new Paragraph("Được giảm(VNĐ)").setFont(font).setFontColor(WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setBold()));
//        productTable.addCell(new Cell().add(new Paragraph("Số lượng").setFont(font).setFontColor(WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setBold()));
//        productTable.addCell(new Cell().add(new Paragraph("Thành tiền(VNĐ)").setFont(font).setFontColor(WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setBold()));
//
//        document.add(productTable);
//
//        Table productTable2 = new Table(fiveColWidth);
//        Bill bill = new Bill();
//        BigDecimal totalSum = BigDecimal.valueOf(0);
//        List<String> nameProduct;
//        DecimalFormat decimalFormat = new DecimalFormat("0.#");
//        if (bill.getBillProducts() != null) {
//            for (BillProduct billProduct : bill.getBillProducts()) {
//                nameProduct = new ArrayList<>();
//                nameProduct.add(billProduct.getProduct().getProduct().getNameProduct());
//                nameProduct.add("[ ");
//                for (ProductDetailField productFieldValue : billProduct.getProduct().getFieldList()) {
//                    nameProduct.add(productFieldValue.getValue());
//                }
//                nameProduct.add(" ]");
//                productTable2.addCell(new Cell().add(new Paragraph(String.join(" ", nameProduct)).setFont(font)));
//
//                productTable2.addCell(new Cell().add(new Paragraph(decimalFormat.format(billProduct.getPrice())).setFont(font)));
//                productTable2.addCell(new Cell().add(new Paragraph(decimalFormat.format(billProduct.getReducedMoney())).setFont(font)));
//                productTable2.addCell(new Cell().add(new Paragraph(String.valueOf(billProduct.getQuantity())).setFont(font)));
//                productTable2.addCell(new Cell().add(new Paragraph(decimalFormat.format(billProduct.getPrice().subtract(billProduct.getReducedMoney().multiply(BigDecimal.valueOf(billProduct.getQuantity()))))).setFont(font)));
//                totalSum = totalSum.add(billProduct.getPrice().subtract(billProduct.getReducedMoney().multiply(BigDecimal.valueOf(billProduct.getQuantity()))));
//            }
//        }
//        document.add(productTable2.setMarginBottom(20f));
//        float onetwo[] = {threecol + 125f, threecol * 2};
//        Table totalTable = new Table(onetwo);
//        totalTable.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
//        totalTable.addCell(new Cell().add(divider2).setBorder(Border.NO_BORDER));
//        document.add(totalTable);
//
//
//        Table totalTable2 = new Table(fiveColWidth);
//        totalTable2.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER).setMarginLeft(10f));
//        totalTable2.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER).setMarginLeft(10f));
//        totalTable2.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER).setMarginLeft(10f));
//        totalTable2.addCell(new Cell().add(new Paragraph("Tổng tiền").setFont(font)).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER).setMarginLeft(10f));
//        totalTable2.addCell(new Cell().add(new Paragraph(String.valueOf(totalSum))).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginLeft(10f));
//        document.add(totalTable2);
//
//        document.add(divider2.setBorder(gb2).setMarginBottom(12f));
//
//        document.add(divider.setBorder(gb3).setMarginBottom(5f));
//        document.add(new Paragraph("Lưu ý: Sản phẩm được trả hàng trong vòng 10 ngày kể từ ngày nhận hàng nếu sản phẩm bị lỗi do nhà sản xuất.").setFont(font).setItalic());
//
//        document.close();
//        Bill bill = new Bill();
//        byte[] pdfData = generatePdf(bill);
//        if (pdfData != null) {
//            System.out.println("File PDF đã tạo thành công.");
//            // Bây giờ bạn có thể sử dụng pdfData theo nhu cầu của mình.
//        } else {
//            System.out.println("Có lỗi xảy ra trong quá trình tạo file PDF.");
//        }
    }

//    static Cell getBillingandShippingCell(String textValue) throws IOException {
//        PdfFont font = PdfFontFactory.createFont("src/main/resources/static/admin/assets/vendor/fonts/arial.ttf", PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);
//
//        return new Cell().add(new Paragraph(textValue).setFont(font)).setFontSize(14f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
//    }
//
//    static Cell getCell10fLeft(String value, Boolean isBold) throws IOException {
//        PdfFont font = PdfFontFactory.createFont("src/main/resources/static/admin/assets/vendor/fonts/arial.ttf", PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);
//
//        Cell cell = new Cell().add(new Paragraph(value).setFont(font)).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
//        return isBold ? cell.setBold() : cell;
//    }
//
//    static Cell getHeaderTextCellValue(String textValue) throws IOException {
//        PdfFont font = PdfFontFactory.createFont("src/main/resources/static/admin/assets/vendor/fonts/arial.ttf", PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);
//
//        return new Cell().add(new Paragraph(textValue).setFont(font)).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
//    }
//
//    public static byte[] generatePdf(Bill bill) throws IOException {
//        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
//            PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
//            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
//            pdfDocument.setDefaultPageSize(PageSize.A4);
//            Document document = new Document(pdfDocument);
//            float fiveCol = 114f;
//            float threecol = 190f;
//            float twocol = 285f;
//            float twocol150 = twocol + 150f;
//            float twocolumnWidth[] = {twocol150, twocol};
//            float fullWidth[] = {threecol * 3};
//            float fiveColWidth[] = {fiveCol, fiveCol, fiveCol, fiveCol, fiveCol};
//            Paragraph onesp = new Paragraph("\n");
//            PdfFont font = PdfFontFactory.createFont("src/main/resources/static/admin/assets/vendor/fonts/arial.ttf", PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);
//
//
//            Table table = new Table(twocolumnWidth);
//            table.addCell(new Cell().add(new Paragraph("Hóa đơn").setFontSize(30).setFont(font)).setBorder(Border.NO_BORDER).setBold());
//            Table nestedtabe = new Table(new float[]{twocol / 2, twocol / 2});
//            nestedtabe.addCell(getHeaderTextCellValue("Mã hóa đơn"));
//            nestedtabe.addCell(getHeaderTextCellValue("HDE8R1MU8Q6U"));
//            nestedtabe.addCell(getHeaderTextCellValue("Ngày tạo"));
//            nestedtabe.addCell(getHeaderTextCellValue("2023/12/12"));
//
//            table.addCell(new Cell().add(nestedtabe).setBorder(Border.NO_BORDER));
//            Border gb = new SolidBorder(GRAY, 2f);
//            Table divider = new Table(fullWidth);
//            divider.setBorder(gb);
//
//            document.add(table);
//            document.add(onesp);
//            document.add(divider);
//            document.add(onesp);
//
//            Table twoColTable0 = new Table(twocolumnWidth);
//            twoColTable0.addCell(getBillingandShippingCell("Thông tin Cửa hàng"));
//            twoColTable0.addCell(getBillingandShippingCell(""));
//            document.add(twoColTable0.setMarginBottom(12f));
//            document.add(new Paragraph("Cửa hàng: Cửa hàng Điện tử Big6 Store.").setFont(font));
//            document.add(new Paragraph("Địa chỉ: 109 P. Đồng Giao, Bắc Sơn, Tam Điệp, Ninh Bình.").setFont(font));
//            document.add(new Paragraph("Số điện thoại: 0945643297.").setFont(font));
//
//
//            Table twoColTable = new Table(twocolumnWidth);
//            twoColTable.addCell(getBillingandShippingCell("Thông tin hóa đơn"));
//            twoColTable.addCell(getBillingandShippingCell(""));
//
//            document.add(twoColTable.setMarginBottom(12f));
//
//            Table twoColTable2 = new Table(twocolumnWidth);
//            twoColTable2.addCell(getCell10fLeft("Người nhận", true));
//            twoColTable2.addCell(getCell10fLeft("SĐT nhận hàng", true));
//            twoColTable2.addCell(getCell10fLeft("Phạm đức oanh", false));
//            twoColTable2.addCell(getCell10fLeft("0369921455", false));
//
//            twoColTable2.addCell(getCell10fLeft("Tổng tiền phải trả", true));
//            twoColTable2.addCell(getCell10fLeft("Voucher", true));
//            twoColTable2.addCell(getCell10fLeft("3999999 VNĐ", false));
//            twoColTable2.addCell(getCell10fLeft("0 VNĐ", false));
//
//
//            twoColTable2.addCell(getCell10fLeft("Email mua hàng", true));
//            twoColTable2.addCell(getCell10fLeft("Địa chỉ nhận", true));
//            twoColTable2.addCell(getCell10fLeft("oanhpdph25707@gmail.com", false));
//            twoColTable2.addCell(getCell10fLeft("Liên trì 1, xã yên hòa", false));
//
//
//            twoColTable2.addCell(getCell10fLeft("Phương thức thanh toán", true));
//            twoColTable2.addCell(getCell10fLeft("Tình trạng", true));
//            twoColTable2.addCell(getCell10fLeft("Tiền mặt", false));
//            twoColTable2.addCell(getCell10fLeft("Đã thanh toán", false));
//
//
//            document.add(twoColTable2.setMarginBottom(12f));
//
//            Border gb2 = new DashedBorder(GRAY, 0.5f);
//            Border gb3 = new SolidBorder(GRAY, 0.5f);
//
//            Table divider2 = new Table(fullWidth);
//
//            document.add(divider2.setBorder(gb2));
//
//            Paragraph productPara = new Paragraph("Thông tin sản phẩm").setFont(font);
//
//            document.add(productPara.setBold());
//            Table productTable = new Table(fiveColWidth);
//            productTable.setBackgroundColor(BLACK, 0.7f);
//
//            productTable.addCell(new Cell().add(new Paragraph("Sản phẩm").setFont(font).setFontColor(WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setBold()));
//            productTable.addCell(new Cell().add(new Paragraph("Giá gốc(VNĐ)").setFont(font).setFontColor(WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setBold()));
//            productTable.addCell(new Cell().add(new Paragraph("Được giảm(VNĐ)").setFont(font).setFontColor(WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setBold()));
//            productTable.addCell(new Cell().add(new Paragraph("Số lượng").setFont(font).setFontColor(WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setBold()));
//            productTable.addCell(new Cell().add(new Paragraph("Thành tiền(VNĐ)").setFont(font).setFontColor(WHITE).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER).setBold()));
//
//            document.add(productTable);
//
//            Table productTable2 = new Table(fiveColWidth);
//            BigDecimal totalSum = BigDecimal.valueOf(0);
//            List<String> nameProduct;
//            DecimalFormat decimalFormat = new DecimalFormat("0.#");
//            if (bill.getBillProducts() != null) {
//                for (BillProduct billProduct : bill.getBillProducts()) {
//                    nameProduct = new ArrayList<>();
//                    nameProduct.add(billProduct.getProduct().getProduct().getNameProduct());
//                    nameProduct.add("[ ");
//                    for (ProductDetailField productFieldValue : billProduct.getProduct().getFieldList()) {
//                        nameProduct.add(productFieldValue.getValue());
//                    }
//                    nameProduct.add(" ]");
//                    productTable2.addCell(new Cell().add(new Paragraph(String.join(" ", nameProduct)).setFont(font)));
//
//                    productTable2.addCell(new Cell().add(new Paragraph(decimalFormat.format(billProduct.getPrice())).setFont(font)));
//                    productTable2.addCell(new Cell().add(new Paragraph(decimalFormat.format(billProduct.getReducedMoney())).setFont(font)));
//                    productTable2.addCell(new Cell().add(new Paragraph(String.valueOf(billProduct.getQuantity())).setFont(font)));
//                    productTable2.addCell(new Cell().add(new Paragraph(decimalFormat.format(billProduct.getPrice().subtract(billProduct.getReducedMoney().multiply(BigDecimal.valueOf(billProduct.getQuantity()))))).setFont(font)));
//                    totalSum = totalSum.add(billProduct.getPrice().subtract(billProduct.getReducedMoney().multiply(BigDecimal.valueOf(billProduct.getQuantity()))));
//                }
//            }
//            document.add(productTable2.setMarginBottom(20f));
//            float onetwo[] = {threecol + 125f, threecol * 2};
//            Table totalTable = new Table(onetwo);
//            totalTable.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
//            totalTable.addCell(new Cell().add(divider2).setBorder(Border.NO_BORDER));
//            document.add(totalTable);
//
//
//            Table totalTable2 = new Table(fiveColWidth);
//            totalTable2.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER).setMarginLeft(10f));
//            totalTable2.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER).setMarginLeft(10f));
//            totalTable2.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER).setMarginLeft(10f));
//            totalTable2.addCell(new Cell().add(new Paragraph("Tổng tiền").setFont(font)).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER).setMarginLeft(10f));
//            totalTable2.addCell(new Cell().add(new Paragraph(String.valueOf(totalSum))).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginLeft(10f));
//            document.add(totalTable2);
//
//            document.add(divider2.setBorder(gb2).setMarginBottom(12f));
//
//            document.add(divider.setBorder(gb3).setMarginBottom(5f));
//            document.add(new Paragraph("Lưu ý: Sản phẩm được trả hàng trong vòng 10 ngày kể từ ngày nhận hàng nếu sản phẩm bị lỗi do nhà sản xuất.").setFont(font).setItalic());
//
//            document.close();
//
//            return byteArrayOutputStream.toByteArray();
//        }
//    }

}
