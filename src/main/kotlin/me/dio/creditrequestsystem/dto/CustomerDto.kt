package me.dio.creditrequestsystem.dto

import me.dio.creditrequestsystem.entity.Address
import me.dio.creditrequestsystem.entity.Customer
import java.math.BigDecimal

data class CustomerDto(
        val firstName: String,
        val lastName: String,
        val cpf: String,
        val income: BigDecimal,
        val email: String,
        val password: String,
        val zipCode: String,
        val street: String,
) {

    fun toEntity() = Customer(
            firstName = this.firstName,
            lastName = this.lastName,
            cpf = this.cpf,
            income = this.income,
            email = this.email,
            password = this.password,
            address = Address(this.zipCode, this.street),
    )

}