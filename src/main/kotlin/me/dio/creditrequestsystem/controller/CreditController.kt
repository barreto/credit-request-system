package me.dio.creditrequestsystem.controller

import me.dio.creditrequestsystem.dto.CreditDto
import me.dio.creditrequestsystem.dto.CreditView
import me.dio.creditrequestsystem.dto.CreditViewListItem
import me.dio.creditrequestsystem.service.impl.CreditService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/credits")
class CreditController(
        private val creditService: CreditService
) {

    @PostMapping
    fun saveCredit(@RequestBody creditDto: CreditDto): String {
        val credit = creditService.save(creditDto.toEntity())
        return "Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!"
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): List<CreditViewListItem> {
        return this.creditService.findAllByCustomer(customerId).map { CreditViewListItem(it) }.toList()
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(
            @RequestParam(value = "customerId") customerId: Long,
            @PathVariable creditCode: UUID
    ): CreditView {
        val credit = creditService.findByCreditCode(customerId, creditCode)
        return  CreditView(credit)
    }


}