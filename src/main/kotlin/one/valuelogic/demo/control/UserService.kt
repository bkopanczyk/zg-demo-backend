package one.valuelogic.demo.control

import one.valuelogic.demo.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserService {
    fun getUsers(page: Pageable): Page<User>? {
        return PageImpl(
            listOf(
                User(
                    id = 1,
                    email = "test@valuelogic.one",
                    firstName = "Gordon",
                    lastName = "Shumway"
                )
            )
        )
    }
}
