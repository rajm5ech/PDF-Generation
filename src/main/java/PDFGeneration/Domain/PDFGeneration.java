package PDFGeneration.Domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PDF_GENERATION")
@NoArgsConstructor
@Getter
@Setter
public class PDFGeneration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PDF_ID")
	private Integer id;

	@Column(name = "CUST_ID")
	@NotNull(message = "Customer ID is null")
	@Pattern(regexp = "^[1-9]$", message = "Invalid Customer ID")
	private int custId;

	@Column(name = "CUST_NAME")
	@NotNull(message = "Customer Name is invalid")
	private String customerName;

	@Column(name = "INVOICE_PDF")
	@Size(min = 100, max = 1000, message = "File size  should be bellow 100KB and above 1MB")
	private byte[] invoicePDF;

	@Column(name = "CREATED_DATE")
	@NotNull(message = "Created Date is invalid")
	private Date createdDate;

	@Column(name = "USER_ID")
	@NotNull(message = "User ID is null")
	@Pattern(regexp = "^[1-9]$", message = "Invalid User ID")
	private int userId;
	
	public PDFGeneration(@NotNull(message = "Invalid Customer ID") int custId,
						 @NotNull(message = "Customer Name is invalid") String customerName,
			@Size(min = 100, max = 1000, message = "File size  should be bellow 100KB and above 1MB") byte[] invoicePDF,
			@NotNull(message = "Created Date is invalid") Date createdDate,
			@NotNull(message = "User ID is null") @Pattern(regexp = "^[1-9]$", message = "Invalid User ID") int userId) {
		this.custId = custId;
		this.customerName = customerName;
		this.invoicePDF = invoicePDF;
		this.createdDate = createdDate;
		this.userId = userId;
	}

}
