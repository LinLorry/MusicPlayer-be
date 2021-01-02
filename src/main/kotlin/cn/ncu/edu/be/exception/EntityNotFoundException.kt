package cn.ncu.edu.be.exception

/**
 * Entity Not Found Exception.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 */
open class EntityNotFoundException : Exception {

    constructor() : super()

    constructor(message: String) : super(message)

}
