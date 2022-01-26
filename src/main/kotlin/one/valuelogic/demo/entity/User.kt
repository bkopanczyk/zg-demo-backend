package one.valuelogic.demo.entity

import javax.persistence.*

@Entity
@Table(name = "vl_user")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long?,

    @Column(name = "email", nullable = false)
    val email: String?,

    @Column(name = "first_name", nullable = false)
    val firstName: String?,

    @Column(name = "last_name", nullable = false)
    val lastName: String?
) {
    constructor() : this(0, "", "", "")
}
