package aaarrgh.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

/**
 * alternativa de testing:
 * http:blog.zenika.com/index.php?post/2013/01/15/spring-mvc-test-framework
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class LoginControllerTests {

	@Test
	public void queSePuedaHacerLogout() throws Exception {

		LoginController controller = new LoginController();

		ModelAndView modelAndView = controller.logout();

		Assert.assertEquals("../../index", modelAndView.getViewName());
		Assert.assertEquals("Logout exitoso.",
				modelAndView.getModel().get("message"));

	}

	@Configuration
	public static class TestConfiguration {

		@Bean
		public LoginController loginController() {
			return new LoginController();
		}

	}
}
