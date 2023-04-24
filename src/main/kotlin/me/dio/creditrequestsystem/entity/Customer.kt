package me.dio.creditrequestsystem.entity

data class Customer(
        val ir: Long? = null,
        var firstName: String = "",
        var lastName: String = "",
        var cep: String = "",
        var email: String = "",
        var password: String = "",
        var address: Address = Address(),
        var credits: List<Credit> = mutableListOf()
)
