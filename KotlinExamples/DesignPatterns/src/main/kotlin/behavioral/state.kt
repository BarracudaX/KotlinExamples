package behavioral

/**
 * State design pattern allows managing states of an object through defined set of classes implementing a State interface without using if/switches inside the object.
 */

data class VendingMachineProduct(val price: Int,val code: String)

interface VendingMachineState{
    fun turnOn()

    fun turnOff()

    fun putMoney(money: Int)

    fun chooseProduct(code: String)

}

class VendingMachineOffState(private val vendingMachine: VendingMachine) : VendingMachineState{
    override fun turnOn() {
        println("Turning vending machine on...")
        vendingMachine.changeState(VendingMachineOnState(vendingMachine))
    }

    override fun turnOff() {
        println("The machine is already off.")
    }

    override fun putMoney(money: Int) {
        println("The machine is off. Returning money back...")
    }

    override fun chooseProduct(code: String) {
        println("The machine is currently off.")
    }


}

class VendingMachineOnState(private val vendingMachine: VendingMachine) : VendingMachineState{
    override fun turnOn() {
        println("The machine is already on...")
    }

    override fun turnOff() {
        println("Turning machine off...")
        vendingMachine.changeState(VendingMachineOffState(vendingMachine))
    }

    override fun putMoney(money: Int) {
        println("Choose the product first.")
    }

    override fun chooseProduct(code: String) {
        val product = vendingMachine.getProduct(code)

        if(product == null){
            println("This product has been exhausted or is unknown.Please, choose another or come back later after the product is added.")
        }else{
            vendingMachine.changeState(VendingMachineProductSelectedState(vendingMachine,product))
        }

    }


}

class VendingMachineProductSelectedState(private val vendingMachine: VendingMachine,private val selectedProduct: VendingMachineProduct) : VendingMachineState{

    private var currentAmount: Int = 0

    override fun turnOn() {
        println("The vending machine is already on...")
    }

    override fun turnOff() {
        println("Turning the vending machine off...")
        vendingMachine.changeState(VendingMachineOffState(vendingMachine))
    }

    override fun putMoney(money: Int) {
        currentAmount += money
        if(currentAmount > selectedProduct.price){
            println("Returning ${currentAmount - selectedProduct.price} change...")
            println("Removing product $selectedProduct")
            vendingMachine.removeProduct(selectedProduct)
            vendingMachine.changeState(VendingMachineOnState(vendingMachine))
        }else if(currentAmount == selectedProduct.price){
            println("Removing product $selectedProduct")
            vendingMachine.removeProduct(selectedProduct)
            vendingMachine.changeState(VendingMachineOnState(vendingMachine))
        }
    }

    override fun chooseProduct(code: String) {
        VendingMachineOnState(vendingMachine).chooseProduct(code)
    }

}

class VendingMachine : VendingMachineState{

    private var products = mutableListOf<VendingMachineProduct>()
    private var state: VendingMachineState = VendingMachineOffState(this)


    override fun turnOn() = state.turnOn()

    override fun turnOff() = state.turnOff()

    override fun putMoney(money: Int) = state.putMoney(money)

    override fun chooseProduct(code: String) = state.chooseProduct(code)

    fun changeState(state: VendingMachineState){
        this.state = state
    }

    fun getProduct(code: String) : VendingMachineProduct? = products.firstOrNull { it.code == code }

    fun removeProduct(product: VendingMachineProduct){
        products.remove(product)
    }

    fun addProduct(product: VendingMachineProduct){
        products.add(product)
    }
}


fun main(){
    val vendingMachine = VendingMachine().apply {
        addProduct(VendingMachineProduct(5,"300"))
    }

    vendingMachine.turnOn()
    vendingMachine.chooseProduct("209")
    vendingMachine.chooseProduct("300")
    vendingMachine.putMoney(3)
    vendingMachine.putMoney(2)
    vendingMachine.chooseProduct("300")
}
