package me.dio.creditrequestsystem.service.impl

import me.dio.creditrequestsystem.entity.Credit
import me.dio.creditrequestsystem.exception.BusinessException
import me.dio.creditrequestsystem.repository.CreditRepository
import me.dio.creditrequestsystem.service.ICreditService
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class CreditService(
        private val creditRepository: CreditRepository,
        private val customerService: CustomerService
) : ICreditService {

    private fun validateMaxDayFirstInstallment(date: LocalDate) {
        val maxFutureDate = LocalDate.now().plusMonths(3)
        val isNotValidDate = date.isAfter(maxFutureDate)

        if (isNotValidDate) throw BusinessException("Max day of first installment is 3 months in the future from today.")
    }

    override fun save(credit: Credit): Credit {
        validateMaxDayFirstInstallment(credit.dayFirstInstallment)

        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }

        return creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long) = creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit = creditRepository.findByCreditCode(creditCode)
                ?: throw BusinessException("CreditCode $creditCode not found.")

        return if (credit.customer?.id == customerId) credit else throw BusinessException("Contact admin.")

    }
}