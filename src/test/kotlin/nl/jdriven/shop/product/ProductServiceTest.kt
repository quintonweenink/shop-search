package nl.jdriven.shop.product

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.springframework.data.repository.findByIdOrNull
import java.util.*


class ProductServiceTest {

    val productRepository = Mockito.mock<ProductRepository>()

    val productService = ProductService(productRepository)

    @Test
    fun search() {
        Mockito.`when`(productRepository.customSearch("002"))
            .thenReturn(
                listOf(
                    Product("002", "Desk", "Work space", 2.11)
                )
            )

        val response = productService.search("002")

        assertEquals("Desk", response.first().name)
        Mockito.verify(productRepository).customSearch("002")
    }

    @Test
    fun insert() {
        val dto = ProductDTO("001", "Desk", "Work space", 2.11)

        productService.insert(dto)

        val argument: ArgumentCaptor<Product> = ArgumentCaptor.forClass(Product::class.java)
        Mockito.verify(productRepository).save(argument.capture())
        assertEquals("001", argument.value.id);
    }

    @Disabled("Had some issues with mocking find by id")
    @Test
    fun updatePrice() {
        Mockito.`when`(productRepository.findByIdOrNull("002"))
            .thenReturn(Product("002", "Desk", "Work space", 2.11))

        productService.updatePrice("002", 3.11)

        val argument: ArgumentCaptor<Product> = ArgumentCaptor.forClass(Product::class.java)
        Mockito.verify(productRepository).save(argument.capture())
        assertEquals(3.11, argument.value.price);
    }

    @Test
    fun updatePriceForItemNotFound() {
        assertThrows(NoSuchElementException::class.java) {
            productService.updatePrice("002", 3.11)
        }

    }
}

