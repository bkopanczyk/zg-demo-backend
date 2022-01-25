package one.valuelogic.demo.boundary

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/"])
class HealthCheckController {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(HealthCheckController::class.java)
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun sayOK(): ResponseEntity<Void> {
        LOGGER.info("Healthcheck call requested")
        return ResponseEntity.ok().build()
    }
}
