package one.valuelogic.demo.config

import one.valuelogic.demo.config.aws.AwsSecretKeysService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Profile("beanstalk")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("one.valuelogic.demo.repository")
class DatabaseConfig(private val awsSecretKeysService: AwsSecretKeysService) {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(DatabaseConfig::class.java)
    }

    @Value("\${vl.datasource.secret}")
    lateinit var secretName: String

    @Value("\${spring.datasource.url}")
    lateinit var url: String

    @Bean
    fun dataSource(): DataSource {
        LOGGER.info("Configuring datasource for beanstalk profile")
        val password = awsSecretKeysService.getSecretString(secretName, "password")
            ?: throw RuntimeException("DB password key $secretName cannot be fetched from AWS Secrets Manager")

        val username = awsSecretKeysService.getSecretString(secretName, "username")
            ?: throw RuntimeException("DB master username key $secretName cannot be fetched from AWS Secrets Manager")
        return DataSourceBuilder.create()
            .username(username)
            .url(url)
            .password(password).build()
    }
}
