package com.laidongs.sba.gateway.repository.search;
import com.laidongs.sba.gateway.domain.PaymentRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PaymentRecord} entity.
 */
public interface PaymentRecordSearchRepository extends ElasticsearchRepository<PaymentRecord, Long> {
}
