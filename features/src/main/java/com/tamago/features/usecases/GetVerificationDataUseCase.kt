package com.tamago.features.usecases

import coil.network.HttpException
import com.tamago.email_data.EmailRepository
import com.tamago.features.response.VerifierResponse
import com.tamago.kickboxioapi.model.VerifierDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Igor Khoroshun on 28.06.2024.
 */

class GetVerificationDataUseCase @Inject constructor(
    private val repository: EmailRepository,
) {
    fun getVerifierInfo(email: String): Flow<VerifierResponse<VerifierDto>> {
        return flow {
            try {
                emit(VerifierResponse.Loading())
                val verifier = repository.getVerificationData(email)
                emit(VerifierResponse.Success(data = verifier))
            } catch (e: HttpException) {
                emit(
                    VerifierResponse.Error(
                        if (e.response.message.startsWith('5')) "Server is not available"
                        else "Invalid request"
                    )
                )
            } catch (e: IOException) {
                emit(VerifierResponse.Error("I/O error: ${e.message}"))
            } catch (e: Exception) {
                emit(VerifierResponse.Error("Unknown error: ${e.message}"))
            }
        }
    }
}

