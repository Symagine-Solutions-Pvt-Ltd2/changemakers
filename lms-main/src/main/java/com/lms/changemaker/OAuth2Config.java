//package com.lms.changemaker;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//@Configuration
//public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Bean
//    public JwtAccessTokenConverter tokenConverter() {
//        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
//        tokenConverter.setSigningKey(PRIVATE_KEY);
//        tokenConverter.setVerifierKey(PUBLIC_KEY);
//        return tokenConverter;
//    }
//
//    @Bean
//    public JwtTokenStore tokenStore() {
//        return new JwtTokenStore(tokenConverter());
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer) throws Exception {
//        endpointsConfigurer.authenticationManager(authenticationManager)
//                .tokenStore(tokenStore())
//                .accessTokenConverter(tokenConverter());
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer securityConfigurer) throws Exception {
//        securityConfigurer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient(CLIENT_ID)
//                .secret(CLIENT_SECRET)
//                .scopes("read","write")
//                .authorizedGrantTypes("password","refresh_token")
//                .accessTokenValiditySeconds(20000)
//                .refreshTokenValiditySeconds(20000);
//    }
//}
