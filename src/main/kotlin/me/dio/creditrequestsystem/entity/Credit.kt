package me.dio.creditrequestsystem.entity

import jakarta.persistence.*
import me.dio.creditrequestsystem.enummeration.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "CREDIT")
data class Credit(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false)
        val id: Long? = null,

        @Column(nullable = false, unique = true)
        val creditCode: UUID = UUID.randomUUID(),

        @Column(nullable = false)
        val creditValue: BigDecimal = BigDecimal.ZERO,

        @Column(nullable = false)
        val dayFirstInstallment: LocalDate,

        @Column(nullable = false)
        val numberOfInstallment: Int = 0,

        @Enumerated
        val status: Status = Status.InProgress,

        @ManyToOne
        var customer: Customer? = null
)
