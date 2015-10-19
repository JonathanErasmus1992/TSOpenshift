package toystore.service;
import toystore.domain.Invoice;

public interface GetInvoiceDetails {
    public Invoice getInvoice(Long invoiceID);
}
