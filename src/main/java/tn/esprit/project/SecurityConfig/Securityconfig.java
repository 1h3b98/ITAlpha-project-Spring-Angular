package tn.esprit.project.SecurityConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tn.esprit.project.Filter.CustomAuthenticationFilter;
import tn.esprit.project.Filter.CustomAuthorizationFilter;
import tn.esprit.project.Service.UserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Securityconfig extends WebSecurityConfigurerAdapter {
   private final UserDetailsService userDetailsService;
   private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter=new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**","/api/token/refresh","/api/user/save"
                ,"/api/role/affectionate/{idu}/{idr}","/api/role/save","/api/role/addtouser/{username}/{RoleName}",
                "/api/addepartement","/api/affecterdpttouser/{username}/{title}","/api/delete/{id}",
                "/api/v1/registration/add","/api/v1/registration/confirm","/api/ForgetPassword/{username}","/api/resetPassword/{newpass}/{username}","/router/sendOTP","/api/getuserbyname/{email}","/api/getrolebyen/{name}","/api/completerleprofil","/api/getueserbyusername/{username}").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/user/**").hasAnyAuthority("user");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/user/save/**").hasAnyAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/users").hasAnyAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/blockedusername/{username}").hasAnyAuthority("admin");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }


}
