package net.cabinssolitude.cabins.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    static final String CLIEN_ID = "devglan-client";
    static final String CLIENT_SECRET = "devglan-secret";
    static final String GRANT_TYPE_PASSWORD = "password";
    static final String AUTHORIZATION_CODE = "authorization_code";
    static final String REFRESH_TOKEN = "refresh_token";
    static final String IMPLICIT = "implicit";
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("as466gf");
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
        /*
         TokenStore или же  IdentifierStore где хранятся идентификаторы, которые предоставляет наш сервер аутентификации, поэтому, когда сервер ресурсов (хранилище) запрашивает кредит на кредитной карте, он может ответить на него. В этом случае мы используем  InMemoryTokenStore класс, который будет хранить идентификаторы в памяти. В реальном приложении мы могли бы использоватьJdbcTokenStore сохранить их в базе данных, чтобы в случае отказа приложения клиентам не приходилось обновлять свои кредитные карты.*/
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        /*Здесь мы уточняем учетные данные банка, я говорю администратора аутентификаций и предлагаемых услуг. Да, во множественном числе, потому что для доступа к банку у нас должны быть имя пользователя и пароль для каждой из предлагаемых услуг. Это очень важная концепция: пользователь и пароль принадлежат банку, а не клиенту. Для каждой услуги, предлагаемой банком, будет проведена единая аутентификация, хотя она может быть одинаковой для разных услуг.*/
        configurer
                .inMemory() //указывает, что мы собираемся хранить сервисы в памяти. В «реальном» приложении мы сохраняем его в базе данных, на сервере LDAP и т. Д.
                .withClient(CLIEN_ID)//это пользователь, с которым мы будем идентифицировать себя...
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )//мы указываем службы, которые настраиваются для определенного пользователя, для CLIEN_ID. В нашем примере мы будем использовать только службу паролей .
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)//это сфера обслуживания
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
                refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter());
        /*здесь мы определяем, какой контроллер аутентификации и хранилище идентификаторов должны использовать конечные точки. Уточните, что конечными точками являются URL-адреса, по которым мы будем разговаривать с нашим «банком», чтобы запросить карты.*/
    }
}