package br.com.ruben.creditapplicationsystem.service

import br.com.ruben.creditapplicationsystem.entity.Customer
import br.com.ruben.creditapplicationsystem.exception.BusinessException
import br.com.ruben.creditapplicationsystem.repository.CustomerRepository
import br.com.ruben.creditapplicationsystem.service.impl.CustomerService
import br.com.ruben.creditapplicationsystem.utils.buildCustomer
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK lateinit var customerRepository: CustomerRepository
    @InjectMockKs lateinit var customerService: CustomerService

    @Test
    fun `should save customer`(){
        val fakeCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer

        val actual: Customer = customerService.save(fakeCustomer)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1){ customerRepository.save(fakeCustomer) }

    }

    @Test
    fun `should find customer by Id`(){

        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)

        val actual: Customer = customerService.findById(fakeId)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1){ customerRepository.findById(fakeId)}

    }

    @Test
    fun `should not find customer by invalid id and throw BusinessExcepition`(){
        val fakeId: Long = Random().nextLong()
        every { customerRepository.findById(fakeId) } returns Optional.empty()

        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("id $fakeId not found")
        verify(exactly = 1) { customerRepository.findById(fakeId) }

    }


    @Test
    fun `should delete a Custumer`(){
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) }returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) }just runs

        customerService.delete(fakeId)

        verify (exactly = 1){ customerRepository.findById(fakeId) }
        verify (exactly = 1){ customerRepository.delete(fakeCustomer) }
    }


}