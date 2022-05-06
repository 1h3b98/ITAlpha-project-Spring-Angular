package tn.esprit.project.SecurityConfig;

import tn.esprit.project.Filter.CustomAuthenticationFilter;
import tn.esprit.project.Filter.CustomAuthorizationFilter;
import tn.esprit.project.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

//import javax.jws.soap.SOAPBinding;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
@RequiredArgsConstructor
public class Securityconfig extends WebSecurityConfigurerAdapter {

    /* private final UserDetailsServiceImpl userDetailsService;
     private final UserService userService;
     private final BCryptPasswordEncoder bCryptPasswordEncoder;
     private final AuthenticationEntryPoint authenticationEntryPoint;
     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
     }
     @Bean
     public CustomAuthorizationFilter authenticationJwtTokenFilter() {
         return new CustomAuthorizationFilter();
     }

     @Override
     protected void configure(HttpSecurity http) throws Exception {

         http.csrf().disable()
                 .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                         .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
         .authorizeRequests().antMatchers("/api/login","/api/token/refresh","/api/user/save"
                 ,"/api/role/affectionate/{idu}/{idr}","/api/role/save","/api/role/addtouser/{username}/{RoleName}",
                 "/api/add-dpt","/api/assigndepartmenttouser/{username}/{title}","/api/delete/{id}",
                 "/api/registration/add","/api/registration/confirm","/api/ForgetPassword/{username}","/api/resetPassword/{newpass}/{username}","/router/sendOTP"
                 ,"/api/getuserbyname/{email}","/api/getrolebyen/{name}","/api/completerleprofil","/api/getueserbyusername/{username}"
                 ,"/router/sendOTP","/api/addofre/{idf}","/api/addrating/{note}/{idp}/{idu}","/api/addReservation/{idu}/{idp}","/api/DeleteRating/{id}"
                 ,"/api/GetMeilleurOffre","/api/countReservztion/{titre}/{id}","/api/getbestparticpte/{titre}","/api/signin").permitAll().anyRequest().authenticated();
         http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/user/**").hasAnyAuthority("user");
         http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/user/save/**").hasAnyAuthority("admin");
         http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/users").hasAnyAuthority("admin");
         http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/blockedusername/{username}").hasAnyAuthority("admin");
         http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/registration/add").hasAnyAuthority("user");

        // http.authorizeRequests().anyRequest().authenticated();
         http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
     }
     @Bean
     @Override
     public AuthenticationManager authenticationManagerBean() throws Exception {
         return super.authenticationManagerBean();
     }

     */
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//
       /* http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**","/api/token/refresh","/api/user/save"
                ,"/api/role/affectionate/{idu}/{idr}","/api/role/save","/api/role/addtouser/{username}/{RoleName}",
                "/api/add-dpt","/api/assigndepartmenttouser/{username}/{title}","/api/delete/{id}",
                "/api/registration/add","/api/registration/confirm","/api/ForgetPassword/{username}","/api/resetPassword/{newpass}/{username}","/router/sendOTP"
                ,"/api/getuserbyname/{email}","/api/getrolebyen/{name}","/api/completerleprofil","/api/getueserbyusername/{username}"
                ,"/router/sendOTP","/api/addofre/{idf}","/api/addrating/{note}/{idp}/{idu}","/api/addReservation/{idu}/{idp}","/api/DeleteRating/{id}"
                ,"/api/GetMeilleurOffre","/api/countReservztion/{titre}/{id}","/api/getbestparticpte/{titre}","/api/GetAllpub","/api/Updatpublicite/{idpub}","/api/DeletePub/{id}").permitAll();


        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/user/**").hasAnyAuthority("user");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/user/save/**").hasAnyAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/users").hasAnyAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/blockedusername/{username}").hasAnyAuthority("admin");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/registration/add").hasAnyAuthority("user").and().rememberMe().userDetailsService(userDetailsService);

        */
        http.headers().addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin","*"));
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login", "/api/token/refresh/**","/api/registration/add","/api/completerleprofil",
                "/api/GetAllpub","/api/getAlluser","/api/registration/confirm","/api/getuser/{id}","/api/role/save","/api/addofre/{idf}",
                "/Event/add","/Event/update","/Event/delete/{event_id}","/Event/getallEvent","/Event/getOneEvent/{event_id}",
                "/Event/getUsersByEvent/{event_id}","/Action/add/{user_id}/{event_id}","/Action/accept/{action_id}/{reciever_id}/{event_id}",
                "/Action/refuse/{action_id}/{reciever_id}/{event_id}","/Action/delete/{action_id}/{event_id}/{user_id}","/Action/delete/{action_id}",
                "/Action/getallAction","/Action/getallFavEvent/{user_id}","/Action/getOneAction/{action_id}","/Action/getInvite/{reciever_id}",
                "/Action/getLike/{user_id}/{event_id}","/Action/getJoin/{user_id}/{event_id}","/Action/getFav/{user_id}/{event_id}",
                "/Action/getAllComment/{event_id}","/Action/getInvite/{reciever_id}/{event_id}").permitAll();
        // http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user").hasAnyAuthority("user");
        // http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/save/**").hasAnyAuthority("admin");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManager()));
         http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider =
//                new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(bCryptPasswordEncoder);
//        provider.setUserDetailsService(userService);
//        return provider;
}


