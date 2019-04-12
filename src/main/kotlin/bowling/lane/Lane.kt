package bowling.lane

class Lane {
    private var pins = BooleanArray(10) { false }
    fun knockedOver(pinNumbers: IntArray) {
        pinNumbers.forEach {
            pins[it] = true
        }
    }

    fun getKnockedOverCount(): Int {
        return pins.count { it }
    }

    fun initializeBowlingPins() {
        pins = BooleanArray(10) { false }
    }
}