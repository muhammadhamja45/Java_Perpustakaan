package com.smk.alasiyah.perpustakaan.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.smk.alasiyah.perpustakaan.model.*;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PDFReportGeneratorExtended {
    
    // Colors untuk desain modern
    private static final BaseColor PRIMARY_COLOR = new BaseColor(102, 126, 234); // #667eea
    private static final BaseColor SECONDARY_COLOR = new BaseColor(118, 75, 162); // #764ba2
    private static final BaseColor DARK_TEXT = new BaseColor(44, 62, 80); // #2c3e50
    private static final BaseColor LIGHT_GRAY = new BaseColor(248, 249, 250); // #f8f9fa
    private static final BaseColor BORDER_COLOR = new BaseColor(224, 224, 224); // #e0e0e0
    
    // ============ GURU REPORTS ============
    public static void generateGuruPDF(List<Guru> data, File outputFile) {
        try {
            Document document = new Document(PageSize.A4.rotate(), 40, 40, 40, 40);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            
            PDFReportGenerator.HeaderFooter headerFooter = new PDFReportGenerator.HeaderFooter();
            writer.setPageEvent(headerFooter);
            
            document.open();
            
            addHeader(document, "LAPORAN DATA GURU");
            
            // Table
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(20f);
            table.setWidths(new float[]{0.5f, 1.2f, 2f, 1.5f, 1.2f, 2f});
            
            // Headers
            String[] headers = {"No", "NIP", "Nama Lengkap", "Mata Pelajaran", "No. Telepon", "Alamat"};
            addTableHeaders(table, headers);
            
            // Data
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 9, DARK_TEXT);
            int no = 1;
            boolean alternate = false;
            
            for (Guru guru : data) {
                addTableCell(table, String.valueOf(no++), dataFont, Element.ALIGN_CENTER, alternate);
                addTableCell(table, guru.getNip(), dataFont, Element.ALIGN_LEFT, alternate);
                addTableCell(table, guru.getNama(), dataFont, Element.ALIGN_LEFT, alternate);
                addTableCell(table, guru.getJabatan(), dataFont, Element.ALIGN_LEFT, alternate);
                addTableCell(table, guru.getNoTelp(), dataFont, Element.ALIGN_CENTER, alternate);
                addTableCell(table, guru.getAlamat(), dataFont, Element.ALIGN_LEFT, alternate);
                alternate = !alternate;
            }
            
            document.add(table);
            addSummary(document, data.size());
            addSignature(document);
            
            document.close();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException("Error generating Guru PDF report: " + e.getMessage(), e);
        }
    }
    
    // ============ SISWA REPORTS ============
    public static void generateSiswaPDF(List<Siswa> data, File outputFile) {
        try {
            Document document = new Document(PageSize.A4.rotate(), 40, 40, 40, 40);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            
            PDFReportGenerator.HeaderFooter headerFooter = new PDFReportGenerator.HeaderFooter();
            writer.setPageEvent(headerFooter);
            
            document.open();
            
            addHeader(document, "LAPORAN DATA SISWA");
            
            // Table
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(20f);
            table.setWidths(new float[]{0.5f, 1.2f, 2f, 0.8f, 1.2f, 2f});
            
            // Headers
            String[] headers = {"No", "NIS", "Nama Lengkap", "Kelas", "No. Telepon", "Alamat"};
            addTableHeaders(table, headers);
            
            // Data
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 9, DARK_TEXT);
            int no = 1;
            boolean alternate = false;
            
            for (Siswa siswa : data) {
                addTableCell(table, String.valueOf(no++), dataFont, Element.ALIGN_CENTER, alternate);
                addTableCell(table, siswa.getNis(), dataFont, Element.ALIGN_LEFT, alternate);
                addTableCell(table, siswa.getNama(), dataFont, Element.ALIGN_LEFT, alternate);
                addTableCell(table, siswa.getKelas(), dataFont, Element.ALIGN_CENTER, alternate);
                addTableCell(table, siswa.getNoTelp(), dataFont, Element.ALIGN_CENTER, alternate);
                addTableCell(table, siswa.getAlamat(), dataFont, Element.ALIGN_LEFT, alternate);
                alternate = !alternate;
            }
            
            document.add(table);
            addSummary(document, data.size());
            addSignature(document);
            
            document.close();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException("Error generating Siswa PDF report: " + e.getMessage(), e);
        }
    }
    
    // ============ BUKU REPORTS ============
    public static void generateBukuPDF(List<Buku> data, File outputFile) {
        try {
            Document document = new Document(PageSize.A4.rotate(), 40, 40, 40, 40);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            
            PDFReportGenerator.HeaderFooter headerFooter = new PDFReportGenerator.HeaderFooter();
            writer.setPageEvent(headerFooter);
            
            document.open();
            
            addHeader(document, "LAPORAN DATA BUKU");
            
            // Table
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(20f);
            table.setWidths(new float[]{0.5f, 1.2f, 2.5f, 1.5f, 1.5f, 0.8f, 0.7f});
            
            // Headers
            String[] headers = {"No", "Kode Buku", "Judul Buku", "Pengarang", "Penerbit", "Tahun", "Stok"};
            addTableHeaders(table, headers);
            
            // Data
            Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 9, DARK_TEXT);
            int no = 1;
            boolean alternate = false;
            
            for (Buku buku : data) {
                addTableCell(table, String.valueOf(no++), dataFont, Element.ALIGN_CENTER, alternate);
                addTableCell(table, buku.getKodeBuku(), dataFont, Element.ALIGN_LEFT, alternate);
                addTableCell(table, buku.getJudul(), dataFont, Element.ALIGN_LEFT, alternate);
                addTableCell(table, buku.getPengarang(), dataFont, Element.ALIGN_LEFT, alternate);
                addTableCell(table, buku.getPenerbit(), dataFont, Element.ALIGN_LEFT, alternate);
                addTableCell(table, String.valueOf(buku.getTahun()), dataFont, Element.ALIGN_CENTER, alternate);
                addTableCell(table, String.valueOf(buku.getStok()), dataFont, Element.ALIGN_CENTER, alternate);
                alternate = !alternate;
            }
            
            document.add(table);
            addSummary(document, data.size());
            addSignature(document);
            
            document.close();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException("Error generating Buku PDF report: " + e.getMessage(), e);
        }
    }
    
    // ============ EXCEL REPORTS ============
    public static void generateGuruExcel(List<Guru> data, File outputFile) {
        try {
            org.apache.poi.ss.usermodel.Workbook workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook();
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Laporan Data Guru");
            
            String[] headers = {"No", "NIP", "Nama Lengkap", "Mata Pelajaran", "No. Telepon", "Alamat"};
            createExcelReport(workbook, sheet, "LAPORAN DATA GURU", headers, data, (row, guru, style, altStyle, no) -> {
                boolean alternate = (no % 2 == 0);
                org.apache.poi.ss.usermodel.CellStyle currentStyle = alternate ? altStyle : style;
                
                createCell(row, 0, no, currentStyle);
                createCell(row, 1, guru.getNip(), currentStyle);
                createCell(row, 2, guru.getNama(), currentStyle);
                createCell(row, 3, guru.getJabatan(), currentStyle);
                createCell(row, 4, guru.getNoTelp(), currentStyle);
                createCell(row, 5, guru.getAlamat(), currentStyle);
            });
            
            autoSizeColumns(sheet, headers.length);
            
            try (java.io.FileOutputStream fileOut = new java.io.FileOutputStream(outputFile)) {
                workbook.write(fileOut);
            }
            workbook.close();
        } catch (Exception e) {
            throw new RuntimeException("Error generating Guru Excel report: " + e.getMessage(), e);
        }
    }
    
    public static void generateSiswaExcel(List<Siswa> data, File outputFile) {
        try {
            org.apache.poi.ss.usermodel.Workbook workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook();
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Laporan Data Siswa");
            
            String[] headers = {"No", "NIS", "Nama Lengkap", "Kelas", "No. Telepon", "Alamat"};
            createExcelReport(workbook, sheet, "LAPORAN DATA SISWA", headers, data, (row, siswa, style, altStyle, no) -> {
                boolean alternate = (no % 2 == 0);
                org.apache.poi.ss.usermodel.CellStyle currentStyle = alternate ? altStyle : style;
                
                createCell(row, 0, no, currentStyle);
                createCell(row, 1, siswa.getNis(), currentStyle);
                createCell(row, 2, siswa.getNama(), currentStyle);
                createCell(row, 3, siswa.getKelas(), currentStyle);
                createCell(row, 4, siswa.getNoTelp(), currentStyle);
                createCell(row, 5, siswa.getAlamat(), currentStyle);
            });
            
            autoSizeColumns(sheet, headers.length);
            
            try (java.io.FileOutputStream fileOut = new java.io.FileOutputStream(outputFile)) {
                workbook.write(fileOut);
            }
            workbook.close();
        } catch (Exception e) {
            throw new RuntimeException("Error generating Siswa Excel report: " + e.getMessage(), e);
        }
    }
    
    public static void generateBukuExcel(List<Buku> data, File outputFile) {
        try {
            org.apache.poi.ss.usermodel.Workbook workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook();
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Laporan Data Buku");
            
            String[] headers = {"No", "Kode Buku", "Judul Buku", "Pengarang", "Penerbit", "Tahun Terbit", "Stok"};
            createExcelReport(workbook, sheet, "LAPORAN DATA BUKU", headers, data, (row, buku, style, altStyle, no) -> {
                boolean alternate = (no % 2 == 0);
                org.apache.poi.ss.usermodel.CellStyle currentStyle = alternate ? altStyle : style;
                
                createCell(row, 0, no, currentStyle);
                createCell(row, 1, buku.getKodeBuku(), currentStyle);
                createCell(row, 2, buku.getJudul(), currentStyle);
                createCell(row, 3, buku.getPengarang(), currentStyle);
                createCell(row, 4, buku.getPenerbit(), currentStyle);
                createCell(row, 5, buku.getTahun(), currentStyle);
                createCell(row, 6, buku.getStok(), currentStyle);
            });
            
            autoSizeColumns(sheet, headers.length);
            
            try (java.io.FileOutputStream fileOut = new java.io.FileOutputStream(outputFile)) {
                workbook.write(fileOut);
            }
            workbook.close();
        } catch (Exception e) {
            throw new RuntimeException("Error generating Buku Excel report: " + e.getMessage(), e);
        }
    }
    
    // ============ HELPER METHODS ============
    private static void addHeader(Document document, String title) throws DocumentException {
        // Logo
        try {
            String logoPath = "src/main/resources/images/image.png";
            java.io.File logoFile = new java.io.File(logoPath);
            
            if (logoFile.exists()) {
                Image logo = Image.getInstance(logoPath);
                logo.scaleToFit(80, 80);
                logo.setAlignment(Element.ALIGN_CENTER);
                logo.setSpacingAfter(15);
                document.add(logo);
            }
        } catch (Exception e) {
            // Skip logo if error
        }
        
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
        
        // Separator
        Paragraph separator = new Paragraph();
        separator.setSpacingBefore(10);
        separator.setSpacingAfter(15);
        separator.add(new Chunk("_______________________________________________________________________________"));
        separator.setAlignment(Element.ALIGN_CENTER);
        document.add(separator);
        
        // Title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, PRIMARY_COLOR);
        Paragraph titlePara = new Paragraph(title, titleFont);
        titlePara.setAlignment(Element.ALIGN_CENTER);
        titlePara.setSpacingAfter(10);
        document.add(titlePara);
        
        // Date
        Font dateFont = FontFactory.getFont(FontFactory.HELVETICA, 11, DARK_TEXT);
        String dateText = "Dicetak: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        Paragraph datePara = new Paragraph(dateText, dateFont);
        datePara.setAlignment(Element.ALIGN_CENTER);
        datePara.setSpacingAfter(20);
        document.add(datePara);
    }
    
    private static void addTableHeaders(PdfPTable table, String[] headers) {
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.WHITE);
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
    }
    
    private static void addTableCell(PdfPTable table, String content, Font font, int alignment, boolean alternate) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setHorizontalAlignment(alignment);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(8);
        cell.setBorderColor(BORDER_COLOR);
        if (alternate) cell.setBackgroundColor(LIGHT_GRAY);
        table.addCell(cell);
    }
    
    private static void addSummary(Document document, int count) throws DocumentException {
        Font summaryFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, DARK_TEXT);
        Paragraph summary = new Paragraph("Total: " + count + " data", summaryFont);
        summary.setSpacingBefore(10);
        summary.setSpacingAfter(30);
        document.add(summary);
    }
    
    private static void addSignature(Document document) throws DocumentException {
        document.add(Chunk.NEWLINE);
        
        PdfPTable signatureTable = new PdfPTable(2);
        signatureTable.setWidthPercentage(100);
        signatureTable.setWidths(new float[]{1f, 1f});
        signatureTable.setSpacingBefore(20);
        
        Font signatureFont = FontFactory.getFont(FontFactory.HELVETICA, 11, DARK_TEXT);
        Font signaturePositionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, PRIMARY_COLOR);
        
        // Admin Perpustakaan
        Paragraph adminSignature = new Paragraph();
        adminSignature.setAlignment(Element.ALIGN_CENTER);
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
        
        // Kepala Sekolah
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
    }
    
    // Excel Helper Methods
    private static <T> void createExcelReport(
            org.apache.poi.ss.usermodel.Workbook workbook,
            org.apache.poi.ss.usermodel.Sheet sheet,
            String title,
            String[] headers,
            List<T> data,
            ExcelRowWriter<T> rowWriter) throws Exception {
        
        // Create styles
        org.apache.poi.ss.usermodel.CellStyle headerStyle = createHeaderStyle(workbook);
        org.apache.poi.ss.usermodel.CellStyle titleStyle = createTitleStyle(workbook);
        org.apache.poi.ss.usermodel.CellStyle dataStyle = createDataStyle(workbook);
        org.apache.poi.ss.usermodel.CellStyle alternateStyle = createAlternateStyle(workbook, dataStyle);
        
        int rowNum = 0;
        
        // Title
        org.apache.poi.ss.usermodel.Row titleRow = sheet.createRow(rowNum++);
        titleRow.setHeight((short) 500);
        org.apache.poi.ss.usermodel.Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("SMK AL-ASIYAH - PERPUSTAKAAN");
        titleCell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, headers.length - 1));
        
        // Report title
        org.apache.poi.ss.usermodel.Row reportTitleRow = sheet.createRow(rowNum++);
        reportTitleRow.setHeight((short) 400);
        org.apache.poi.ss.usermodel.Cell reportTitleCell = reportTitleRow.createCell(0);
        reportTitleCell.setCellValue(title);
        reportTitleCell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, 1, 0, headers.length - 1));
        
        // Date
        org.apache.poi.ss.usermodel.Row dateRow = sheet.createRow(rowNum++);
        org.apache.poi.ss.usermodel.Cell dateCell = dateRow.createCell(0);
        dateCell.setCellValue("Dicetak: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(2, 2, 0, headers.length - 1));
        
        rowNum++; // Empty row
        
        // Headers
        org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(rowNum++);
        headerRow.setHeight((short) 400);
        for (int i = 0; i < headers.length; i++) {
            org.apache.poi.ss.usermodel.Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
        
        // Data
        int no = 1;
        for (T item : data) {
            org.apache.poi.ss.usermodel.Row dataRow = sheet.createRow(rowNum++);
            rowWriter.writeRow(dataRow, item, dataStyle, alternateStyle, no++);
        }
        
        // Total
        rowNum++;
        org.apache.poi.ss.usermodel.Row totalRow = sheet.createRow(rowNum++);
        org.apache.poi.ss.usermodel.Cell totalCell = totalRow.createCell(0);
        org.apache.poi.ss.usermodel.CellStyle boldStyle = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        boldFont.setFontHeightInPoints((short) 12);
        boldStyle.setFont(boldFont);
        totalCell.setCellValue("Total: " + data.size() + " data");
        totalCell.setCellStyle(boldStyle);
        
        // Signature
        addExcelSignature(sheet, rowNum + 2, workbook);
    }
    
    private static org.apache.poi.ss.usermodel.CellStyle createHeaderStyle(org.apache.poi.ss.usermodel.Workbook workbook) {
        org.apache.poi.ss.usermodel.CellStyle style = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        font.setColor(org.apache.poi.ss.usermodel.IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
        style.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
        style.setFillForegroundColor(org.apache.poi.ss.usermodel.IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(org.apache.poi.ss.usermodel.BorderStyle.THIN);
        style.setBorderTop(org.apache.poi.ss.usermodel.BorderStyle.THIN);
        style.setBorderLeft(org.apache.poi.ss.usermodel.BorderStyle.THIN);
        style.setBorderRight(org.apache.poi.ss.usermodel.BorderStyle.THIN);
        return style;
    }
    
    private static org.apache.poi.ss.usermodel.CellStyle createTitleStyle(org.apache.poi.ss.usermodel.Workbook workbook) {
        org.apache.poi.ss.usermodel.CellStyle style = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        font.setColor(org.apache.poi.ss.usermodel.IndexedColors.DARK_BLUE.getIndex());
        style.setFont(font);
        style.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
        return style;
    }
    
    private static org.apache.poi.ss.usermodel.CellStyle createDataStyle(org.apache.poi.ss.usermodel.Workbook workbook) {
        org.apache.poi.ss.usermodel.CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(org.apache.poi.ss.usermodel.BorderStyle.THIN);
        style.setBorderTop(org.apache.poi.ss.usermodel.BorderStyle.THIN);
        style.setBorderLeft(org.apache.poi.ss.usermodel.BorderStyle.THIN);
        style.setBorderRight(org.apache.poi.ss.usermodel.BorderStyle.THIN);
        style.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
        return style;
    }
    
    private static org.apache.poi.ss.usermodel.CellStyle createAlternateStyle(
            org.apache.poi.ss.usermodel.Workbook workbook, 
            org.apache.poi.ss.usermodel.CellStyle baseStyle) {
        org.apache.poi.ss.usermodel.CellStyle style = workbook.createCellStyle();
        style.cloneStyleFrom(baseStyle);
        style.setFillForegroundColor(org.apache.poi.ss.usermodel.IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND);
        return style;
    }
    
    private static void createCell(org.apache.poi.ss.usermodel.Row row, int column, Object value, 
                                   org.apache.poi.ss.usermodel.CellStyle style) {
        org.apache.poi.ss.usermodel.Cell cell = row.createCell(column);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else {
            cell.setCellValue(value.toString());
        }
        cell.setCellStyle(style);
    }
    
    private static void autoSizeColumns(org.apache.poi.ss.usermodel.Sheet sheet, int columnCount) {
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1000);
        }
    }
    
    private static void addExcelSignature(org.apache.poi.ss.usermodel.Sheet sheet, int startRow, 
                                         org.apache.poi.ss.usermodel.Workbook workbook) {
        org.apache.poi.ss.usermodel.CellStyle signatureBoldStyle = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font signatureBoldFont = workbook.createFont();
        signatureBoldFont.setBold(true);
        signatureBoldFont.setFontHeightInPoints((short) 11);
        signatureBoldFont.setColor(org.apache.poi.ss.usermodel.IndexedColors.DARK_BLUE.getIndex());
        signatureBoldStyle.setFont(signatureBoldFont);
        signatureBoldStyle.setAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER);
        
        // Admin Perpustakaan
        org.apache.poi.ss.usermodel.Row adminPositionRow = sheet.createRow(startRow + 1);
        org.apache.poi.ss.usermodel.Cell adminPositionCell = adminPositionRow.createCell(1);
        adminPositionCell.setCellValue("Admin Perpustakaan");
        adminPositionCell.setCellStyle(signatureBoldStyle);
        
        // Kepala Sekolah
        org.apache.poi.ss.usermodel.Row adminDateRow = sheet.createRow(startRow);
        org.apache.poi.ss.usermodel.Cell kepalaDateCell = adminDateRow.createCell(3);
        kepalaDateCell.setCellValue("Bogor, " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
        
        org.apache.poi.ss.usermodel.Cell kepalaPositionCell = adminPositionRow.createCell(3);
        kepalaPositionCell.setCellValue("Kepala Sekolah");
        kepalaPositionCell.setCellStyle(signatureBoldStyle);
        
        // Signature lines
        org.apache.poi.ss.usermodel.Row nameRow = sheet.createRow(startRow + 7);
        nameRow.createCell(1).setCellValue("( ..................................................... )");
        nameRow.createCell(3).setCellValue("( ..................................................... )");
        
        org.apache.poi.ss.usermodel.Row nipRow = sheet.createRow(startRow + 8);
        nipRow.createCell(1).setCellValue("NIP: ........................................");
        nipRow.createCell(3).setCellValue("NIP: ........................................");
    }
    
    @FunctionalInterface
    interface ExcelRowWriter<T> {
        void writeRow(org.apache.poi.ss.usermodel.Row row, T item, 
                     org.apache.poi.ss.usermodel.CellStyle style, 
                     org.apache.poi.ss.usermodel.CellStyle altStyle, 
                     int no) throws Exception;
    }
}

