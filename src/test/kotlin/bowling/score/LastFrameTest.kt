package bowling.score

import bowling.score.exception.InvalidRollCountException
import bowling.score.exception.InvalidRollScoreException
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

    @Test
    fun `정상적인 롤 스코어 입력 (10점 3번 입력)`() {
        lasttFrame.setNextRollScore(10)
        assertEquals(10, lasttFrame.getRollScore(0))
        assertEquals(10, lasttFrame.getFrameScore())
        lasttFrame.setNextRollScore(10)
        assertEquals(10, lasttFrame.getRollScore(1))
        assertEquals(20, lasttFrame.getFrameScore())
        lasttFrame.setNextRollScore(10)
        assertEquals(10, lasttFrame.getRollScore(2))
        assertEquals(30, lasttFrame.getFrameScore())
    }

    @Test(expected = InvalidRollCountException::class)
    fun `잘못된 스코어 입력 (10개보다 많은 점수 입력)`() {
        lasttFrame.setNextRollScore(10)
        lasttFrame.setNextRollScore(5)
        lasttFrame.setNextRollScore(6)
    }

    @Test(expected = InvalidRollCountException::class)
    fun `클리어 못했는데 3번 롤하는 경우`() {
        lasttFrame.setNextRollScore(3)
        lasttFrame.setNextRollScore(4)
        lasttFrame.setNextRollScore(4)
    }

    @Test(expected = InvalidRollScoreException::class)
    fun `잘못된 스코어 입력`() {
        lasttFrame.setNextRollScore(15)
    }

    @Test(expected = InvalidRollCountException::class)
    fun `스코어 4번 입력`() {
        lasttFrame.setNextRollScore(10)
        lasttFrame.setNextRollScore(10)
        lasttFrame.setNextRollScore(10)
        lasttFrame.setNextRollScore(10)
    }
}