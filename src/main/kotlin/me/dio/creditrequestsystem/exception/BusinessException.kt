package me.dio.creditrequestsystem.exception

import java.lang.RuntimeException

class BusinessException(override val message: String?) : RuntimeException(message)