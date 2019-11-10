package com.laidongs.sba.gateway.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link PaymentRecordSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class PaymentRecordSearchRepositoryMockConfiguration {

    @MockBean
    private PaymentRecordSearchRepository mockPaymentRecordSearchRepository;

}
