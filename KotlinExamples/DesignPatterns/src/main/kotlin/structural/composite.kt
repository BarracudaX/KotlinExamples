package structural

/**
 * Composite design patterns allows dealing with composite objects that shape tree-like structure as if they were individual objects.
 */

private const val BOXING_PRICE = 10.5

interface Product{
    fun price() : Double
}

class SimpleProduct(private val price: Double) : Product {
    override fun price(): Double = price
}

class Box : Product{

    private val products = mutableListOf<Product>()

    override fun price(): Double = products.sumOf { it.price() } + BOXING_PRICE

    fun addProduct(product: Product){
        products.add(product)
    }


}