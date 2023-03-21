package creational

/**
 * Abstract factory pattern allows creation of related objects.
 */

interface Unit

interface Vehicle : Unit

interface AttackUnit : Unit

class BigWolf : Vehicle

class Horse : Vehicle

class Orc : AttackUnit

class Knight : AttackUnit

interface UnitFactory{

    fun createAttackUnit() : AttackUnit

    fun createVehicle() : Vehicle

}

class AllianceUnitFactory : UnitFactory{
    override fun createAttackUnit(): AttackUnit = Knight()

    override fun createVehicle(): Vehicle  = Horse()

}

class HordeUnitFactory : UnitFactory{
    override fun createAttackUnit(): AttackUnit = Orc()

    override fun createVehicle(): Vehicle = BigWolf()

}

