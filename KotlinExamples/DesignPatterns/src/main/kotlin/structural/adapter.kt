package structural

/**
 * Adapter design pattern allows using an existing code to implement a functionality of an Interface when the existing code does not implement the interface or when it
 * partially implements the functionality. This is done by adapting the existing class to an interface we are willing to implement.
 */

interface Formatter{

    fun format( value: String) : String

}

class ExclamationFormatter : Formatter{
    override fun format(value: String): String = "$value!"

}

class QuestionMarkAdder{

    fun addQuestionMark(value: String) : String = "$value?"

}

class QuestionMarkAdderFormatterAdapter(private val questionMarkAdder: QuestionMarkAdder) : Formatter{
    override fun format(value: String): String = questionMarkAdder.addQuestionMark(value)

}