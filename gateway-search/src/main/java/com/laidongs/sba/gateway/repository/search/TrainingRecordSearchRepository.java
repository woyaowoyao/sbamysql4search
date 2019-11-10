package com.laidongs.sba.gateway.repository.search;
import com.laidongs.sba.gateway.domain.TrainingRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link TrainingRecord} entity.
 */
public interface TrainingRecordSearchRepository extends ElasticsearchRepository<TrainingRecord, Long> {
}
