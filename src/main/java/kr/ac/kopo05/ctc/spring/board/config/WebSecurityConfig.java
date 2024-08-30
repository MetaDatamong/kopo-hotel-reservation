package kr.ac.kopo05.ctc.spring.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests((authorizeRequests) -> {
					// user + a 의 요청이 들어오면 로그인 되어있는지 확인.
					authorizeRequests.requestMatchers("/user/**").authenticated();
					
					// manager + a 의 요청이 들어오면 해당 url access
					authorizeRequests.requestMatchers("/manager/**")
					.hasAnyRole("ADMIN", "MANAGER");
					
					authorizeRequests.requestMatchers("/admin/**")
					.hasRole("ADMIN");
					
					authorizeRequests.anyRequest().permitAll();
				})
				
				.formLogin((formLogin) -> {
					formLogin.loginPage("/login");
				})
				
				.logout((logout) -> 
				logout.logoutUrl("/logout")
				)
				
				.build();
	}
}