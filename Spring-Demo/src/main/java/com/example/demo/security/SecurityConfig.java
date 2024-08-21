package com.example.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.userDetailsImp.UserDetailsServiceIml;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class SecurityConfig {

//	@Autowired
//	private UserDetailsServiceIml userDetailsServiceImpl;

	@Value("${manger.base.url}")
	private String baseUrl;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//		http.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests(requests -> requests.requestMatchers("/registerUser", "/login").permitAll())
//				.authorizeHttpRequests(requests -> requests.requestMatchers("/admin/**").hasRole("ADMIN"))
//				.authorizeHttpRequests(
//						requests -> requests.requestMatchers("/user/**").hasRole("USER").anyRequest().authenticated()
//						);

		http.csrf(csrf -> csrf.disable());
		http.authorizeHttpRequests(
				req -> req.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN").requestMatchers("/admin/**")
						.hasRole("ADMIN").requestMatchers("/manager/**").hasRole("MANAGER").requestMatchers("/ceo/**")
						.hasRole("CEO").requestMatchers("/public/**").permitAll().anyRequest().authenticated());

//		http.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
//		http.formLogin(formlogin -> formlogin.successHandler(successHandler));
		http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());// for postman api

//        http
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/user/**").hasRole("USER")
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().authenticated())
//                .formLogin(login -> login
//                        .loginPage("/login") // Custom login page
//                        .permitAll())
//                .logout(logout -> logout
//                        .permitAll());

//		http.csrf().disable().authorizeHttpRequests().requestMatchers("/public/**").permitAll()
//		.and().authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN")
//		.requestMatchers("/user/**").hasRole("USER")
//		.anyRequest().authenticated();
//		
		return http.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsServiceIml();
	}

	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService());

		return provider;
	}

	@Bean
	WebClient webClient() {
		return WebClient.builder().baseUrl(baseUrl).build();
	}

}
