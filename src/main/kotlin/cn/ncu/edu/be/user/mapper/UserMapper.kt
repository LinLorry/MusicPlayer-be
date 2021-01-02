package cn.ncu.edu.be.user.mapper

import cn.ncu.edu.be.user.data.User
import org.apache.ibatis.annotations.*
import org.springframework.stereotype.Component

/**
 * User Mapper.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 * @see Mapper
 */
@Mapper
@Component
interface UserMapper {

    @Select("SELECT id, disable, username, password, create_time, update_time " +
            "FROM user " +
            "WHERE id = #{id}")
    fun findUserById(@Param("id") id: Long): User?

    @Select("SELECT id, disable, username, password, create_time, update_time " +
            "FROM user " +
            "WHERE username= #{username}")
    fun findUserByUsername(@Param("username") username: String): User?

    @Insert("INSERT INTO user " +
            "(username, password) " +
            "VALUES " +
            "(#{user.username}, #{user.password})")
    @Options(keyProperty = "user.id", useGeneratedKeys = true, keyColumn = "id")
    fun addUserByMap(@Param("user") user: Map<String, Any>): Int

    @Update("UPDATE user " +
            "SET password=#{password}, update_time=CURRENT_TIMESTAMP " +
            "where id=#{id}")
    fun updatePassword(@Param("id") id: Long, @Param("password") password: String): Int

}