package behavioral

import java.time.LocalDateTime

/**
 * Memento design pattern is used for undo and restoring state of the object to it's previous state without exposing private information of that object.
 */

class Editor{

    private var text: String = ""

    inner class Memento(val creationDate: LocalDateTime,private val textState: String){

        internal fun restore(){
            this@Editor.text = textState
        }

    }

    fun appendText(text: String){
        this.text += text
    }

    fun getState() : Memento = Memento(LocalDateTime.now(),text)

    fun restore(memento: Memento){
        memento.restore()
    }

    fun getText() : String = text

}

class EditorHistory(private val editor: Editor) {

    private val history = ArrayDeque<Editor.Memento>()

    fun onTextModification(){
        history.addLast(editor.getState())
    }

    fun undo(){
        val memento = history.removeLastOrNull() ?: return
        editor.restore(memento)
    }

}

fun main(){
    val editor = Editor()
    val history = EditorHistory(editor)

    editor.appendText("A")
    history.onTextModification()
    editor.appendText("B")
    history.onTextModification()
    editor.appendText("C")
    history.onTextModification()
    editor.appendText("D")

    println(editor.getText())

    history.undo()
    println(editor.getText())

    history.undo()
    println(editor.getText())

    history.undo()
    println(editor.getText())

    history.undo()
    println(editor.getText())
}