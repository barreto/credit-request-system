package me.dio.creditrequestsystem.dto

import me.dio.creditrequestsystem.entity.Credit
import me.dio.creditrequestsystem.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
        val creditValue: BigDecimal,
        val dayFirstOfInstallment: LocalDate,
        val numberOfInstallments: Int,
        val customerId: Long
) {
    fun toEntity() = Credit(
            creditValue = this.creditValue,
            dayFirstInstallment = this.dayFirstOfInstallment,
            numberOfInstallments = this.numberOfInstallments,
            customer = Customer(id = customerId)
    )
}
