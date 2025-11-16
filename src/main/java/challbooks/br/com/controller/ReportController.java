package challbooks.br.com.controller;

import challbooks.br.com.service.ReportService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping(value = "/books-by-author", produces = "application/pdf")
    public ResponseEntity<byte[]> downloadPdf() {

        byte[] pdf = reportService.generateBooksByAuthorReport();

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "inline; filename=books_by_author.pdf")
                .body(pdf);
    }

    @GetMapping("/test")
    public String test() {
        return "Controller OK";
    }

    @PostConstruct
    public void init() {
        System.out.println("ReportController LOADED");
    }


}
