package br.com.serratec.trabfinal_api.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.serratec.trabfinal_api.security.JwtAuthenticationFilter;
import br.com.serratec.trabfinal_api.security.JwtAuthorizationFilter;
import br.com.serratec.trabfinal_api.security.JwtUtil;

//FALTA CONFIGURAR CORRETAMENTE!!
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(requests -> requests
						.requestMatchers(HttpMethod.GET, "/clientes/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/clientes/**").permitAll()
						.requestMatchers(HttpMethod.PUT, "/clientes/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/pecas/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/pecas/**").permitAll()
						.requestMatchers(HttpMethod.PUT, "/pecas/**").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/pecas/**").permitAll()
						
						.requestMatchers(HttpMethod.POST, "/perfis").permitAll()
						.requestMatchers(HttpMethod.POST, "/veiculos").permitAll()
						.requestMatchers(HttpMethod.GET, "/veiculos").permitAll()
						.requestMatchers("/h2-console/**").permitAll()
						.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

						.requestMatchers(HttpMethod.POST, "/usuarios").permitAll()

						.requestMatchers(HttpMethod.GET, "/usuarios").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/veiculos/*/foto").hasAnyRole("ADMIN") // ,"teste1", "teste2"
						.requestMatchers(HttpMethod.POST, "/veiculos").hasAnyRole("ADMIN")
						.anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.headers(headers -> headers.frameOptions(FrameOptionsConfig::disable));

		http.addFilterBefore(new JwtAuthenticationFilter(
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil),
				UsernamePasswordAuthenticationFilter.class);

		http.addFilterBefore(new JwtAuthorizationFilter(
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil,
				userDetailsService),
				UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	BCryptPasswordEncoder criptografar() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:2000"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
		return source;
	}

}
