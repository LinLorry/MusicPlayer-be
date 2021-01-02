package cn.ncu.edu.be.util

import cn.ncu.edu.be.user.data.User
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import java.util.*

/**
 * Token Util
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 */
object TokenUtil {

    const val TOKEN_VALIDITY = 5 * 60 * 60 * 1000.toLong()

    private val key = Keys.secretKeyFor(SignatureAlgorithm.HS512)

    /**
     * Generate Token by user
     * @param user the user.
     * @return token string.
     */
    @Throws(JwtException::class)
    fun generateToken(user: User): String {
        return Jwts.builder()
                .setSubject(user.id.toString())
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(key)
                .compact()
    }

    /**
     * Get user id From token
     * @param token the token string.
     * @return the user id.
     * @throws ExpiredJwtException if token expired.
     * @throws SecurityException if token invalid.
     */
    @Throws(ExpiredJwtException::class, SecurityException::class)
    fun getIdFromToken(token: String): Long {
        return getAllClaimsFromToken(token).subject.toLong()
    }

    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
    }

}
