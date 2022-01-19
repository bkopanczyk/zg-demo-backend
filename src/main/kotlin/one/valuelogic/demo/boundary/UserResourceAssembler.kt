package one.valuelogic.demo.boundary

import one.valuelogic.demo.entity.User
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.stereotype.Component

@Component
class UserResourceAssembler :
    RepresentationModelAssemblerSupport<User, UserResource>(UserController::class.java, UserResource::class.java) {

    override fun toModel(entity: User): UserResource {
        return UserResource(firstName = entity.firstName, lastName = entity.lastName, email = entity.email)
    }
}
