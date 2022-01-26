package one.valuelogic.demo.control

import one.valuelogic.demo.entity.User
import one.valuelogic.demo.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(private val userRepository: UserRepository) {

    @Transactional(readOnly = true)
    fun getUsers(page: Pageable): Page<User>? {
        return userRepository.findAll(page)
    }

    fun createUser(email: String?, firstname: String?, lastName: String?): User {
        return userRepository.save(User(id = null, email = email, firstName = firstname, lastName = lastName))
    }

    @Transactional(readOnly = true)
    fun getByEmail(email: String): User? {
        return userRepository.getDistinctByEmail(email)
    }
}
