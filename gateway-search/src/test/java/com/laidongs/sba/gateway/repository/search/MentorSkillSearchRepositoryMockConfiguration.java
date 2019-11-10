package com.laidongs.sba.gateway.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link MentorSkillSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class MentorSkillSearchRepositoryMockConfiguration {

    @MockBean
    private MentorSkillSearchRepository mockMentorSkillSearchRepository;

}
