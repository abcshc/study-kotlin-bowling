package bowling.lane

import bowling.lane.exception.InvalidPinNumberException
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class LaneTest {
    lateinit var lane: Lane

    @BeforeTest
    fun setup() {
        lane = Lane()
    }

    @Test
    fun `knocked over 1, 2, 3, 4 pin`() {
        lane.knockedOver(intArrayOf(1, 2, 3, 4))
        assertEquals(4, lane.getKnockedOverCount())
    }

    @Test
    fun `initialize bowling pins in lane`() {
        lane.knockedOver(intArrayOf(1, 2, 3, 4))
        lane.initializeBowlingPins()
        assertEquals(0, lane.getKnockedOverCount())
    }

    @Test
    fun `throw Exception when input invalid pin number`() {
        assertFailsWith(InvalidPinNumberException::class) {
            lane.knockedOver(intArrayOf(0))
        }

        assertFailsWith(InvalidPinNumberException::class) {
            lane.knockedOver(intArrayOf(11))
        }
    }
}