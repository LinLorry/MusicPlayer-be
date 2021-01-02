package cn.ncu.edu.be.security.filter

import cn.ncu.edu.be.security.authentication.UsernamePasswordAuthenticationToken
import com.alibaba.fastjson.JSONObject
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.util.StreamUtils
import org.springframework.util.StringUtils
import java.nio.charset.Charset
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Username And Passowrd Authentication Filter.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 * @see AbstractAuthenticationProcessingFilter
 */
class UsernamePasswordAuthenticationFilter(private val salt: String) :
        AbstractAuthenticationProcessingFilter(AntPathRequestMatcher("/api/token", "POST")) {

    /**
     * Get username and password from request body and set UsernamePasswordAuthenticationToken
     */
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val body = StreamUtils.copyToString(request.inputStream, Charset.defaultCharset())

        val username: String
        val password: String

        if (StringUtils.hasText(body)) {
            val json = JSONObject.parseObject(body)
            username = json.getString("username") ?: ""
            password = salt + (json.getString("password") ?: "") + salt
        } else {
            username = ""
            password = ""
        }

        val authRequest = UsernamePasswordAuthenticationToken(username, password)

        return this.authenticationManager.authenticate(authRequest)
    }
}
