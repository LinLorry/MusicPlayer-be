package cn.ncu.edu.be.config

import cn.ncu.edu.be.security.filter.JwtAuthenticationFilter
import cn.ncu.edu.be.security.provider.DaoAuthenticationProvider
import cn.ncu.edu.be.security.handler.JsonLoginFailureHandler
import cn.ncu.edu.be.security.handler.JsonLoginSuccessHandler
import cn.ncu.edu.be.security.filter.UsernamePasswordAuthenticationFilter
import cn.ncu.edu.be.security.provider.JwtAuthenticationProvider
import cn.ncu.edu.be.user.UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.logout.LogoutFilter

/**
 * Web Security Configuration.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 */
@EnableWebSecurity
class WebSecurityConfiguration {

    /**
     * Json Login Configurer Adapter
     * match url pattern "/api/token".
     */
    @Configuration
    @Order(1)
    class JsonLoginConfigurerAdapter(
            @Value("\${secret.salt}") private val salt: String,
            private val userService: UserService
    ) : WebSecurityConfigurerAdapter() {

        private val authFilter = UsernamePasswordAuthenticationFilter(salt).apply {
            setAuthenticationFailureHandler(JsonLoginFailureHandler())
            setAuthenticationSuccessHandler(JsonLoginSuccessHandler())
        }

        override fun configure(auth: AuthenticationManagerBuilder) {
            auth.authenticationProvider(DaoAuthenticationProvider(userService))
        }

        override fun configure(http: HttpSecurity) {
            authFilter.setAuthenticationManager(authenticationManager())
            http.csrf().disable()
                    .sessionManagement().disable()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .antMatcher("/api/token").authorizeRequests {
                        it.antMatchers(HttpMethod.POST).permitAll()
                        it.anyRequest().authenticated()
                    }
                    .addFilterAfter(authFilter, LogoutFilter::class.java)
        }
    }

    /**
     * Jwt Login Configurer Adapter
     */
    @Configuration
    class JwtConfigurerAdapter(
            @Value("\${secret.authenticationName}") authenticationName: String,
            private val userService: UserService
    ) : WebSecurityConfigurerAdapter() {

        private val authFilter = JwtAuthenticationFilter(authenticationName)

        override fun configure(auth: AuthenticationManagerBuilder) {
            auth.authenticationProvider(JwtAuthenticationProvider(userService))
        }

        override fun configure(http: HttpSecurity) {
            http.csrf().disable()
                    .sessionManagement().disable()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .authorizeRequests {
                        it.antMatchers("/api/user/registry").permitAll()
                        it.anyRequest().authenticated()
                    }
                    .addFilterAfter(authFilter, LogoutFilter::class.java)
        }
    }
}
