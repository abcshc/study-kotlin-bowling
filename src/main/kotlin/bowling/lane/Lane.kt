package bowling.lane

import bowling.lane.exception.InvalidPinNumberException

class Lane {
    private var pins = BooleanArray(10) { false }
    fun knockedOver(pinNumbers: IntArray) {
        pinNumbers.forEach {
            if (it > 10 || it < 1)
                throw InvalidPinNumberException()
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