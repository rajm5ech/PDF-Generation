package PDFGeneration.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemplateInput {
    @NotNull
    @NotEmpty
    public String userId;
    @NotNull
    @NotEmpty
    public String h1Font;
    @NotNull
    @NotEmpty
    public String h1Size;
    @NotNull
    @NotEmpty
    public String h1Position;
    @NotNull
    @NotEmpty
    public String h2Font;
    @NotNull
    @NotEmpty
    public String h2Size;
    @NotNull
    @NotEmpty
    public String h2Position;
    @NotNull
    @NotEmpty
    public String itemFont;
    @NotNull
    @NotEmpty
    public String itemSize;
    @NotNull
    @NotEmpty
    public String itemTypography;
    @NotNull
    @NotEmpty
    public String currency;
}
