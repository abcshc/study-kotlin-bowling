package bowling.score

import bowling.score.exception.InvalidRollCountException
import bowling.score.exception.InvalidRollScoreException
import kotlin.test.assertEquals
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

class LastFrameTest {
    private lateinit var lastFrame: Frame

    @BeforeTest
    fun setup() {
        lastFrame = LastFrame()
    }

    @Test
    fun `정상적인 롤 스코어 입력 (2번 입력)`() {
        lastFrame.setNextRollScore(5)
        assertEquals(5, lastFrame.getFrameScore())
        lastFrame.setNextRollScore(3)
        assertEquals(8, lastFrame.getFrameScore())

        assertEquals(5, lastFrame.getRollScore(0))
        assertEquals(3, lastFrame.getRollScore(1))
    }

    @Test
    fun `정상적인 롤 스코어 입력 (2번 입력, 첫 시도 0점)`() {
        lastFrame.setNextRollScore(0)
        assertEquals(0, lastFrame.getFrameScore())
        lastFrame.setNextRollScore(3)
        assertEquals(3, lastFrame.getFrameScore())

        assertEquals(0, lastFrame.getRollScore(0))
        assertEquals(3, lastFrame.getRollScore(1))
    }

    @Test
    fun `정상적인 롤 스코어 입력 (2번 입력, 모두 0점)`() {
        lastFrame.setNextRollScore(0)
        assertEquals(0, lastFrame.getFrameScore())
        lastFrame.setNextRollScore(0)
        assertEquals(0, lastFrame.getFrameScore())

        assertEquals(0, lastFrame.getRollScore(0))
        assertEquals(0, lastFrame.getRollScore(1))
    }

    @Test
    fun `정상적인 롤 스코어 입력 (2번째 클리어, 3번 입력)`() {
        lastFrame.setNextRollScore(5)
        assertEquals(5, lastFrame.getFrameScore())
        lastFrame.setNextRollScore(5)
        assertEquals(10, lastFrame.getFrameScore())
        lastFrame.setNextRollScore(5)
        assertEquals(15, lastFrame.getFrameScore())

        assertEquals(5, lastFrame.getRollScore(0))
        assertEquals(5, lastFrame.getRollScore(1))
        assertEquals(5, lastFrame.getRollScore(2))
    }

    @Test
    fun `정상적인 롤 스코어 입력 (1번째 클리어, 3번 입력)`() {
        lastFrame.setNextRollScore(10)
        assertEquals(10, lastFrame.getFrameScore())
        lastFrame.setNextRollScore(5)
        assertEquals(15, lastFrame.getFrameScore())
        lastFrame.setNextRollScore(5)
        assertEquals(20, lastFrame.getFrameScore())

        assertEquals(10, lastFrame.getRollScore(0))
        assertEquals(5, lastFrame.getRollScore(1))
        assertEquals(5, lastFrame.getRollScore(2))
    }

    @Test
    fun `정상적인 롤 스코어 입력 (3번 클리어, 10점 3번 입력)`() {
        lastFrame.setNextRollScore(10)
        assertEquals(10, lastFrame.getFrameScore())
        lastFrame.setNextRollScore(10)
        assertEquals(20, lastFrame.getFrameScore())
        lastFrame.setNextRollScore(10)
        assertEquals(30, lastFrame.getFrameScore())

        assertEquals(10, lastFrame.getRollScore(0))
        assertEquals(10, lastFrame.getRollScore(1))
        assertEquals(10, lastFrame.getRollScore(2))
    }

    @Test(expected = InvalidRollCountException::class)
    fun `잘못된 스코어 입력 (10개보다 많은 점수 입력)`() {
        lastFrame.setNextRollScore(10)
        lastFrame.setNextRollScore(5)
        lastFrame.setNextRollScore(6)
    }

    @Test(expected = InvalidRollCountException::class)
    fun `클리어 못했는데 3번 롤하는 경우`() {
        lastFrame.setNextRollScore(3)
        lastFrame.setNextRollScore(4)
        lastFrame.setNextRollScore(4)
    }

    @Test(expected = InvalidRollCountException::class)
    fun `클리어 못했는데 3번 롤하는 경우 0점`() {
        lastFrame.setNextRollScore(0)
        lastFrame.setNextRollScore(0)
        lastFrame.setNextRollScore(0)
    }

    @Test(expected = InvalidRollScoreException::class)
    fun `잘못된 스코어 입력`() {
        lastFrame.setNextRollScore(15)
    }

    @Test(expected = InvalidRollCountException::class)
    fun `스코어 4번 입력`() {
        lastFrame.setNextRollScore(10)
        lastFrame.setNextRollScore(10)
        lastFrame.setNextRollScore(10)
        lastFrame.setNextRollScore(10)
    }

    @Test
    fun `스코어 4번 입력 (마지막 동작에만 Exception, 이전 동작 확인)`() {
        lastFrame.setNextRollScore(10)
        assertEquals(10, lastFrame.getFrameScore())
        lastFrame.setNextRollScore(10)
        assertEquals(20, lastFrame.getFrameScore())
        lastFrame.setNextRollScore(10)
        assertEquals(30, lastFrame.getFrameScore())

        assertEquals(10, lastFrame.getRollScore(0))
        assertEquals(10, lastFrame.getRollScore(1))
        assertEquals(10, lastFrame.getRollScore(2))

        // 예외 발생
        assertFailsWith(InvalidRollCountException::class) {
            lastFrame.setNextRollScore(10)
        }
    }
}