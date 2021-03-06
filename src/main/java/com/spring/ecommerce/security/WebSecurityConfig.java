package com.spring.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.ecommerce.security.jwt.JwtAuthEntryPoint;
import com.spring.ecommerce.security.jwt.JwtAuthTokenFilter;
import com.spring.ecommerce.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,proxyTargetClass=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired private UserDetailsServiceImpl userDetailsService;
	@Autowired private JwtAuthEntryPoint unauthorizedHandler;
    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf()
        		.disable()
        		.authorizeRequests()
        		.antMatchers("/api/auth/**").permitAll()
        		.antMatchers("/api/image/**").permitAll()
        		.antMatchers("/api/category/getAll").permitAll()
        		.antMatchers("/api/product/getAll").permitAll()
        		.antMatchers("/api/subCategory/getAll").permitAll()
        		.antMatchers("/api/brand/getAll").permitAll()
        		.antMatchers("/api/subCategory/getAll/{id}").permitAll()
        		.antMatchers("/api/product/getAllBySubCategory/{id}").permitAll()
        		.antMatchers("/api/product/getAllByfilteredName/{id}").permitAll()
        		.antMatchers("/api/product/getAllVariation/{id}").permitAll()
           		.antMatchers("/api/product/getAllBySubCategory/{id}").permitAll()
           		.antMatchers("/api/product/getAllByBrand/{id}").permitAll()
        		.antMatchers("/api/product/{id}").permitAll()
           		.antMatchers("/api/recommendation/itemBased/{id}").permitAll()
          // 		.antMatchers("/api/recommendation/userBased/{id}").permitAll()
        	//	.antMatchers("/api/recommendation/**").permitAll()
//        		.antMatchers("/api/image/**").permitAll()
//        		.antMatchers("/api/image/**").permitAll()
//        		.antMatchers("/api/image/**").permitAll()
//        		.antMatchers("/api/image/**").permitAll()
//        		.antMatchers("/api/image/**").permitAll()
//        		.antMatchers("/api/image/**").permitAll()
//        		.antMatchers("/api/image/**").permitAll()
//        		.antMatchers("/api/image/**").permitAll()
//        		.antMatchers("/api/image/**").permitAll()
//        		.antMatchers("/api/image/**").permitAll()
//        		.antMatchers("/api/image/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
