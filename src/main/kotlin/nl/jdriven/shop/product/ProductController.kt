package nl.jdriven.shop.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(@Autowired productService: ProductService) {

    private final var productService: ProductService
    init {
        this.productService = productService;
    }

    @PostMapping("/insert")
    fun insert(@RequestBody product: ProductDTO): String {
        productService.insert(product)
        return "Successfully inserted a new product"
    }

    @PutMapping("/{id}/price")
    fun updateProductPrice(@PathVariable id: String, @RequestParam price: Double): String {
        productService.updatePrice(id, price)
        return "Successfully updated the price of the product"
    }

    @GetMapping("/search")
    fun search(@RequestParam searchString: String): List<Product> {
        return productService.search(searchString)
    }
}