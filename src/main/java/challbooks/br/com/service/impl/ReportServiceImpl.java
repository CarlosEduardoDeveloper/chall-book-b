package challbooks.br.com.service.impl;

import challbooks.br.com.domain.VwBooksByAuthor;
import challbooks.br.com.repository.ReportRepository;
import challbooks.br.com.service.ReportService;
import challbooks.br.com.utils.HeaderFooterPageEvent;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository repository;

    @Override
    public byte[] generateBooksByAuthorReport() {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            
            com.lowagie.text.Document pdf =
                    new com.lowagie.text.Document(com.lowagie.text.PageSize.A4, 40, 40, 80, 50);
            
            com.lowagie.text.pdf.PdfWriter writer =
                    com.lowagie.text.pdf.PdfWriter.getInstance(pdf, baos);
            
            writer.setPageEvent(new HeaderFooterPageEvent());

            pdf.open();
            
            com.lowagie.text.Paragraph title = new com.lowagie.text.Paragraph(
                    "Books by Author Report\n\n",
                    com.lowagie.text.FontFactory.getFont("Helvetica", 20, com.lowagie.text.Font.BOLD)
            );
            title.setAlignment(com.lowagie.text.Element.ALIGN_CENTER);
            pdf.add(title);

            List<VwBooksByAuthor> rows = repository.findReportData();

            String currentAuthor = null;
            String currentBook = null;

            PdfPTable table = null;

            for (VwBooksByAuthor row : rows) {
                
                if (!row.getAuthorName().equals(currentAuthor)) {
                    currentAuthor = row.getAuthorName();

                    pdf.add(new com.lowagie.text.Paragraph(
                            "\nAuthor: " + currentAuthor + "\n",
                            com.lowagie.text.FontFactory.getFont("Helvetica", 16, com.lowagie.text.Font.BOLD)
                    ));
                }
                
                if (!row.getBookTitle().equals(currentBook)) {

                    currentBook = row.getBookTitle();

                    pdf.add(new com.lowagie.text.Paragraph(
                            "Book: " + currentBook + " (" + row.getPublicationYear() + ")\n" +
                                    "Publisher: " + row.getPublisher() + "\n" +
                                    "Edition: " + row.getEdition() + "\n" +
                                    "Price: R$ " + row.getPrice() + "\n\n",
                            com.lowagie.text.FontFactory.getFont("Helvetica", 12)
                    ));
                    
                    table = createStyledTable();
                }
                
                table.addCell(createValueCell(row.getSubjectDescription()));
                
                if (isNextRowNewBook(rows, row)) {
                    pdf.add(table);
                }
            }
            
            if (table != null) {
                pdf.add(table);
            }

            pdf.close();

            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF report", e);
        }
    }
    
    private PdfPTable createStyledTable() {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setSpacingBefore(5);
        table.setSpacingAfter(10);
        return table;
    }

    private PdfPCell createHeaderCell(String text) {
        PdfPCell cell = new PdfPCell(new com.lowagie.text.Phrase(
                text, com.lowagie.text.FontFactory.getFont("Helvetica", 12, com.lowagie.text.Font.BOLD)
        ));
        cell.setBackgroundColor(new java.awt.Color(230, 230, 230));
        cell.setPadding(8);
        return cell;
    }

    private PdfPCell createValueCell(String text) {
        PdfPCell cell = new PdfPCell(new com.lowagie.text.Phrase(
                text, com.lowagie.text.FontFactory.getFont("Helvetica", 11)
        ));
        cell.setPadding(6);
        return cell;
    }

    private boolean isNextRowNewBook(List<VwBooksByAuthor> rows, VwBooksByAuthor currentRow) {
        int index = rows.indexOf(currentRow);
        if (index == rows.size() - 1) return true;
        return !rows.get(index + 1).getBookTitle().equals(currentRow.getBookTitle());
    }
}