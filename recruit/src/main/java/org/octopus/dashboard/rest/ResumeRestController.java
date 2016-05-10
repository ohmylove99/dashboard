package org.octopus.dashboard.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Validator;

import org.octopus.dashboard.entity.Resume;
import org.octopus.dashboard.service.resume.ResumeService;
import org.octopus.dashboard.shared.beanvalidator.BeanValidators;
import org.octopus.dashboard.shared.constants.MediaTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/v1/resume")
public class ResumeRestController {

	private static Logger logger = LoggerFactory.getLogger(ResumeRestController.class);

	@Autowired
	private ResumeService resumeService;

	@Autowired
	private Validator validator;

	@RequestMapping(method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public List<Resume> list() {
		return resumeService.getAllResume();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Resume get(@PathVariable("id") Long id) {
		Resume resume = resumeService.getResume(id);
		if (resume == null) {
			String message = "not found (id:" + id + ")";
			logger.warn(message);
			throw new RestException(HttpStatus.NOT_FOUND, message);
		}
		return resume;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST, consumes = MediaTypes.JSON)
	public ResponseEntity<?> create(@RequestBody Resume resume, UriComponentsBuilder uriBuilder) {

		BeanValidators.validateWithException(validator, resume);

		resumeService.saveResume(resume);

		Long id = resume.getId();
		URI uri = uriBuilder.path("/api/v1/resume/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaTypes.JSON)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Resume resume) {

		BeanValidators.validateWithException(validator, resume);

		resumeService.saveResume(resume);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		resumeService.deleteResume(id);
	}
}
