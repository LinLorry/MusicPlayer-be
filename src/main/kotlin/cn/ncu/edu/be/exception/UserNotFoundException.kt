package cn.ncu.edu.be.exception

/**
 * User Not Found Exception.
 * @author Lin Lorry
 * @author linlorry.p@gmail.com
 * @see EntityNotFoundException
 */
class UserNotFoundException: EntityNotFoundException {

    constructor(id: Long) : super("User\$$id not found.")

    constructor(username: String) : super("User\$$username not found")

}

