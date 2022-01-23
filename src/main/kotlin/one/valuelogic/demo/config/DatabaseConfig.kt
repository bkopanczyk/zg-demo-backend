package one.valuelogic.demo.config

import one.valuelogic.demo.config.aws.AwsSecretKeysService
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("one.valuelogic.demo.repository")
class DatabaseConfig(private val awsSecretKeysService: AwsSecretKeysService) {
    @Value("\${vl.datasource.secret}")
    lateinit var secretName: String

    @Value("\${spring.datasource.url}")
    lateinit var url: String

    @Bean
    fun dataSource(): DataSource {
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
