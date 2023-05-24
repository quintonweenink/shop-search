package nl.jdriven.shop.product

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface ProductRepository : JpaRepository<Product?, String?> {

    @Query("select * from product p where p.id like %?1% or p.name like %?1% or p.description like %?1% or p.price like %?1%" , nativeQuery = true)
    fun customSearch(searchField: String): List<Product>
}