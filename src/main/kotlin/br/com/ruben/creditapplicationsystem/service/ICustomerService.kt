package br.com.ruben.creditapplicationsystem.service

import br.com.ruben.creditapplicationsystem.entity.Customer


interface ICustomerService {

    fun save(customer: Customer): Customer

    fun findById(id: Long): Customer

    fun delete(id: Long)

}