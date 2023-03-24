package behavioral

/**
 * Template Method design pattern allows creating an algorithm in a superclass while allowing subclasses redefine parts of it by overriding methods that are used during the execution of the algorithm.
 */

data class PackagedProduct(val name: String)

abstract class AbstractPackagingService{

    protected val products = mutableListOf<PackagedProduct>()

    fun addProduct(packagedProduct: PackagedProduct){
        products.add(packagedProduct)
    }

    fun finish(){
        val filteredProducts = filterProducts()
        println("Publishing $filteredProducts...")
        products.clear()
    }

    abstract fun filterProducts() : List<PackagedProduct>

}

class FilterSanctionedProducts(private val notAllowedProducts: List<String>) : AbstractPackagingService(){
    override fun filterProducts(): List<PackagedProduct> = products.filter { !notAllowedProducts.contains(it.name) }


}