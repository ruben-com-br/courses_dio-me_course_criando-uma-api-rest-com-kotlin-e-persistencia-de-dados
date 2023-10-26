package br.com.ruben.creditapplicationsystem.service.impl

import br.com.ruben.creditapplicationsystem.entity.Customer
import br.com.ruben.creditapplicationsystem.exception.BusinessException
import br.com.ruben.creditapplicationsystem.repository.CustomerRepository
import br.com.ruben.creditapplicationsystem.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) : ICustomerService {
    override fun save(customer: Customer): Customer =
        this.customerRepository.save(customer)

    override fun findById(id: Long): Customer =
        this.customerRepository.findById(id).orElseThrow {
            throw BusinessException("id $id not found")
        }

    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }

}