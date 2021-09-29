//package com.hugo.bookapi.config;
//
//import com.hugo.bookapi.request.JwtAuthenticationEntryPoint;
//import com.hugo.bookapi.service.AccountServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    @Autowired
//    private AccountServiceImpl accountServiceImpl;
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService( accountServiceImpl);
////    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        // We don't need CSRF for this example
//        httpSecurity.csrf().disable()
//                // dont authenticate this particular request
//                .authorizeRequests().antMatchers("/authenticate").permitAll().
//                // all other requests need to be authenticated
//                        anyRequest().authenticated().and().
//                // make sure we use stateless session; session won't be used to
//                // store user's state.
//                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        // Add a filter to validate the tokens with every request
////        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}