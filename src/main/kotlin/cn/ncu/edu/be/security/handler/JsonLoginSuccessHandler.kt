package cn.ncu.edu.be.security.handler

import cn.ncu.edu.be.user.data.User
import cn.ncu.edu.be.util.TokenUtil
import com.alibaba.fastjson.JSONObject
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Json Login Success Handler.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 * @see AuthenticationSuccessHandler
 */
class JsonLoginSuccessHandler : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        val json = JSONObject(mapOf<String, Any>(
                "status" to 1, "validity" to TokenUtil.TOKEN_VALIDITY,
                "token" to TokenUtil.generateToken(authentication.details as User)
        ))

        response.contentType = "application/json; charset=utf-8"
        response.writer.write(json.toJSONString())
    }
}