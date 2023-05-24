package nl.jdriven.shop

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.client.RestTemplate


@Configuration
class Config {

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http.csrf().disable()
            .authorizeHttpRequests { authz ->
                authz.requestMatchers("/products/search/**").permitAll()
                    .anyRequest().authenticated()
            }
            .httpBasic()
        return http.build()
    }
}