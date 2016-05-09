package org.octopus.dashboard.service.resume;

import java.util.List;
import java.util.Map;

import org.octopus.dashboard.entity.Resume;
import org.octopus.dashboard.repository.ResumeDao;
import org.octopus.dashboard.shared.persistence.DynamicSpecifications;
import org.octopus.dashboard.shared.persistence.SearchFilter;
import org.octopus.dashboard.shared.persistence.SearchFilter.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ResumeService {

	private ResumeDao resumeDao;

	public Resume getResume(Long id) {
		return resumeDao.findOne(id);
	}

	public void saveResume(Resume entity) {
		resumeDao.save(entity);
	}

	public void deleteResume(Long id) {
		resumeDao.delete(id);
	}

	public List<Resume> getAllResume() {
		return (List<Resume>) resumeDao.findAll();
	}

	public Page<Resume> getUserResume(Long userId, Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Resume> spec = buildSpecification(userId, searchParams);

		return resumeDao.findAll(spec, pageRequest);
	}

	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	private Specification<Resume> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<Resume> spec = DynamicSpecifications.bySearchFilter(filters.values(), Resume.class);
		return spec;
	}

	@Autowired
	public void setResumeDao(ResumeDao resumeDao) {
		this.resumeDao = resumeDao;
	}
}
