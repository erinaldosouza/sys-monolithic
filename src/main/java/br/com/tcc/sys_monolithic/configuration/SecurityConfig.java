package br.com.tcc.sys_monolithic.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.tcc.sys_monolithic.security.JWTAuthenticationFilter;
import br.com.tcc.sys_monolithic.security.JWTUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
		
	private final UserDetailsService userDetailsService;
	private final JWTUtil jwtUtil;
	 
	@Autowired
	//TODO use conditional properties
	public SecurityConfig(@Qualifier("userSecurityDatabaseService") UserDetailsService userDetailsService, JWTUtil jwtUtil) {
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable(); // this use the corsConfigurationSource method configuration
		
		http.authorizeRequests()
		    .antMatchers(new String[] {"/**"})
		    .permitAll()
		    .anyRequest().authenticated();
		
		http.addFilter(new JWTAuthenticationFilter(this.jwtUtil, authenticationManager()));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		super.configure(http);
	}
	
	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source  = new UrlBasedCorsConfigurationSource();
		
		//TODO To figure out how to allow just the API GATEWAY to request services instead of /**
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println(Thread.currentThread().getName());
		auth.userDetailsService(userDetailsService).passwordEncoder( bCryptPasswordEncoder());

		// auth.userDetailsService(userDetailsService).passwordEncoder( NoOpPasswordEncoder.getInstance());
	}
	
	@Bean
	protected BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}