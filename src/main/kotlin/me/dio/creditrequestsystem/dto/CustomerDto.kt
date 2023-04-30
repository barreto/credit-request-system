package me.dio.creditrequestsystem.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.creditrequestsystem.entity.Address
import me.dio.creditrequestsystem.entity.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
        @field:NotEmpty(message = "First name cannot be empty.")
        val firstName: String,

        @field:NotEmpty(message = "Last name cannot be empty.")
        val lastName: String,

        @field:CPF(message = "Invalid CPF")
        val cpf: String,

        @field:NotNull(message = "Income cannot be null.")
        val income: BigDecimal,

        @field:Email(message = "Invalid e-mail")
        val email: String,

        @field:NotEmpty(message = "Password cannot be empty.")
        val password: String,

        @field:NotEmpty(message = "Zip code cannot be empty.")
        val zipCode: String,

        @field:NotEmpty(message = "Street cannot be empty.")
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