package me.dio.creditrequestsystem.exception

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails> {
        val httpStatus = HttpStatus.BAD_REQUEST
        val detailsErrors: MutableMap<String, String?> = mutableMapOf()

        ex.allErrors.forEach {
            val field = (it as FieldError).field

            detailsErrors[field] = it.defaultMessage
        }

        return ResponseEntity
                .status(httpStatus)
                .body(ExceptionDetails(
                        title = "${httpStatus.name}! Consult the documentation.",
                        timestamp = LocalDateTime.now(),
                        status = httpStatus.value(),
                        exception = ex.javaClass.simpleName,
                        details = detailsErrors
                ))
    }

    @ExceptionHandler(DataAccessException::class)
    fun handleDataAccessException(ex: DataAccessException): ResponseEntity<ExceptionDetails> {
        val httpStatus = HttpStatus.CONFLICT

        return ResponseEntity
                .status(httpStatus)
                .body(ExceptionDetails(
                        title = "${httpStatus.name}! Consult the documentation.",
                        timestamp = LocalDateTime.now(),
                        status = httpStatus.value(),
                        exception = ex.javaClass.simpleName,
                        details = mutableMapOf(
                                ex.cause?.javaClass?.simpleName.toString() to ex.message?.substringBefore(";")
                        )
                ))
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException): ResponseEntity<ExceptionDetails> {
        val httpStatus = HttpStatus.BAD_REQUEST

        return ResponseEntity
                .status(httpStatus)
                .body(ExceptionDetails(
                        title = "${httpStatus.name}! Consult the documentation.",
                        timestamp = LocalDateTime.now(),
                        status = httpStatus.value(),
                        exception = ex.javaClass.simpleName,
                        details = mutableMapOf(
                                "description" to ex.message?.substringBefore(";")
                        )
                ))
    }

}