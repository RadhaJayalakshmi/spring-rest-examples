package springsecurity.sample.radha.basic.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "springsecurity.sample.radha.basic")
public class ApplicationConfiguration {
}
