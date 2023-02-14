package com.umut.account.dto

import com.umut.account.model.Transaction
import java.math.BigDecimal
import java.time.LocalDateTime

data class CustomerAccountDto(
        val id: String,
        var balance: BigDecimal? = BigDecimal.ZERO,
        val transactions: Set<TransactionDto>?,
        val creationDate: LocalDateTime
) {

}
