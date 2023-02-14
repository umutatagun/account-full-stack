package com.umut.account.model

import javax.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Account(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String? = "",
        val balance: BigDecimal? = BigDecimal.ZERO,
        val creationDate: LocalDateTime,

        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL] )
        @JoinColumn(name = "customer_id", nullable = false)
        val customer: Customer?,

        @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        val transactions: Set<Transaction> = HashSet()

) {

        constructor(customer: Customer, balance: BigDecimal, creationDate: LocalDateTime) : this(
                id = "",
                customer = customer,
                balance = balance,
                creationDate = creationDate
        )

        override fun hashCode(): Int {
                var result = id?.hashCode() ?: 0
                result = 31 * result + (balance?.hashCode() ?: 0)
                result = 31 * result + creationDate.hashCode()
                result = 31 * result + (customer?.hashCode() ?: 0)
                return result
        }
}
