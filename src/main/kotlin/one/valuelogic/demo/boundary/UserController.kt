package one.valuelogic.demo.boundary

import one.valuelogic.demo.control.UserService
import one.valuelogic.demo.entity.User
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.data.web.SortDefault
import org.springframework.hateoas.PagedModel
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(path = ["/api/v1"])
class UserController(private val userService: UserService, private val userResourceAssembler: UserResourceAssembler) {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(UserController::class.java)
    }

    @GetMapping(path = ["users"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUsers(
        @SortDefault(value = ["lastName"]) pageable: Pageable,
        pagedResourcesAssembler: PagedResourcesAssembler<User>
    ): PagedModel<UserResource>? {
        LOGGER.info("Get all users call requested")
        return userService.getUsers(pageable)?.let { pagedResourcesAssembler.toModel(it, userResourceAssembler) }
    }

    @PostMapping(path = ["users"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody userResource: UserResource): ResponseEntity<UserResource> {
        val resource = userResourceAssembler.toModel(
            userService.createUser(
                userResource.email,
                userResource.firstName,
                userResource.lastName
            )
        )
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .pathSegment(resource.email)
                .build()
                .toUri()
        ).body(resource)
    }
}
