package org.octopus.dashboard.repository;

import org.octopus.dashboard.entity.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResumeDao extends PagingAndSortingRepository<Resume, Long>, JpaSpecificationExecutor<Resume> {

	Page<Resume> findByJobId(Long id, Pageable pageRequest);

	@Modifying
	@Query("delete from Resume resume where resume.job.id=?1")
	void deleteByJobId(Long id);
}
