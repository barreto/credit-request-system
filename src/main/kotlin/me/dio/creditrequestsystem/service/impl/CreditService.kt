package me.dio.creditrequestsystem.service.impl

import me.dio.creditrequestsystem.entity.Credit
import me.dio.creditrequestsystem.repository.CreditRepository
import me.dio.creditrequestsystem.service.ICreditService
import java.lang.RuntimeException
import java.util.*

class CreditService(
        private val creditRepository: CreditRepository,
        private val customerService: CustomerService
) : ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }

        return creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long) = creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit = creditRepository.findByCreditCode(creditCode)
                ?: throw RuntimeException("CreditCode $creditCode not found.")

        return if (credit.customer?.id == customerId) credit else throw RuntimeException("Contact admin.")

    }
}