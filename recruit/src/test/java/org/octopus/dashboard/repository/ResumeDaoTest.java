package org.octopus.dashboard.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.octopus.dashboard.entity.Resume;
import org.octopus.dashboard.recurit.shared.test.spring.SpringTransactionalTestCase;
import org.octopus.dashboard.shared.persistence.DynamicSpecifications;
import org.octopus.dashboard.shared.persistence.SearchFilter;
import org.octopus.dashboard.shared.persistence.SearchFilter.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class ResumeDaoTest extends SpringTransactionalTestCase {

	@Autowired
	private ResumeDao resumeDao;

	@Test
	public void findResumesById() throws Exception {
		Resume recume = resumeDao.findOne(1L);
		assertThat(recume.getId()).isEqualTo(1);
	}

	@Test
	public void uploadDoc() throws Exception {
		preUploadDoc();
		postUploadDoc();
	}

	public void preUploadDoc() throws Exception {
		final String NAME = "My Dummy Resume";
		Resume resume = new Resume();
		resume.setName(NAME);
		resumeDao.save(resume);
		// Map<String, Object> searchParams = new HashMap<String, Object>();
		// searchParams.put("LIKE_name", NAME);
		// Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		filters.put("resume.name", new SearchFilter("name", Operator.EQ, NAME));
		Specification<Resume> spec = DynamicSpecifications.bySearchFilter(filters.values(), Resume.class);
		assertThat(resumeDao.findAll(spec).get(0).getName()).contains(NAME);
	}

	private void postUploadDoc() throws Exception {
		final String NAME = "My Dummy Resume";
		Resume resume = resumeDao.findOne(1L);
		assertThat(resume.getId()).isEqualTo(1);
		InputStream is = null;
		try {
			is = ResumeDaoTest.class.getResourceAsStream("/data/files/Resume.docx");
			byte[] doc = new byte[is.available()];
			is.read(doc);

			// Resume resume = resumeDao.findAll(spec).get(0);
			resume.setOriginalDoc(doc);
			resumeDao.save(resume);

			Resume resume2 = resumeDao.findOne(1L);
			assertThat(resume2.getId()).isEqualTo(1);
			resume2.getConvertedDoc();

		} catch (Exception e) {

		} finally {
			is.close();
		}
	}
}
