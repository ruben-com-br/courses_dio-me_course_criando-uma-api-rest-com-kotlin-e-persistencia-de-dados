package br.com.ruben.creditapplicationsystem.service

import br.com.ruben.creditapplicationsystem.entity.Credit
import br.com.ruben.creditapplicationsystem.entity.Customer
import br.com.ruben.creditapplicationsystem.exception.BusinessException
import br.com.ruben.creditapplicationsystem.repository.CreditRepository
import br.com.ruben.creditapplicationsystem.service.impl.CreditService
import br.com.ruben.creditapplicationsystem.service.impl.CustomerService
import br.com.ruben.creditapplicationsystem.utils.buildCredit
import br.com.ruben.creditapplicationsystem.utils.buildCustomer
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*


@ExtendWith(MockKExtension::class)
class CreditServiceTest {

    @MockK lateinit var customerService: CustomerService
    @MockK lateinit var creditRepository: CreditRepository
    @InjectMockKs lateinit var creditService: CreditService


    @Test
    fun `should save credit`(){
        val fakeCustumer: Customer = buildCustomer()
        val fakeCredit: Credit = buildCredit()
        every { customerService.findById(1L) } returns fakeCustumer
        every { creditRepository.save(fakeCredit) }returns fakeCredit

        val actual: Credit = creditService.save(fakeCredit)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isEqualTo(fakeCredit)
        verify (exactly = 1){ customerService.findById(1L) }
        verify (exactly = 1){ creditRepository.save(fakeCredit)}

    }

    @Test
    fun `should find all credits by custumer`(){
        val fakeId: Long = Random().nextLong()
        val fakeCredit: List<Credit> = listOf(buildCredit())
        every { creditRepository.findAllByCustomer(fakeId) } returns fakeCredit

        val actual: List<Credit> = creditService.findAllByCustomer(fakeId)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isEqualTo(fakeCredit)
        verify(exactly = 1) { creditRepository.findAllByCustomer(fakeId) }

    }

    @Test
    fun `should find credits by customer id and credit code`(){
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        val fakeCreditCode: UUID = UUID.randomUUID()
        val fakeCredit: Credit = buildCredit(customer = fakeCustomer)
        every { creditRepository.findByCreditCode(fakeCreditCode) } returns fakeCredit

        val actual: Credit = creditService.findByCreditCode(fakeId, fakeCreditCode)

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isEqualTo(fakeCredit)

        verify(exactly = 1) { creditRepository.findByCreditCode(fakeCreditCode) }

    }

    @Test
    fun `should throw BusinessException when credit code not found`(){
        val fakeId: Long = Random().nextLong()
        val fakeCreditCode: UUID = UUID.randomUUID()
        every { creditRepository.findByCreditCode(fakeCreditCode) }returns null

        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { creditService.findByCreditCode(fakeId, fakeCreditCode) }
            .withMessage("Creditcode $fakeCreditCode not found")

        verify (exactly = 1){ creditRepository.findByCreditCode(fakeCreditCode) }
    }

    @Test
    fun `shold thorw IllegalArgumentException when custumer id doesn't match`(){
        val fakeId: Long = 1
        val fakeCustomer: Customer = buildCustomer(id = 2L)
        val fakeCreditCode: UUID = UUID.randomUUID()
        val fakeCredit: Credit = buildCredit(customer = fakeCustomer)

        every { creditRepository.findByCreditCode(fakeCreditCode) } returns fakeCredit

        Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { creditService.findByCreditCode(fakeId, fakeCreditCode) }
            .withMessage("Contact Admin")

    }

}