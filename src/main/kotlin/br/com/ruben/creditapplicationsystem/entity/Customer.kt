package br.com.ruben.creditapplicationsystem.entity

data class Customer (
    val firstName: String = "",
    val lastName: String = "",
    val cpf: String,
    val email: String = "",
    val password: String = "",
    val address: Address = Address(),
    val credits: List<Credit> = mutableListOf(),
    val id: Long? = null
)