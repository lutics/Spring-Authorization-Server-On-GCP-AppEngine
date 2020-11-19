package com.example.demo.backend

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.reactive.function.client.WebClient

@Controller
class AuthorizationController(
        private val webClient: WebClient
) {

    @GetMapping(value = ["/authorize"], params = ["grant_type=authorization_code"])
    fun authorizeWithAuthorizationCode(
            @RegisteredOAuth2AuthorizedClient("test-client-authorization-code") authorizedClient: OAuth2AuthorizedClient,
            model: Model
    ): String {
        val messages = webClient
                .get()
                .uri("http://localhost:8090/messages")
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(Array<String>::class.java)
                .block()

        model.addAttribute("messages", messages)

        return "index"
    }

    @GetMapping(value = ["/authorize"], params = ["grant_type=client_credentials"])
    fun authorizeWithClientCredentials(
            @RegisteredOAuth2AuthorizedClient("test-client-client-credentials") authorizedClient: OAuth2AuthorizedClient,
            model: Model
    ): String {
        val messages = webClient
                .get()
                .uri("http://localhost:8090/messages")
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(Array<String>::class.java)
                .block()

        model.addAttribute("messages", messages)

        return "index"
    }
}