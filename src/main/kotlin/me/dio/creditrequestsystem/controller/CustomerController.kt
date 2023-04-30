package me.dio.creditrequestsystem.controller

import jakarta.validation.Valid
import me.dio.creditrequestsystem.dto.CustomerDto
import me.dio.creditrequestsystem.dto.CustomerUpdateDto
import me.dio.creditrequestsystem.dto.CustomerView
import me.dio.creditrequestsystem.service.impl.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerController(
        private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<String> {
        val savedCustomer = customerService.save(customerDto.toEntity())

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Customer ${savedCustomer.email} saved!")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer = customerService.findById(id)

        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) = customerService.delete(id)

    @PatchMapping
    fun updateCustomer(
            @RequestParam(value = "customerId") id: Long,
            @RequestBody @Valid customerUpdateDto: CustomerUpdateDto
    ): ResponseEntity<CustomerView> {
        val customer = customerService.findById(id)
        val customerToUpdate = customerUpdateDto.toEntity(customer)
        val updatedCustomer = customerService.save(customerToUpdate)

        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(updatedCustomer))
    }
}