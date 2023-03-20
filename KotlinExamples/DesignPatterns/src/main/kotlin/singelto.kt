import java.util.concurrent.atomic.AtomicInteger

object CounterSingleton{

    private var counter = AtomicInteger(0)

    fun increment() = counter.incrementAndGet()
}