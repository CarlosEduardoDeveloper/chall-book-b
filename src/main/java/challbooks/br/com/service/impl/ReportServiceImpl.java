package challbooks.br.com.service.impl;

import challbooks.br.com.domain.VwBooksByAuthor;
import challbooks.br.com.repository.ReportRepository;
import challbooks.br.com.service.ReportService;
import challbooks.br.com.utils.HeaderFooterPageEvent;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository repository;

    @Override
    public byte[] generateBooksByAuthorReport() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            Document pdf = new Document(PageSize.A4, 40, 40, 80, 50);
            PdfWriter writer = PdfWriter.getInstance(pdf, baos);
            writer.setPageEvent(new HeaderFooterPageEvent());
            pdf.open();

            Paragraph title = new Paragraph(
                    "Catálogo\n\n",
                    FontFactory.getFont("Helvetica", 20, Font.BOLD)
            );
            title.setAlignment(Element.ALIGN_CENTER);
            pdf.add(title);

            List<VwBooksByAuthor> rows = repository.findReportData();

            Map<Long, BookGroup> groups = groupBookData(rows);

            renderPdfContent(pdf, groups);

            pdf.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }

    private Map<Long, BookGroup> groupBookData(List<VwBooksByAuthor> rows) {
        Map<Long, BookGroup> groups = new LinkedHashMap<>();

        for (VwBooksByAuthor row : rows) {
            Long bookId = row.getBookId();

            if (bookId == null) {
                bookId = (long) row.getBookTitle().hashCode();
            }

            BookGroup g = groups.computeIfAbsent(bookId, id -> {
                BookGroup newGroup = new BookGroup();
                newGroup.bookId = id;
                newGroup.title = row.getBookTitle();
                newGroup.publisher = row.getPublisher();
                newGroup.edition = String.valueOf(row.getEdition());
                newGroup.publicationYear = row.getPublicationYear() != null
                        ? String.valueOf(row.getPublicationYear()) : "";
                newGroup.price = row.getPrice() != null
                        ? String.valueOf(row.getPrice()) : "";
                return newGroup;
            });

            if (row.getAuthorName() != null && !row.getAuthorName().trim().isEmpty()) {
                String authorName = row.getAuthorName().trim();
                boolean added = g.authors.add(authorName);
            }

            if (row.getSubjectDescription() != null && !row.getSubjectDescription().trim().isEmpty()) {
                g.subjects.add(row.getSubjectDescription().trim());
            }
        }

        return groups;
    }

    private void renderPdfContent(Document pdf, Map<Long, BookGroup> groups) throws DocumentException {
        for (BookGroup g : groups.values()) {

            pdf.add(new Paragraph(
                    "\nLivro: " + (g.title == null ? "—" : g.title),
                    FontFactory.getFont("Helvetica", 16, Font.BOLD)
            ));

            pdf.add(new Paragraph(
                    "Ano: " + g.publicationYear + "\n" +
                            "Editora: " + (g.publisher == null ? "" : g.publisher) + "\n" +
                            "Edição: " + (g.edition == null ? "" : g.edition) + "\n" +
                            "Preço: R$ " + g.price + "\n",
                    FontFactory.getFont("Helvetica", 12)
            ));

            String authorsLine = g.authors.isEmpty() ? "—" : String.join(", ", g.authors);
            pdf.add(new Paragraph(
                    "Autores: " + authorsLine + "\n",
                    FontFactory.getFont("Helvetica", 12, Font.BOLD)
            ));

            String subjectsLine = g.subjects.isEmpty() ? "—" : String.join(", ", g.subjects);
            pdf.add(new Paragraph(
                    "Assuntos: " + subjectsLine + "\n",
                    FontFactory.getFont("Helvetica", 12, Font.BOLD)
            ));

            pdf.add(new Paragraph("\n"));
        }
    }

    private static class BookGroup {
        Long bookId;
        String title;
        String publisher;
        String edition;
        String publicationYear;
        String price;
        Set<String> authors = new LinkedHashSet<>();
        Set<String> subjects = new LinkedHashSet<>();
    }
}