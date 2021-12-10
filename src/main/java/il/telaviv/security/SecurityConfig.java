package il.telaviv.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/getdata").authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();

        http.csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("dima").password(passwordEncoder.encode("777")).roles("ADMIN")
                .and()
                .withUser("mars").password(passwordEncoder.encode("888")).roles("USER")
                .and()
                .passwordEncoder(passwordEncoder);
    }



//    @Override
//    public void addCorsMappings(CorsRegistry registry) {          // if we want to allow some/all origins
//        registry
//                .addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("*");
//    }


}
