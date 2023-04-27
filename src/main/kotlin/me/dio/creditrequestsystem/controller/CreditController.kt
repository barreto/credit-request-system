package me.dio.creditrequestsystem.controller

import me.dio.creditrequestsystem.dto.CreditDto
import me.dio.creditrequestsystem.dto.CreditView
import me.dio.creditrequestsystem.dto.CreditViewListItem
import me.dio.creditrequestsystem.service.impl.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/credits")
class CreditController(
        private val creditService: CreditService
) {

    @PostMapping
    fun saveCredit(@RequestBody creditDto: CreditDto): ResponseEntity<String> {
        val credit = creditService.save(creditDto.toEntity())

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!")
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewListItem>> {
        val creditList = creditService.findAllByCustomer(customerId).map { CreditViewListItem(it) }.toList()

        return ResponseEntity.status(HttpStatus.OK).body(creditList)
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(
            @RequestParam(value = "customerId") customerId: Long,
            @PathVariable creditCode: UUID
    ): ResponseEntity<CreditView> {
        val credit = creditService.findByCreditCode(customerId, creditCode)

        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
    }

}