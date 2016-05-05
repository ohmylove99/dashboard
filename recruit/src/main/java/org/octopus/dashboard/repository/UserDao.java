package org.octopus.dashboard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.octopus.dashboard.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {

	User findByLoginName(String loginName);

}
