package com.laidongs.sba.gateway.repository.search;
import com.laidongs.sba.gateway.domain.MyCalendar;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link MyCalendar} entity.
 */
public interface MyCalendarSearchRepository extends ElasticsearchRepository<MyCalendar, Long> {
}
