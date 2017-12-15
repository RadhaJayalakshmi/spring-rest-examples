package springsecurity.sample.radha.basic;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springsecurity.sample.radha.basic.service.UserService;

@Configuration
public class TestContext {

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }
}
