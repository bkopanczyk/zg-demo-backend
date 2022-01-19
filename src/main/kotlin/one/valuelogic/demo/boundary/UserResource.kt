package one.valuelogic.demo.boundary

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation

@Relation(value = "user", collectionRelation = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserResource(val firstName: String, val lastName: String, val email: String) :
    RepresentationModel<UserResource>()
