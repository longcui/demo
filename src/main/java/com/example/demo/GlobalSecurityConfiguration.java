package com.example.demo;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class GlobalSecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter {

   /**
    * Registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        // // TODO Auto-generated method stub
        // return null;

        //For SessionAuthenctionStartegy we should use RegisterSessionAuthenticationStrategy because we are using public Keycloak client for authentication.
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    /**
     * Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate [javax.servlet.Filter]: Factory method 'springSecurityFilterChain' threw exception; nested exception is java.lang.IllegalStateException: 
permitAll only works with either HttpSecurity.authorizeRequests() or HttpSecurity.authorizeHttpRequests(). Please define one or the other but not both.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
       super.configure(http);
       http
               .authorizeRequests()
            //    .antMatchers("/**").hasRole("default-roles-dev-idc")
            //  .anyRequest().permitAll();
               .antMatchers("/**").authenticated()
             .anyRequest().permitAll();
    }


}