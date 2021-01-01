package cn.ncu.edu.be.user.data

import cn.ncu.edu.be.util.ModifyTimeLogEntity
import java.sql.Timestamp

/**
 * Entity User.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 * @see ModifyTimeLogEntity
 */
data class User(
        val id: Long, var disable: Boolean,
        var username: String, var password: String,
        override val createTime: Timestamp, override val updateTime: Timestamp
) : ModifyTimeLogEntity(createTime, updateTime)
