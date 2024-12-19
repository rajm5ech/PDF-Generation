package PDFGeneration.Exception;

public class PDFGenerationFailed extends RuntimeException {
    public PDFGenerationFailed(String message) {
        super(message);
    }

    public PDFGenerationFailed(String message, Throwable ex) {
        super(message, ex);
    }
}
