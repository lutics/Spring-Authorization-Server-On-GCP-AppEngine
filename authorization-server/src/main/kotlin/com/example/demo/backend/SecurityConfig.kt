package com.example.demo.backend

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.keys.StaticKeyGeneratingKeyManager
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import java.util.*

@EnableWebSecurity
@Import(OAuth2AuthorizationServerConfiguration::class)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        super.configure(http)

        http.csrf().disable()
    }

    @Bean
    fun registeredClientRepository(): RegisteredClientRepository = InMemoryRegisteredClientRepository(
            RegisteredClient.withId(UUID.randomUUID().toString())
                    .clientId("id")
                    .clientSecret("secret")
                    .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                    .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                    .redirectUri("http://localhost:8080/oauth2/redirect")
                    .redirectUri("android-app://com.example.demo.android/oauth2/redirect")
                    .tokenSettings { it.enableRefreshTokens(true) }
                    .scope("read")
                    .scope("write")
                    .build()
    )

    @Bean
    fun keyManager() = StaticKeyGeneratingKeyManager()

    @Bean
    override fun userDetailsService(): UserDetailsManager = InMemoryUserDetailsManager(
            User.withDefaultPasswordEncoder()
                    .username("tester")
                    .password("tester")
                    .roles("USER")
                    .build()
    )
}