package com.mpk.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.mpk.security.CsrfHeaderFilter;
import com.mpk.security.CustomAccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	private CustomAccessDeniedHandler customAccessDeniedHandler = new CustomAccessDeniedHandler();
	
	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring()
	    	.antMatchers("/resources/**","/js/**","/views/**");
	    }
	  
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/", "/homepage","/principal","/registration/**","/map/busstop","/busline/**","/timetable/**").permitAll()
            .antMatchers("/busstop/**","/user/**/","/bus/**","/driver/**","/admin/**","/route/**","/user/","/workshedule/**").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
            .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
            .and()
            .formLogin()
            	.loginPage("/homepage").permitAll()
            	.loginProcessingUrl("/authenticate")
            	.usernameParameter("login")
            	.passwordParameter("password")
    		.and()
    		.csrf()
			.disable();
	//.csrfTokenRepository(csrfTokenRepository());
}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and();
        auth
        	.jdbcAuthentication()
        		.dataSource(dataSource)
        		.usersByUsernameQuery("select username,password, active from user_account where username=?")
        		.authoritiesByUsernameQuery("select username, role from user_account where username=?");
    }

    
    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}