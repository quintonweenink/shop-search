package nl.jdriven.shop.product

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ProductControllerTest {

    val productService = mock<ProductService>()

    val productController = ProductController(productService)

    @Test
    fun insert() {
        val dto = ProductDTO("001", "Desk", "Work space", 2.11)

        val response = productController.insert(dto)

        assertEquals("Successfully inserted a new product", response)
        verify(productService).insert(dto)
    }

    @Test
    fun updateProductPrice() {
        val response = productController.updateProductPrice("002", 22.11)

        assertEquals("Successfully updated the price of the product", response)
        verify(productService).updatePrice("002", 22.11)
    }

    @Test
    fun search() {
        Mockito.`when`(productService.search("002"))
            .thenReturn(listOf(
                Product("002", "Desk", "Work space", 2.11)
            ))

        val response = productController.search("002")

        assertEquals("Desk", response.first().name)
        verify(productService).search("002")
    }
}