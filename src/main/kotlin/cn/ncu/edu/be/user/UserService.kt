package cn.ncu.edu.be.user

import cn.ncu.edu.be.exception.UserNotFoundException
import cn.ncu.edu.be.user.data.User
import cn.ncu.edu.be.user.mapper.UserMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

/**
 * User Service
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 */
@Service
class UserService(
        @Value("\${secret.salt}") private val salt: String, private val userMapper: UserMapper
) {
    private val passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    /**
     * Create User Service
     * @param username the username.
     * @param password the password.
     * @return if create user success return true, else return false.
     */
    fun create(username: String, password: String): Boolean {
        val passwordEncoded = passwordEncoder.encode(salt + password + salt)
        return userMapper.addUserByUsernameAndPassword(username, passwordEncoded) == 1
    }

    /**
     * Update User Password Service
     * @param id the user id.
     * @param password the user password.
     * @return if update password success return true, else return false.
     */
    fun updatePassword(id: Long, password: String): Boolean {
        val passwordEncoded = passwordEncoder.encode(salt + password + salt)
        return userMapper.updatePassword(id, passwordEncoded) == 1
    }

    /**
     * Load User By Id
     * @param id the user id.
     * @return the user.
     * @throws UserNotFoundException when id couldn't be found.
     */
    @Throws(UserNotFoundException::class)
    fun loadById(id: Long): User {
        return userMapper.findUserById(id) ?: throw UserNotFoundException(id)
    }

    /**
     * Load User By Id
     * @param username the username.
     * @return the user.
     * @throws UserNotFoundException when username couldn't be found.
     */
    @Throws(UserNotFoundException::class)
    fun loadByUsername(username: String): User {
        return userMapper.findUserByUsername(username)
                ?: throw UserNotFoundException(username)
    }

}
