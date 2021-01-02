package cn.ncu.edu.be.security.authentication

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils

/**
 * Username And Password Authentication Token.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 * @see Authentication
 */
class UsernamePasswordAuthenticationToken(
        private val username: String, private val password: String
) : Authentication {

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun getName(): String = username

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = AuthorityUtils.NO_AUTHORITIES

    override fun getCredentials(): String = password

    override fun getDetails(): Any? = null

    override fun getPrincipal(): String = username

    override fun isAuthenticated(): Boolean = false

    override fun setAuthenticated(isAuthenticated: Boolean) { }

}
