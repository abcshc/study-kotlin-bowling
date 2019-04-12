package bowling.lane

import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class LaneTest {
    lateinit var lane: Lane
    @Before
    fun setup() {
        lane = Lane()
    }

    @Test
    fun test_knockedOver() {
        lane.knockedOver(intArrayOf(1, 2, 3, 4))
        assertEquals(4, lane.getKnockedOverCount())
    }

    @Test
    fun test_initializeBowlingPins() {
        lane.knockedOver(intArrayOf(1, 2, 3, 4))
        lane.initializeBowlingPins()
        assertEquals(0, lane.getKnockedOverCount())
    }
}