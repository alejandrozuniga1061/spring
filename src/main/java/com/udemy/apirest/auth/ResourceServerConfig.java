package com.udemy.apirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		///api/clientes/page/** los ** cualquier página se puede mirar
			.antMatchers(HttpMethod.GET, "/api/**", "/images/**",  "/swagger-ui.*", "/webjars/springfox-swagger-ui/**", "/swagger-resources/**", "/v2/api-docs/**", "/csrf").permitAll()//Se envían todas las rutas públicas
			//.antMatchers("/api/clientes/{id}").permitAll()
			//.antMatchers("/api/facturas/**").permitAll()
			/*.antMatchers(HttpMethod.GET,"/api/clientes/{id}").hasAnyRole("USER", "ADMIN")
			.antMatchers(HttpMethod.POST,"/api/clientes/upload").hasAnyRole("USER", "ADMIN")
			.antMatchers(HttpMethod.POST,"/api/clientes").hasRole("ADMIN")
			.antMatchers("/api/clientes/**").hasRole("ADMIN") //LAs rutas que no quedaron definidas arriba se vuelve solo de uso ADMIN*/
			.anyRequest().authenticated()//Para todas las demás deben estar autenticadas
			.and().cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "*"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config); //Para todas las rutas del backend
		
		return source;
		
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>( new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);//registra el filtro como màs alto en el stack
		return bean;
	}
	
	

	//Implementa las reglas de seguridad para cada usuario
	
}
