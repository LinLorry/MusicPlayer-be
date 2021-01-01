package cn.ncu.edu.be.util

import java.io.Serializable
import java.sql.Timestamp

/**
 * Modify Time Log Entity Abstract Class
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 */
open class ModifyTimeLogEntity(open val createTime: Timestamp, open val updateTime: Timestamp) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}
