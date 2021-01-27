package com.marcelodeassis.thermostasis.repository;

import com.marcelodeassis.thermostasis.domain.ApiCallLog;
import org.springframework.data.repository.CrudRepository;

public interface ApiCallLogRepository extends CrudRepository<ApiCallLog, Long> {
}
