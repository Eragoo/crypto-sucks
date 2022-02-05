package com.coinsucks.core.config;
//
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//@AllArgsConstructor
public class SecurityConfig {//extends WebSecurityConfigurerAdapter {

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers("/**")
//                .antMatchers("/swagger-ui.html")
//                .antMatchers("/webjars/**")
//                .antMatchers("/swagger-resources/**")
//                .antMatchers("/v2/api-docs")
//                .antMatchers("/actuator/health")
//                .antMatchers("/actuator/info")
//                .antMatchers("/build-reports/**")
//                .antMatchers("/auth")
//                .antMatchers("/actuator/*")
//                .antMatchers("/accounts/confirm")
//                .antMatchers(HttpMethod.GET, "/inspections/**/images/retrieve")
//                .antMatchers(HttpMethod.GET, "/ml-model-task-definition/**/")
//                .antMatchers(HttpMethod.GET, "/ml-model-task-definition/**/dataset")
//                .antMatchers(HttpMethod.POST, "/accounts")
//                .antMatchers(HttpMethod.GET, "/industries/default")
//                .antMatchers(HttpMethod.POST, "/industries")
//                .antMatchers(HttpMethod.PUT, "/accounts/request-password-reset")
//                .antMatchers("/gis/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(STATELESS)
//                .and()
//              //  .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//              //  .exceptionHandling().authenticationEntryPoint(entryPoint).accessDeniedHandler(problemSupport)
//              //  .and()
//                .authorizeRequests()
//                .anyRequest().authenticated();
//    }
//
//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
}
