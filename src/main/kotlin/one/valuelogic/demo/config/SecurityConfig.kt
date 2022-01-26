package one.valuelogic.demo.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.concurrent.TimeUnit

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    @Value("\${vl.cors.allowed-origins}")
    lateinit var allowedOrigins: List<String>

    override fun configure(http: HttpSecurity) {
        http
            .exceptionHandling()
            .and()
            .cors()
            .and()
            .csrf()
            .disable()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = allowedOrigins
        configuration.addAllowedHeader(CorsConfiguration.ALL)
        configuration.addAllowedMethod(HttpMethod.HEAD)
        configuration.addAllowedMethod(HttpMethod.GET)
        configuration.addAllowedMethod(HttpMethod.POST)
        configuration.addAllowedMethod(HttpMethod.PUT)
        configuration.addAllowedMethod(HttpMethod.PATCH)
        configuration.addAllowedMethod(HttpMethod.DELETE)
        configuration.addAllowedMethod(HttpMethod.OPTIONS)
        configuration.allowCredentials = true
        configuration.maxAge = TimeUnit.DAYS.toSeconds(1)
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
