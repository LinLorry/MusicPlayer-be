package cn.ncu.edu.be.security.authentication

import cn.ncu.edu.be.user.data.User
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils

/**
 * User Authentication Token.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 * @see Authentication
 */
class UserAuthenticationToken(private val user: User) : Authentication {

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun getName(): String = user.username

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = AuthorityUtils.NO_AUTHORITIES

    override fun getCredentials(): String = user.password

    override fun getDetails(): User = user

    override fun getPrincipal(): Long = user.id

    override fun isAuthenticated(): Boolean = !user.disable

    override fun setAuthenticated(isAuthenticated: Boolean) { }

}
