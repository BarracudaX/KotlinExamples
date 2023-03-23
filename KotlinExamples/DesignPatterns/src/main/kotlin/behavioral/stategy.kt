package behavioral

/**
 * Strategy design pattern allows defining a family of interchangeable algorithms that is used by class and,optionally,change this algorithm at runtime.
 */

interface TaxesService{

    fun taxed(money: Double) : Double

}

class GreeceTaxesService : TaxesService{
    override fun taxed(money: Double): Double = money - (money*0.15)

}

class GermanyTaxesService : TaxesService{
    override fun taxed(money: Double): Double {
        return if(money < 1000){
            money - (money*0.20)
        }else if(money < 5000){
            money - (money*0.25)
        }else{
            money - (money*0.30)
        }
    }

}

class PaymentService(private var taxesService: TaxesService){

    private val regularPayment = 1000.0

    fun changeTaxesRules(taxesService: TaxesService){
        this.taxesService = taxesService
    }

    fun getPayment(hours: Int) : Double{
        return if(hours <= 50){
            taxesService.taxed(regularPayment)
        }else if(hours <= 100){
            val additionalHours = hours - 50
            taxesService.taxed(regularPayment + 40*additionalHours)
        }else{
            val additionalHours = hours - 50
            taxesService.taxed(regularPayment + 40*additionalHours+200)
        }
    }

}