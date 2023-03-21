package creational

/**
 * Factory Method Pattern allows superclass to focus on business logic using some abstraction, while allowing subclass to specify the actual class of the abstraction that is used by the superclass.
 */

interface Transport{

    fun transport(cargo: String)

}

class Ship : Transport{
    override fun transport(cargo: String) {
        println("Transporting cargo $cargo over sea.")
    }

}

class Truck : Transport{
    override fun transport(cargo: String) {
        println("Transporting cargo $cargo with a truck.")
    }

}


abstract class LogisticService{

    protected abstract fun newTransport() : Transport


    fun transport(cargo: String){
        val transport = newTransport()

        transport.transport(cargo)
    }

}

class SeaLogisticService : LogisticService(){
    override fun newTransport(): Transport = Ship()

}

class TruckLogisticService : LogisticService(){
    override fun newTransport(): Transport = Truck()

}
