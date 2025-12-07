package com.smk.alasiyah.perpustakaan.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.smk.alasiyah.perpustakaan.model.Peminjaman;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PDFReportGenerator {
    
    // Colors untuk desain modern
    private static final BaseColor PRIMARY_COLOR = new BaseColor(102, 126, 234); // #667eea
    private static final BaseColor SECONDARY_COLOR = new BaseColor(118, 75, 162); // #764ba2
    private static final BaseColor ACCENT_COLOR = new BaseColor(240, 147, 251); // #f093fb
    private static final BaseColor DARK_TEXT = new BaseColor(44, 62, 80); // #2c3e50
    private static final BaseColor LIGHT_GRAY = new BaseColor(248, 249, 250); // #f8f9fa
    private static final BaseColor BORDER_COLOR = new BaseColor(224, 224, 224); // #e0e0e0
    
    public static void generateLaporanPDF(List<Peminjaman> data, File outputFile, String jenisLaporan, LocalDate tanggal) {
        try {
            Document document = new Document(PageSize.A4, 40, 40, 40, 40);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            
            // Add header and footer
            HeaderFooter headerFooter = new HeaderFooter();
            writer.setPageEvent(headerFooter);
            
            document.open();
            
            // ============ HEADER SECTION ============
            // Logo placeholder (you can add actual logo later)
            Paragraph logoText = new Paragraph("📚", FontFactory.getFont(FontFactory.HELVETICA, 48, Font.NORMAL, PRIMARY_COLOR));
            logoText.setAlignment(Element.ALIGN_CENTER);
            logoText.setSpacingAfter(10);
            document.add(logoText);
            
            // School Name
            Font schoolNameFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, DARK_TEXT);
            Paragraph schoolName = new Paragraph("SMK AL-ASIYAH", schoolNameFont);
            schoolName.setAlignment(Element.ALIGN_CENTER);
            schoolName.setSpacingAfter(5);
            document.add(schoolName);
            
            // Subtitle
            Font subtitleFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.GRAY);
            Paragraph subtitle = new Paragraph("Perpustakaan SMK AL-ASIYAH", subtitleFont);
            subtitle.setAlignment(Element.ALIGN_CENTER);
            subtitle.setSpacingAfter(5);
            document.add(subtitle);
            
            // Address
            Font addressFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY);
            Paragraph address = new Paragraph("Bogor, Jawa Barat", addressFont);
            address.setAlignment(Element.ALIGN_CENTER);
            address.setSpacingAfter(20);
            document.add(address);
            
            // Separator Line
            Paragraph separator = new Paragraph();
            separator.setSpacingBefore(10);
            separator.setSpacingAfter(15);
            separator.add(new Chunk("_________________________________________________________________________"));
            separator.setAlignment(Element.ALIGN_CENTER);
            document.add(separator);
            
            // Report Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, PRIMARY_COLOR);
            Paragraph title = new Paragraph("LAPORAN " + jenisLaporan.toUpperCase() + " PEMINJAMAN BUKU", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(10);
            document.add(title);
            
            // Date Information
            Font dateFont = FontFactory.getFont(FontFactory.HELVETICA, 11, DARK_TEXT);
            String dateText;
            if ("SEMUA DATA".equals(jenisLaporan.toUpperCase())) {
                dateText = "Dicetak: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
            } else {
                dateText = "Tanggal: " + tanggal.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
            }
            Paragraph datePara = new Paragraph(dateText, dateFont);
            datePara.setAlignment(Element.ALIGN_CENTER);
            datePara.setSpacingAfter(20);
            document.add(datePara);
            
            // ============ TABLE SECTION ============
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(20f);
            table.setWidths(new float[]{0.7f, 2f, 3f, 3f, 1.8f});
            
            // Table Header
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.WHITE);
            String[] headers = {"No", "Tanggal Pinjam", "Buku", "Anggota", "Status"};
            
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                cell.setBackgroundColor(PRIMARY_COLOR);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setPadding(10);
                cell.setBorderWidth(1f);
                cell.setBorderColor(PRIMARY_COLOR);
                table.addCell(cell);
            }
            
            // Table Data
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10, DARK_TEXT);
            Font dataBoldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, DARK_TEXT);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            int no = 1;
            boolean alternate = false;
            for (Peminjaman p : data) {
                // No
                PdfPCell noCell = new PdfPCell(new Phrase(String.valueOf(no++), dataFont));
                noCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                noCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                noCell.setPadding(8);
                noCell.setBorderColor(BORDER_COLOR);
                if (alternate) noCell.setBackgroundColor(LIGHT_GRAY);
                table.addCell(noCell);
                
                // Tanggal
                PdfPCell tanggalCell = new PdfPCell(new Phrase(p.getTglPinjam().format(formatter), dataFont));
                tanggalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tanggalCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tanggalCell.setPadding(8);
                tanggalCell.setBorderColor(BORDER_COLOR);
                if (alternate) tanggalCell.setBackgroundColor(LIGHT_GRAY);
                table.addCell(tanggalCell);
                
                // Buku
                PdfPCell bukuCell = new PdfPCell(new Phrase(p.getNamaBuku(), dataFont));
                bukuCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                bukuCell.setPadding(8);
                bukuCell.setBorderColor(BORDER_COLOR);
                if (alternate) bukuCell.setBackgroundColor(LIGHT_GRAY);
                table.addCell(bukuCell);
                
                // Anggota
                PdfPCell anggotaCell = new PdfPCell(new Phrase(p.getNamaAnggota(), dataFont));
                anggotaCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                anggotaCell.setPadding(8);
                anggotaCell.setBorderColor(BORDER_COLOR);
                if (alternate) anggotaCell.setBackgroundColor(LIGHT_GRAY);
                table.addCell(anggotaCell);
                
                // Status
                String status = p.getStatusPinjam() != null ? p.getStatusPinjam() : "dipinjam";
                BaseColor statusColor = status.equalsIgnoreCase("dikembalikan") ? 
                    new BaseColor(39, 174, 96) : new BaseColor(231, 76, 60);
                Font statusFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, statusColor);
                PdfPCell statusCell = new PdfPCell(new Phrase(status.toUpperCase(), statusFont));
                statusCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                statusCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                statusCell.setPadding(8);
                statusCell.setBorderColor(BORDER_COLOR);
                if (alternate) statusCell.setBackgroundColor(LIGHT_GRAY);
                table.addCell(statusCell);
                
                alternate = !alternate;
            }
            
            document.add(table);
            
            // ============ SUMMARY SECTION ============
            Font summaryFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, DARK_TEXT);
            Paragraph summary = new Paragraph("Total: " + data.size() + " transaksi", summaryFont);
            summary.setSpacingBefore(10);
            summary.setSpacingAfter(30);
            document.add(summary);
            
            // ============ SIGNATURE SECTION ============
            // Add space before signature
            document.add(Chunk.NEWLINE);
            
            PdfPTable signatureTable = new PdfPTable(2);
            signatureTable.setWidthPercentage(100);
            signatureTable.setWidths(new float[]{1f, 1f});
            signatureTable.setSpacingBefore(20);
            
            Font signatureFont = FontFactory.getFont(FontFactory.HELVETICA, 11, DARK_TEXT);
            Font signatureBoldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, DARK_TEXT);
            Font signaturePositionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, PRIMARY_COLOR);
            
            // ========== LEFT COLUMN: Admin Perpustakaan ==========
            Paragraph adminSignature = new Paragraph();
            adminSignature.setAlignment(Element.ALIGN_CENTER);
            adminSignature.add(new Chunk("Bogor, " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")), signatureFont));
            adminSignature.add(Chunk.NEWLINE);
            adminSignature.add(new Chunk("Admin Perpustakaan", signaturePositionFont));
            adminSignature.add(Chunk.NEWLINE);
            adminSignature.add(Chunk.NEWLINE);
            adminSignature.add(Chunk.NEWLINE);
            adminSignature.add(Chunk.NEWLINE);
            adminSignature.add(new Chunk("\n\n____________________________\n", signatureFont));
            adminSignature.add(new Chunk("( ..................................................... )\n", signatureFont));
            adminSignature.add(new Chunk("NIP: ........................................", signatureFont));
            
            PdfPCell adminCell = new PdfPCell(adminSignature);
            adminCell.setBorder(Rectangle.NO_BORDER);
            adminCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            adminCell.setPaddingTop(20);
            adminCell.setPaddingRight(20);
            signatureTable.addCell(adminCell);
            
            // ========== RIGHT COLUMN: Kepala Sekolah ==========
            Paragraph kepalaSekolahSignature = new Paragraph();
            kepalaSekolahSignature.setAlignment(Element.ALIGN_CENTER);
            kepalaSekolahSignature.add(new Chunk("Bogor, " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")), signatureFont));
            kepalaSekolahSignature.add(Chunk.NEWLINE);
            kepalaSekolahSignature.add(new Chunk("Kepala Sekolah", signaturePositionFont));
            kepalaSekolahSignature.add(Chunk.NEWLINE);
            kepalaSekolahSignature.add(Chunk.NEWLINE);
            kepalaSekolahSignature.add(Chunk.NEWLINE);
            kepalaSekolahSignature.add(Chunk.NEWLINE);
            kepalaSekolahSignature.add(new Chunk("\n\n____________________________\n", signatureFont));
            kepalaSekolahSignature.add(new Chunk("( ..................................................... )\n", signatureFont));
            kepalaSekolahSignature.add(new Chunk("NIP: ........................................", signatureFont));
            
            PdfPCell kepalaSekolahCell = new PdfPCell(kepalaSekolahSignature);
            kepalaSekolahCell.setBorder(Rectangle.NO_BORDER);
            kepalaSekolahCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            kepalaSekolahCell.setPaddingTop(20);
            kepalaSekolahCell.setPaddingLeft(20);
            signatureTable.addCell(kepalaSekolahCell);
            
            document.add(signatureTable);
            
            document.close();
            writer.close();
            
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF report: " + e.getMessage(), e);
        }
    }
    
    public static void generateExcelReport(List<Peminjaman> data, File outputFile, String jenisLaporan, LocalDate tanggal) {
        try {
            org.apache.poi.ss.usermodel.Workbook workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook();
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Laporan " + jenisLaporan);
            
            // Create header style
            org.apache.poi.ss.usermodel.CellStyle headerStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setColor(org.apache.poi.ss.usermodel.IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
            headerStyle.setFillForegroundColor(org.apache.poi.ss.usermodel.IndexedColors.DARK_BLUE.getIndex());
            headerStyle.setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            headerStyle.setBorderTop(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            headerStyle.setBorderLeft(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            headerStyle.setBorderRight(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            
            // Create title style
            org.apache.poi.ss.usermodel.CellStyle titleStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 16);
            titleFont.setColor(org.apache.poi.ss.usermodel.IndexedColors.DARK_BLUE.getIndex());
            titleStyle.setFont(titleFont);
            titleStyle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
            
            // Create data style
            org.apache.poi.ss.usermodel.CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setBorderBottom(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            dataStyle.setBorderTop(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            dataStyle.setBorderLeft(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            dataStyle.setBorderRight(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            dataStyle.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
            
            // Create alternate row style
            org.apache.poi.ss.usermodel.CellStyle alternateStyle = workbook.createCellStyle();
            alternateStyle.cloneStyleFrom(dataStyle);
            alternateStyle.setFillForegroundColor(org.apache.poi.ss.usermodel.IndexedColors.GREY_25_PERCENT.getIndex());
            alternateStyle.setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
            
            int rowNum = 0;
            
            // Title
            org.apache.poi.ss.usermodel.Row titleRow = sheet.createRow(rowNum++);
            titleRow.setHeight((short) 500);
            org.apache.poi.ss.usermodel.Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("SMK AL-ASIYAH - PERPUSTAKAAN");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 4));
            
            // Report title
            org.apache.poi.ss.usermodel.Row reportTitleRow = sheet.createRow(rowNum++);
            reportTitleRow.setHeight((short) 400);
            org.apache.poi.ss.usermodel.Cell reportTitleCell = reportTitleRow.createCell(0);
            reportTitleCell.setCellValue("LAPORAN " + jenisLaporan.toUpperCase() + " PEMINJAMAN BUKU");
            reportTitleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, 1, 0, 4));
            
            // Date row (different for "Semua Data")
            org.apache.poi.ss.usermodel.Row dateRow = sheet.createRow(rowNum++);
            org.apache.poi.ss.usermodel.Cell dateCell = dateRow.createCell(0);
            if (!"SEMUA DATA".equals(jenisLaporan.toUpperCase())) {
                dateCell.setCellValue("Tanggal: " + tanggal.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
            } else {
                dateCell.setCellValue("Dicetak: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
            }
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(2, 2, 0, 4));
            
            // Empty row
            rowNum++;
            
            // Header row
            org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(rowNum++);
            headerRow.setHeight((short) 400);
            String[] headers = {"No", "Tanggal Pinjam", "Buku", "Anggota", "Status"};
            for (int i = 0; i < headers.length; i++) {
                org.apache.poi.ss.usermodel.Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Data rows
            int no = 1;
            boolean alternate = false;
            for (Peminjaman peminjaman : data) {
                org.apache.poi.ss.usermodel.Row dataRow = sheet.createRow(rowNum++);
                org.apache.poi.ss.usermodel.CellStyle currentStyle = alternate ? alternateStyle : dataStyle;
                
                org.apache.poi.ss.usermodel.Cell cell0 = dataRow.createCell(0);
                cell0.setCellValue(no++);
                cell0.setCellStyle(currentStyle);
                
                org.apache.poi.ss.usermodel.Cell cell1 = dataRow.createCell(1);
                cell1.setCellValue(peminjaman.getTglPinjam().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                cell1.setCellStyle(currentStyle);
                
                org.apache.poi.ss.usermodel.Cell cell2 = dataRow.createCell(2);
                cell2.setCellValue(peminjaman.getNamaBuku());
                cell2.setCellStyle(currentStyle);
                
                org.apache.poi.ss.usermodel.Cell cell3 = dataRow.createCell(3);
                cell3.setCellValue(peminjaman.getNamaAnggota());
                cell3.setCellStyle(currentStyle);
                
                org.apache.poi.ss.usermodel.Cell cell4 = dataRow.createCell(4);
                String status = peminjaman.getStatusPinjam() != null ? peminjaman.getStatusPinjam() : "dipinjam";
                cell4.setCellValue(status.toUpperCase());
                cell4.setCellStyle(currentStyle);
                
                alternate = !alternate;
            }
            
            // Total row
            rowNum++;
            org.apache.poi.ss.usermodel.Row totalRow = sheet.createRow(rowNum++);
            org.apache.poi.ss.usermodel.Cell totalCell = totalRow.createCell(0);
            org.apache.poi.ss.usermodel.CellStyle boldStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font boldFont = workbook.createFont();
            boldFont.setBold(true);
            boldFont.setFontHeightInPoints((short) 12);
            boldStyle.setFont(boldFont);
            totalCell.setCellValue("Total: " + data.size() + " transaksi");
            totalCell.setCellStyle(boldStyle);
            
            // Signature section
            rowNum += 2;
            
            // Create signature header
            org.apache.poi.ss.usermodel.CellStyle signatureBoldStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font signatureBoldFont = workbook.createFont();
            signatureBoldFont.setBold(true);
            signatureBoldFont.setFontHeightInPoints((short) 11);
            signatureBoldFont.setColor(org.apache.poi.ss.usermodel.IndexedColors.DARK_BLUE.getIndex());
            signatureBoldStyle.setFont(signatureBoldFont);
            signatureBoldStyle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
            
            // Admin Perpustakaan (Left Column)
            org.apache.poi.ss.usermodel.Row adminDateRow = sheet.createRow(rowNum);
            org.apache.poi.ss.usermodel.Cell adminDateCell = adminDateRow.createCell(1);
            adminDateCell.setCellValue("Bogor, " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
            
            org.apache.poi.ss.usermodel.Row adminPositionRow = sheet.createRow(rowNum + 1);
            org.apache.poi.ss.usermodel.Cell adminPositionCell = adminPositionRow.createCell(1);
            adminPositionCell.setCellValue("Admin Perpustakaan");
            adminPositionCell.setCellStyle(signatureBoldStyle);
            
            // Kepala Sekolah (Right Column)
            org.apache.poi.ss.usermodel.Cell kepalaDateCell = adminDateRow.createCell(3);
            kepalaDateCell.setCellValue("Bogor, " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
            
            org.apache.poi.ss.usermodel.Cell kepalaPositionCell = adminPositionRow.createCell(3);
            kepalaPositionCell.setCellValue("Kepala Sekolah");
            kepalaPositionCell.setCellStyle(signatureBoldStyle);
            
            rowNum += 6; // Space for signature
            
            // Admin Name
            org.apache.poi.ss.usermodel.Row adminNameRow = sheet.createRow(rowNum);
            org.apache.poi.ss.usermodel.Cell adminNameCell = adminNameRow.createCell(1);
            adminNameCell.setCellValue("( ..................................................... )");
            
            // Kepala Sekolah Name
            org.apache.poi.ss.usermodel.Cell kepalaNameCell = adminNameRow.createCell(3);
            kepalaNameCell.setCellValue("( ..................................................... )");
            
            // Admin NIP
            org.apache.poi.ss.usermodel.Row adminNIPRow = sheet.createRow(rowNum + 1);
            org.apache.poi.ss.usermodel.Cell adminNIPCell = adminNIPRow.createCell(1);
            adminNIPCell.setCellValue("NIP: ........................................");
            
            // Kepala Sekolah NIP
            org.apache.poi.ss.usermodel.Cell kepalaNIPCell = adminNIPRow.createCell(3);
            kepalaNIPCell.setCellValue("NIP: ........................................");
            
            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
                // Add extra width for readability
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1000);
            }
            
            // Write to file
            try (java.io.FileOutputStream fileOut = new java.io.FileOutputStream(outputFile)) {
                workbook.write(fileOut);
            }
            
            workbook.close();
            
        } catch (Exception e) {
            throw new RuntimeException("Error generating Excel report: " + e.getMessage(), e);
        }
    }
    
    // Header Footer Event Handler
    static class HeaderFooter extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                Font footerFont = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.GRAY);
                
                // Footer
                Phrase footer = new Phrase("Halaman " + writer.getPageNumber(), footerFont);
                ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER,
                    footer,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom() - 10,
                    0);
                
                // Generated timestamp
                Phrase timestamp = new Phrase("Digenerate: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), footerFont);
                ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_RIGHT,
                    timestamp,
                    document.right(),
                    document.bottom() - 10,
                    0);
                    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
