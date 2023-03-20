package creational

class MyClass private constructor(){


    companion object{
        private lateinit var cached: MyClass

        fun getNewOrCached() : MyClass {

            if(!this::cached.isInitialized){
                cached = MyClass()
            }

            return cached
        }

    }

}