package cn.ncu.edu.be.security.authentication

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils

/**
 * Jwt Authentication Token.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 * @see Authentication
 */
class JwtAuthenticationToken(private val token: String) : Authentication {

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun getName(): String = token

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = AuthorityUtils.NO_AUTHORITIES

    override fun getCredentials(): String = token

    override fun getDetails(): Any? = null

    override fun getPrincipal(): String = token

    override fun isAuthenticated(): Boolean = false

    override fun setAuthenticated(isAuthenticated: Boolean) { }

}
