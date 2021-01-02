package cn.ncu.edu.be.security.exception

import cn.ncu.edu.be.exception.UserNotFoundException
import org.springframework.security.core.AuthenticationException

/**
 * User Not Found Authentication Exception.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 * @see AuthenticationException
 */
class UserNotFoundAuthenticationException : AuthenticationException {

    constructor(msg: String, cause: Throwable) : super(msg, cause)

    constructor(msg: String) : super(msg)

    constructor(userNotFoundException: UserNotFoundException) : super(userNotFoundException.message)

}
