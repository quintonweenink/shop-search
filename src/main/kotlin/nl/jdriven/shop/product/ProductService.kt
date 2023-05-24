package nl.jdriven.shop.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProductService @Autowired constructor(private var productRepository: ProductRepository) {

    fun search(searchString: String): List<Product> {
        return productRepository.customSearch(searchString);
    }

    fun insert(product: ProductDTO) {
        val entity = Product(product.id, product.name, product.description, product.price)
        productRepository.save(entity);
    }

    fun updatePrice(id: String, price: Double) {
        val result = productRepository.findByIdOrNull(id);

        if (result != null) {
            result.price = price
            productRepository.save(result)
        } else throw NoSuchElementException()

    }
}