package one.valuelogic.demo.boundary

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/"])
class HealthCheckController {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun sayOK(): ResponseEntity<Void> {
        return ResponseEntity.ok().build()
    }
}
