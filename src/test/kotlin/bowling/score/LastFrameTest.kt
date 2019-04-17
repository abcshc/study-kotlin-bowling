package bowling.score

import bowling.score.exception.InvalidRollCountException
import kotlin.test.assertEquals
import kotlin.test.BeforeTest
import kotlin.test.Test

class LastFrameTest {
    lateinit var lasttFrame: Frame

    @BeforeTest
    fun setup() {
        lasttFrame = LastFrame()
    }

    @Test
    fun `정상적인 롤 스코어 입력 (2번 입력)`() {
        lasttFrame.setNextRollScore(5)
        assertEquals(5, lasttFrame.getRollScore(0))
        assertEquals(5, lasttFrame.getFrameScore())
        lasttFrame.setNextRollScore(3)
        assertEquals(3, lasttFrame.getRollScore(1))
        assertEquals(8, lasttFrame.getFrameScore())
    }

    @Test
    fun `정상적인 롤 스코어 입력 (3번 입력)`() {
        lasttFrame.setNextRollScore(5)
        assertEquals(5, lasttFrame.getRollScore(0))
        assertEquals(5, lasttFrame.getFrameScore())
        lasttFrame.setNextRollScore(5)
        assertEquals(5, lasttFrame.getRollScore(1))
        assertEquals(10, lasttFrame.getFrameScore())
        lasttFrame.setNextRollScore(5)
        assertEquals(5, lasttFrame.getRollScore(2))
        assertEquals(15, lasttFrame.getFrameScore())
    }

    @Test(expected = InvalidRollCountException::class)
    fun `잘못된 스코어 점수 입력 (10개보다 많은 점수 입력)`() {
        lasttFrame.setNextRollScore(10)
        assertEquals(10, lasttFrame.getRollScore(0))
        assertEquals(10, lasttFrame.getFrameScore())
        lasttFrame.setNextRollScore(5)
        assertEquals(5, lasttFrame.getRollScore(1))
        assertEquals(15, lasttFrame.getFrameScore())
        lasttFrame.setNextRollScore(6)
        assertEquals(6, lasttFrame.getRollScore(2))
        assertEquals(21, lasttFrame.getFrameScore())
    }
}