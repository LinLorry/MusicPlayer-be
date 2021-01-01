package cn.ncu.edu.be.config

import org.springframework.context.annotation.Configuration
import java.util.TimeZone

/**
 * Music Player Backend Base Configuration.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 */
@Configuration
class Configuration {
    init {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }
}