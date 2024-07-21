package PDFGeneration.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PDF_TEMPLATE")
@NoArgsConstructor
@Getter
@Setter
@NamedNativeQuery(name = "PDFTemplate.findById", query = "SELECT * FROM PDF_TEMPLATE WHERE USER_ID = :userID AND ID = :pdfTemplateID")
public class PDFTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_ID")
    private Integer id;

    @Column(name = "USER_ID")
    @NotNull(message = "User ID is null")
    private int userId;

    @Column(name = "H1_FONT")
    private String h1Font;

    @Column(name = "H1_SIZE")
    private int h1Size;

    @Column(name = "H1_POSITION")
    private String h1Position;

    @Column(name = "H2_FONT")
    private String h2Font;

    @Column(name = "H2_SIZE")
    private int h2Size;

    @Column(name = "H2_POSITION")
    private String h2Position;

    @Column(name = "I_FONT")
    private String itemFont;

    @Column(name = "I_SIZE")
    private int itemSize;

    @Column(name = "I_TYPOGRAPHY")
    private String itemTypography;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "LOGO")
    private byte[] companyLogo;

    @Column(name = "LOGO_POSITION")
    private String logoPosition;

    @Column(name = "LOGO_SIZE")
    private int[] logoSize;


    public PDFTemplate(String h1Font, int h1Size, String h1Position, String h2Font, int h2Size, String h2Position, String itemFont, int itemSize, String itemTypography, String currency, int userId , byte[] companyLogo , String logoPosition , int[] logoSize) {
        super();
        this.h1Font = h1Font;
        this.h1Size = h1Size;
        this.h1Position = h1Position;
        this.h2Font = h2Font;
        this.h2Size = h2Size;
        this.h2Position = h2Position;
        this.itemFont = itemFont;
        this.itemSize = itemSize;
        this.itemTypography = itemTypography;
        this.currency = currency;
        this.userId = userId;
        this.companyLogo = companyLogo;
        this.logoPosition = logoPosition;
        this.logoSize = logoSize;
    }

}
