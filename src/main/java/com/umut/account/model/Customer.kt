package com.umut.account.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
data class Customer(
       @Id
       @GeneratedValue(generator = "UUID")
       @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
       val id: String?,

       val name: String?,
       val surname: String?,

       @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
       val accounts: Set<Account>?
) {

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (surname?.hashCode() ?: 0)
        return result
    }
}
