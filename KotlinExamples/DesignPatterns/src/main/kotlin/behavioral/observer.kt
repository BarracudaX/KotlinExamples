package behavioral

/**
 * Observer design patterns allows objects to subscribe to another object for notification through a defined interface which allows lose-coupling between the observer and observable object.
 */

interface Observer{

    fun priceChangeNotification(product: Product)

}

data class Product(val productName: String){

    private val observers = mutableListOf<Observer>()
    var price: Double = 0.0
        set(value) {
            field = value
            observers.forEach { it.priceChangeNotification(this) }
        }

    fun addObserver(observer: Observer){
        observers.add(observer)
    }

}