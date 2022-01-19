package one.valuelogic.demo.boundary

import one.valuelogic.demo.control.UserService
import one.valuelogic.demo.entity.User
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.data.web.SortDefault
import org.springframework.hateoas.PagedModel
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1"])
class UserController(private val userService: UserService, private val userResourceAssembler: UserResourceAssembler) {

    @GetMapping(path = ["users"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUsers(
        @SortDefault(value = ["lastName"]) pageable: Pageable,
        pagedResourcesAssembler: PagedResourcesAssembler<User>
    ): PagedModel<UserResource>? {
        return userService.getUsers(pageable)?.let { pagedResourcesAssembler.toModel(it, userResourceAssembler) }
    }
}
