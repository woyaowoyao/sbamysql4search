package com.laidongs.sba.gateway.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link MentorSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class MentorSearchRepositoryMockConfiguration {

    @MockBean
    private MentorSearchRepository mockMentorSearchRepository;

}
