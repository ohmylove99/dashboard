package org.octopus.dashboard.recurit.shared.test.spring;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ActiveProfiles(Profiles.UNIT_TEST)
public abstract class SpringContextTestCase extends AbstractJUnit4SpringContextTests {
}
