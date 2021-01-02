package cn.ncu.edu.be.security.provider

import cn.ncu.edu.be.security.exception.PasswordWrongAuthenticationException
import cn.ncu.edu.be.security.exception.UserNotFoundAuthenticationException
import cn.ncu.edu.be.exception.UserNotFoundException
import cn.ncu.edu.be.security.authentication.UserAuthenticationToken
import cn.ncu.edu.be.security.authentication.UsernamePasswordAuthenticationToken
import cn.ncu.edu.be.user.UserService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.util.Assert

/**
 * Dao Authentication Provider.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 * @see AuthenticationProvider
 */
class DaoAuthenticationProvider : AuthenticationProvider {

    private val passwordEncoder: PasswordEncoder

    private val userService: UserService

    constructor(userService: UserService) {
        this.userService = userService
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    constructor(userService: UserService, passwordEncoder: PasswordEncoder) {
        this.userService = userService
        this.passwordEncoder = passwordEncoder
    }

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken::class.java, authentication)

        val auth = authentication as UsernamePasswordAuthenticationToken
        val username: String = auth.principal
        val user = try {
            userService.loadByUsername(username)
        } catch (ex: UserNotFoundException) {
            throw UserNotFoundAuthenticationException(ex)
        } catch (ex: InternalAuthenticationServiceException) {
            throw ex
        } catch (ex: Exception) {
            throw InternalAuthenticationServiceException(ex.message, ex)
        }

        if (!passwordEncoder.matches(auth.credentials, user.password)) {
            throw PasswordWrongAuthenticationException("Bad credentials")
        }

        return UserAuthenticationToken(user)
    }

    override fun supports(authentication: Class<*>): Boolean = UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)

}
