package one.valuelogic.demo.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import javax.sql.DataSource

@Profile("!beanstalk")
@Configuration
class LocalDatabaseConfig {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(LocalDatabaseConfig::class.java)
    }

    @Value("\${spring.datasource.url}")
    lateinit var url: String

    @Bean
    fun dataSource(): DataSource {
        LOGGER.info("Configuring local database connection")
        return DataSourceBuilder.create()
            .username("postgres")
            .url(url).build()
    }
}
