package creational

interface Unit

interface Infantry : Unit

enum class InfantryUnits{
    ROCKET_SOLDIER, RIFLEMEN
}
class RocketSoldier : Infantry

class Riflemen : Infantry

interface Vehicle : Unit

enum class VehicleUnits{
    APC,TANK
}

class APCVehicle : Vehicle

class Tank : Vehicle

interface Building<in UnitType,out ProducedUnit> where UnitType : Enum<*> , ProducedUnit : Unit{

    fun build(type: UnitType) : ProducedUnit

}

class HQ {

    private val buildings = mutableSetOf<Building<*,Unit>>()

    fun barracks() : Barracks{
        val building = Barracks()
        buildings.add(building)
        return building
    }

    fun vehicleFactory() : VehicleFactory {
        val building = VehicleFactory()
        buildings.add(building)
        return building
    }

}

class Barracks : Building<InfantryUnits,Infantry>{
    override fun build(type: InfantryUnits): Infantry = when(type){
        InfantryUnits.ROCKET_SOLDIER -> RocketSoldier()
        InfantryUnits.RIFLEMEN -> Riflemen()
    }

}

class VehicleFactory : Building<VehicleUnits,Vehicle>{
    override fun build(type: VehicleUnits): Vehicle = when(type){
        VehicleUnits.APC -> APCVehicle()
        VehicleUnits.TANK -> Tank()
    }

}