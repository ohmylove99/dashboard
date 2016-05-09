package org.octopus.dashboard.repository;

import org.octopus.dashboard.entity.Job;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JobDao extends PagingAndSortingRepository<Job, Long> {

	Job findByName(String name);

}
