package br.com.ruben.creditapplicationsystem.exception

data class BusinessException(override val message: String?): RuntimeException(message) {
}