package nl.jdriven.shop.product

import jakarta.persistence.Entity
import jakarta.persistence.Id


@Entity
class Product (

    @Id
    var id: String,
    var name: String,
    var description: String,
    var price: Double

)