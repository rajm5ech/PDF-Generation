package PDFGeneration.Exception;

public class PdfTemplateNotFound extends RuntimeException {
    public PdfTemplateNotFound(String message) {
        super(message);
    }
}
