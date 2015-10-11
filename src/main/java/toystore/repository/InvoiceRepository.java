package toystore.repository;


import org.springframework.data.repository.CrudRepository;

import toystore.domain.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice,Long>{
}
