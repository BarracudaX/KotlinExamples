import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

interface Animal{
    val id: Long
}

abstract class Dog(override val id: Long) : Animal

class BullDog(id: Long) : Dog(id)

class Beagle(id: Long) : Dog(id)

abstract class Cat(override val id: Long) : Animal

class RussianBlue(id: Long) : Cat(id)

class Siamese(id: Long) : Cat(id)

class AnimalFactory {

    private var counter = AtomicLong(1)

    fun get(type: String,bread: String) : Animal =
        when(type){
            "dog" -> getDog(bread)
            "cat" -> getCat(bread)
            else -> throw IllegalArgumentException("Unknown animal $type.")
        }


    private fun getCat(bread: String) : Cat =
        when(bread){
            "russianblue" -> RussianBlue(counter.getAndIncrement())
            "siamese" -> Siamese(counter.getAndIncrement())
            else -> throw IllegalArgumentException("Unknown cat bread $bread.")
        }

    private fun getDog(bread: String) : Dog =
        when(bread){
            "bulldog" -> BullDog(counter.getAndIncrement())
            "beagle" -> Beagle(counter.getAndIncrement())
            else -> throw IllegalArgumentException("Unknown dog bread $bread.")
        }
}