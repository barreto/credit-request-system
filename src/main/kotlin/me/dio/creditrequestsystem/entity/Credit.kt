package me.dio.creditrequestsystem.entity

import me.dio.creditrequestsystem.enummeration.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

data class Credit(
        val id: Long? = null,
        val creditCode: UUID = UUID.randomUUID(),
        val creditValue: BigDecimal = BigDecimal.ZERO,
        val dayFirstInstallment: LocalDate,
        val numberOfInstallment: Int = 0,
        val status: Status = Status.InProgress,
        val customer: Customer? = null
)
