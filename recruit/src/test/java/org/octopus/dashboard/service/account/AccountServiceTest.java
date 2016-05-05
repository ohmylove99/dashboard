package org.octopus.dashboard.service.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.octopus.dashboard.data.UserData;
import org.octopus.dashboard.entity.User;
import org.octopus.dashboard.repository.TaskDao;
import org.octopus.dashboard.repository.UserDao;
import org.octopus.dashboard.service.account.ShiroDbRealm.ShiroUser;
import org.octopus.dashboard.shared.exception.ServiceException;
import org.octopus.dashboard.shared.utils.clock.MockClock;
import org.octopus.dashboard.test.security.shiro.ShiroTestUtils;

public class AccountServiceTest {

	@InjectMocks
	private AccountService accountService;

	@Mock
	private UserDao mockUserDao;

	@Mock
	private TaskDao mockTaskDao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ShiroTestUtils.mockSubject(new ShiroUser(3L, "foo", "Foo"));
	}

	@Test
	public void registerUser() {
		User user = UserData.randomNewUser();
		Date currentTime = new Date();
		accountService.setClock(new MockClock(currentTime));

		accountService.registerUser(user);

		assertThat(user.getRoles()).isEqualTo("user");
		assertThat(user.getRegisterDate()).isEqualTo(currentTime);
		assertThat(user.getPassword()).isNotNull();
		assertThat(user.getSalt()).isNotNull();
	}

	@Test
	public void updateUser() {

		User user = UserData.randomNewUser();
		accountService.updateUser(user);
		assertThat(user.getSalt()).isNotNull();

		User user2 = UserData.randomNewUser();
		user2.setPlainPassword(null);
		accountService.updateUser(user2);
		assertThat(user2.getSalt()).isNull();
	}

	@Test
	public void deleteUser() {

		accountService.deleteUser(2L);
		Mockito.verify(mockUserDao).delete(2L);

		try {
			accountService.deleteUser(1L);
			failBecauseExceptionWasNotThrown(ServiceException.class);
		} catch (ServiceException e) {
			// expected exception
		}
		Mockito.verify(mockUserDao, Mockito.never()).delete(1L);
	}

}
