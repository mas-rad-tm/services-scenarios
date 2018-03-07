package ch.globaz.tmmas.indexationsearchservice.infrastructure.repository;


import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.RenteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexRepository extends ElasticsearchRepository<RenteDocument, Long> {

    Page<RenteDocument> findByNumero(String no, Pageable pageable);

    List<RenteDocument> findByrequerantId(String title);

}
