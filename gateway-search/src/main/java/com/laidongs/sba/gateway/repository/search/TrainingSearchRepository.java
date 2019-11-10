package com.laidongs.sba.gateway.repository.search;
import com.laidongs.sba.gateway.domain.Training;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Training} entity.
 */
public interface TrainingSearchRepository extends ElasticsearchRepository<Training, Long> {
}
