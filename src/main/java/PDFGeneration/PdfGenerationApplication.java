package PDFGeneration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class PdfGenerationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfGenerationApplication.class, args);
	}

}
