package com.laidongs.sba.gateway.repository.search;
import com.laidongs.sba.gateway.domain.Mentor;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Mentor} entity.
 */
public interface MentorSearchRepository extends ElasticsearchRepository<Mentor, Long> {
}
