package one.valuelogic.demo.config.aws

import com.amazonaws.services.secretsmanager.AWSSecretsManager
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class AwsSecretKeysService(private val awsSecretsManagerClient: AWSSecretsManager) {
    fun getSecretString(secretKey: String?, property: String?): String? {
        try {
            val secretValue = awsSecretsManagerClient.getSecretValue(GetSecretValueRequest().withSecretId(secretKey))
            val secretString = secretValue.secretString
            return ObjectMapper().readTree(secretString)[property].textValue()
        } catch (ex: IOException) {
            LOGGER.error("AWS Secret parsing error: {} property not found in secret", property, ex)
        }
        return null
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(AwsSecretKeysService::class.java)
    }
}
