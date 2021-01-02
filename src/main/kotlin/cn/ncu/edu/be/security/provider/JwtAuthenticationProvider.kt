package cn.ncu.edu.be.security.provider

import cn.ncu.edu.be.exception.UserNotFoundException
import cn.ncu.edu.be.security.authentication.JwtAuthenticationToken
import cn.ncu.edu.be.security.authentication.UserAuthenticationToken
import cn.ncu.edu.be.security.exception.UserNotFoundAuthenticationException
import cn.ncu.edu.be.user.UserService
import cn.ncu.edu.be.util.TokenUtil
import io.jsonwebtoken.JwtException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.util.Assert

/**
 * Jwt Authentication Provider.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 * @see AuthenticationProvider
 */
class JwtAuthenticationProvider(private val userService: UserService) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication?): Authentication {
        Assert.isInstanceOf(JwtAuthenticationToken::class.java, authentication)

        val auth = authentication as JwtAuthenticationToken

        val user = try {
            userService.loadById(TokenUtil.getIdFromToken(auth.credentials))
        } catch (ex: UserNotFoundException) {
            throw UserNotFoundAuthenticationException(ex)
        } catch (ex: JwtException) {
            throw BadCredentialsException(ex.message ?: "JwtAuthenticationProvider.authenticate: Jwt Exception")
        } catch (ex: Exception) {
            throw InternalAuthenticationServiceException(ex.message, ex)
        }

        return UserAuthenticationToken(user)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return JwtAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
