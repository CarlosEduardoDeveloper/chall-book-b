package challbooks.br.com.utils;

import java.io.IOException;

public class HeaderFooterPageEvent extends com.lowagie.text.pdf.PdfPageEventHelper {

    private com.lowagie.text.Image logo;

    public HeaderFooterPageEvent() {
        try {
            logo = com.lowagie.text.Image.getInstance(
                    this.getClass().getResource("/static/logo.png")
            );
            logo.scaleToFit(80, 80);
        } catch (Exception ignored) {}
    }

    @Override
    public void onStartPage(com.lowagie.text.pdf.PdfWriter writer, com.lowagie.text.Document document) {
        if (logo != null) {
            logo.setAbsolutePosition(document.left(), document.top() + 10);
            try {
                writer.getDirectContent().addImage(logo);
            } catch (Exception ignored) {}
        }
    }

    @Override
    public void onEndPage(com.lowagie.text.pdf.PdfWriter writer, com.lowagie.text.Document document) {
        com.lowagie.text.pdf.PdfContentByte cb = writer.getDirectContent();
        cb.beginText();
        try {
            cb.setFontAndSize(com.lowagie.text.pdf.BaseFont.createFont(), 10);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        cb.showTextAligned(
                com.lowagie.text.Element.ALIGN_CENTER,
                "Page " + document.getPageNumber(),
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() - 20,
                0
        );

        cb.endText();
    }
}
