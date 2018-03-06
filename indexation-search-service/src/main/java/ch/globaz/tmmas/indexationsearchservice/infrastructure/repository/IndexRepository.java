package ch.globaz.tmmas.indexationsearchservice.infrastructure.repository;


import ch.globaz.tmmas.indexationsearchservice.infrastructure.repository.models.RenteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexRepository extends ElasticsearchRepository<RenteDto, String> {

    Page<RenteDto> findByNumero(String no, Pageable pageable);

    List<RenteDto> findByrequerantId(String title);

}
