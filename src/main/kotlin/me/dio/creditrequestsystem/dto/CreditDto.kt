package me.dio.creditrequestsystem.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import me.dio.creditrequestsystem.entity.Credit
import me.dio.creditrequestsystem.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
        @field:NotNull(message = "Income cannot be null.")
        val creditValue: BigDecimal,

        @field:Future(message = "Day first of installment must be in the future.")
        val dayFirstOfInstallment: LocalDate,

        @field:NotNull
        @Min(1, message = "Minimum number of installments must be one (in cash).")
        @Max(48, message = "Maximum number of installments is 48.")
        val numberOfInstallments: Int,

        @field:NotNull(message = "Invalid input")
        val customerId: Long
) {
    fun toEntity() = Credit(
            creditValue = this.creditValue,
            dayFirstInstallment = this.dayFirstOfInstallment,
            numberOfInstallments = this.numberOfInstallments,
            customer = Customer(id = customerId)
    )
}
