package net.cabinssolitude.cabins.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                anonymous().disable()
                .authorizeRequests()
                .antMatchers("/users/**").access("hasRole('ADMIN')")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());

//        http
//                .authorizeRequests (). antMatchers ("/ oauth / token", "/ oauth / authorize **", "/ publishes"). permitAll ();
//        // .anyRequest (). authenticated ();
//        http.requestMatchers (). antMatchers ("/ private") // Deny access to "/ private"
//                .and (). authorizeRequests ()
//                .antMatchers ("/ private"). access ("hasRole ('USER')")
//                .and (). requestMatchers (). antMatchers ("/ admin") // Deny access to "/ admin"
//                .and (). authorizeRequests ()
//                .antMatchers ("/ admin"). access ("hasRole ('ADMIN')");
//    }
//    http.authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**", "/publica").permitAll()
//          разрешить все запросы к "/ oauth / token", "/ oauth / authorize **", "/ publish" без какой-либо проверки. "
//    anyRequest().authenticated()
//          Эта строка комментируется. В противном случае все ресурсы были бы доступны только в том случае, если пользователь прошел валидацию.
//    requestMatchers().antMatchers("/privada")
//          Запретить доступ к URL "/ частный"
//    authorizeRequests().antMatchers("/privada").access("hasRole('USER')")
//          разрешить доступ к «/ private», если проверенный пользователь имеет роль «USER»
//    requestMatchers().antMatchers("/admin")
//          Запретить доступ к URL "/ admin"
//    authorizeRequests().antMatchers("/admin").access("hasRole('ADMIN')")
//          разрешить доступ к «/ admin», если проверенный пользователь имеет роль «ADMIN»
    }
}
