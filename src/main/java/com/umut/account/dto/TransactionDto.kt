package com.umut.account.dto

import com.umut.account.model.TransactionType
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionDto(
        val id: String?,
        val transactionType: TransactionType?,
        val amount: BigDecimal?,
        val transactionDate: LocalDateTime?,
) {

}
