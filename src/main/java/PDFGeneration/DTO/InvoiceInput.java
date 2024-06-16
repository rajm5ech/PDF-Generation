package PDFGeneration.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class InvoiceInput {
    @NotNull
    @NotEmpty
    public String userId;
    @NotNull
    @NotEmpty
    public String supplierName;
    @NotNull
    @NotEmpty
    public String supplierAddress;
    @NotNull
    @NotEmpty
    @Size(max = 15)
    public String supplierGSTIN;
    @NotNull
    @NotEmpty
    public String clientName;
    @NotNull
    public String clientAddress;
    @Size(max = 15)
    public String clientGSTIN;
    public String addressOfDelivery;
    public String[] stateNameAndCode;
    @NotNull
    @Size(max = 16)
    public String taxInvoiceNumber;
    public String hsnCode;
    @NotNull
    @NotEmpty
    public List<HashMap<String, String>> item;
    @NotNull
    @NotEmpty
    public String totalAmount;
    @NotNull
    @NotEmpty
    public byte[] digitalSignature;


}
