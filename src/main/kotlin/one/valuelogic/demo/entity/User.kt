package one.valuelogic.demo.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "vl_user")
data class User(
    @Id
    @Column(name = "id", nullable = false)
    val id: Long = 0,

    @Column(name = "email", nullable = false)
    val email: String = "",

    @Column(name = "first_name", nullable = false)
    val firstName: String = "",

    @Column(name = "last_name", nullable = false)
    val lastName: String = ""
)
