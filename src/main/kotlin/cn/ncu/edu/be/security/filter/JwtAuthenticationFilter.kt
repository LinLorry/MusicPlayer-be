package cn.ncu.edu.be.security.filter

import cn.ncu.edu.be.security.authentication.JwtAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Jwt Authentication Filter.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 * @see OncePerRequestFilter
 */
class JwtAuthenticationFilter(private val AuthenticationName: String) : OncePerRequestFilter() {

    /**
     * Get token from http header "Authorization" and set JwtAuthenticationToken
     * on authentication
     */
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val requestTokenHeader = request.getHeader("Authorization")

        if (requestTokenHeader != null && requestTokenHeader.startsWith("$AuthenticationName ")) {
            val token = requestTokenHeader.substring(AuthenticationName.length + 1)
            SecurityContextHolder.getContext().authentication = JwtAuthenticationToken(token)
        }
        filterChain.doFilter(request, response)
    }

}
