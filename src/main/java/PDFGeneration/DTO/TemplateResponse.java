package PDFGeneration.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class TemplateResponse {
    public HttpStatus status;
    public String errorDescription;
}
