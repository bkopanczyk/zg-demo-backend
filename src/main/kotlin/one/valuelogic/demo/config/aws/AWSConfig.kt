package one.valuelogic.demo.config.aws

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.services.secretsmanager.AWSSecretsManager
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AWSConfig {
    @Value("\${cloud.aws.region.static}")
    lateinit var awsRegion: String

    @Bean
    fun awsCredentialsProvider(): AWSCredentialsProvider {
        return DefaultAWSCredentialsProviderChain()
    }

    @Bean
    fun awsSecretsManager(awsCredentialsProvider: AWSCredentialsProvider): AWSSecretsManager {
        return AWSSecretsManagerClientBuilder.standard()
            .withRegion(awsRegion)
            .withCredentials(awsCredentialsProvider)
            .build()
    }
}
