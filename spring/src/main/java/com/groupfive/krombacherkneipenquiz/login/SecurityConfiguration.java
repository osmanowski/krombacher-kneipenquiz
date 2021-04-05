package com.groupfive.krombacherkneipenquiz.login;

//import net.bytebuddy.build.Plugin;
import com.groupfive.krombacherkneipenquiz.models.Spieler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select benutzername,pw,enable from user where benutzername = ?")
                .authoritiesByUsernameQuery("select  benutzername,role from user where benutzername = ?");


    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*
        http.authorizeRequests()
                .antMatchers("/testad").hasRole("Admin")
                .antMatchers("/testkn","/api/fragen").hasAnyRole("Kneipenbesitzer","Admin")
                .antMatchers("/testsp").hasAnyRole("Spieler","Admin","Kneipenbesitzer")
                .antMatchers("/testga").permitAll()
                .and().formLogin();*/

        // No Form Login
        http.csrf().
                disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers("/api/**").permitAll()
                /*
                .antMatchers("/testad","/api/addkneipe","/api/getallkneipen","/api/deletekneipe/**",
                        "/api/addkneipetobesitzer/**","/api/allbesitzer","/api/besitzer/**")
                    .hasRole("Admin")
                .antMatchers("/testkn")
                    .hasRole("Kneipenbesitzer")
                .antMatchers("/api/addFrage","/api/deletefrage/**","/api/addPaket",
                        "/api/getRandomFragen/**","/api/allFragenPakete","/api/addfragetopaket/**","/api/getkneipe/**","/api/addQuizrunde")
                    .hasAnyRole("Kneipenbesitzer","Admin")
                .antMatchers("/testsp")
                    .hasRole("Spieler")
                .antMatchers("/api/fragen","/api/frage/**","/api/getpaketfuerquiz/**","/api/getfragenpaket/**",
                        "/api/getQuizRunde/**","/api/gethighscoreofround/{id}","/api/getspielerliste/{quizid}","/api/addspielertoquiz/**",
                        "/api/addtouserscore/**","/api/ip/**","/api/allspieler")
                    .hasAnyRole("Kneipenbesitzer","Admin", "Spieler")
                .antMatchers("/testga","/api/signupplayer","/api/getuser/**","/api/regus","/api/signupbesitzer","/api/allspieler").permitAll()

                 */
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }




    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    public String passwordhashen(String passw) {
        /*
            test
         */
        String hashedPassword = "";
        int i = 0;

        while (i < 10) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            hashedPassword = passwordEncoder.encode(passw);
            i++;
        }

        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        Boolean check = false;
        check = passwordEncoder2.upgradeEncoding(hashedPassword);

        if (check){
            while (passwordEncoder2.upgradeEncoding(hashedPassword)== true) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                hashedPassword = passwordEncoder.encode(passw);
                check = passwordEncoder2.upgradeEncoding(hashedPassword);
            }
        }
        return hashedPassword ;
    }




}
