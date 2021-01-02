package cn.ncu.edu.be.security.handler

import cn.ncu.edu.be.security.exception.PasswordWrongAuthenticationException
import cn.ncu.edu.be.security.exception.UserNotFoundAuthenticationException
import com.alibaba.fastjson.JSONObject
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Json Login Failure Handler.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 * @see AuthenticationFailureHandler
 */
class JsonLoginFailureHandler : AuthenticationFailureHandler {

    override fun onAuthenticationFailure(request: HttpServletRequest?, response: HttpServletResponse, exception: AuthenticationException?) {
        val json = JSONObject()
        json["status"] = 0

        when (exception) {
            is BadCredentialsException -> json["message"] = "Bad credentials."
            is UserNotFoundAuthenticationException -> json["message"] = "Username not exist."
            is PasswordWrongAuthenticationException -> json["message"] = "Password wrong."
            else -> json["message"] = "Login failure."
        }

        response.contentType = "application/json; charset=utf-8"
        response.writer.write(json.toJSONString())
        response.status = HttpStatus.UNAUTHORIZED.value()
    }
}