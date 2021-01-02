package cn.ncu.edu.be.security.exception

import org.springframework.security.core.AuthenticationException

/**
 * Password Wrong Authentication Exception.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 * @see AuthenticationException
 */
class PasswordWrongAuthenticationException : AuthenticationException {

    constructor(msg: String, cause: Throwable) : super(msg, cause)

    constructor(msg: String) : super(msg)

}
