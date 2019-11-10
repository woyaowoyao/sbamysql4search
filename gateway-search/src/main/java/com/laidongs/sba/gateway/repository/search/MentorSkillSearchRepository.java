package com.laidongs.sba.gateway.repository.search;
import com.laidongs.sba.gateway.domain.MentorSkill;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MentorSkill} entity.
 */
public interface MentorSkillSearchRepository extends ElasticsearchRepository<MentorSkill, Long> {
}
