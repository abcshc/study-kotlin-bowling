package bowling.score

import bowling.score.exception.EndedGameException
import bowling.score.exception.InvalidRollScoreException
import kotlin.test.*

class DefaultFrameTest {
    lateinit var defaultFrame: Frame

    @BeforeTest
    fun setup() {
        defaultFrame = DefaultFrame()
    }

    @Test
    fun `정상적인 롤 스코어 입력 (2번 입력)`() {
        defaultFrame.setNextRollScore(5)
        assertEquals(5, defaultFrame.getRollScore(0))
        assertEquals(5, defaultFrame.getFrameScore())
        defaultFrame.setNextRollScore(3)
        assertEquals(3, defaultFrame.getRollScore(1))
        assertEquals(8, defaultFrame.getFrameScore())
    }

    @Test
    fun `정상적인 롤 스코어 입력 0점`() {
        defaultFrame.setNextRollScore(0)
        assertEquals(0, defaultFrame.getRollScore(0))
        assertEquals(0, defaultFrame.getFrameScore())
        defaultFrame.setNextRollScore(0)
        assertEquals(0, defaultFrame.getRollScore(1))
        assertEquals(0, defaultFrame.getFrameScore())
    }

    @Test
    fun `정상적인 롤 스코어 입력 0점 후 10점`() {
        defaultFrame.setNextRollScore(0)
        assertEquals(0, defaultFrame.getRollScore(0))
        assertEquals(0, defaultFrame.getFrameScore())
        defaultFrame.setNextRollScore(10)
        assertEquals(10, defaultFrame.getRollScore(1))
        assertEquals(10, defaultFrame.getFrameScore())
    }

    @Test
    fun `정상적인 롤 스코어 입력 합계 10점`() {
        defaultFrame.setNextRollScore(5)
        assertEquals(5, defaultFrame.getRollScore(0))
        assertEquals(5, defaultFrame.getFrameScore())
        defaultFrame.setNextRollScore(5)
        assertEquals(5, defaultFrame.getRollScore(1))
        assertEquals(10, defaultFrame.getFrameScore())
    }

    @Test
    fun `종료되지 않은 프레임 확인 일반 점수 입력`() {
        defaultFrame.setNextRollScore(5)
        assertFalse(defaultFrame.isEndedFrame())
    }

    @Test
    fun `종료되지 않은 프레임 확인 0점`() {
        defaultFrame.setNextRollScore(0)
        assertFalse(defaultFrame.isEndedFrame())
    }

    @Test
    fun `종료된 프레임 확인 일반 점수 입력`() {
        defaultFrame.setNextRollScore(3)
        defaultFrame.setNextRollScore(5)
        assertTrue(defaultFrame.isEndedFrame())
    }

    @Test
    fun `종료된 프레임 확인 스트라이크`() {
        defaultFrame.setNextRollScore(10)
        assertTrue(defaultFrame.isEndedFrame())
    }

    @Test
    fun `종료된 프레임 확인 스페어`() {
        defaultFrame.setNextRollScore(9)
        defaultFrame.setNextRollScore(1)
        assertTrue(defaultFrame.isEndedFrame())
    }

    @Test
    fun `종료된 프레임 확인 0졈 입력`() {
        defaultFrame.setNextRollScore(0)
        defaultFrame.setNextRollScore(0)
        assertTrue(defaultFrame.isEndedFrame())
    }

    @Test(expected = EndedGameException::class)
    fun `3번 입력으로 인한 예외 발생`() {
        defaultFrame.setNextRollScore(1)
        defaultFrame.setNextRollScore(2)
        defaultFrame.setNextRollScore(3)
    }

    @Test(expected = EndedGameException::class)
    fun `첫 10점 후 입력으로 인한 예외 발생`() {
        defaultFrame.setNextRollScore(10)
        defaultFrame.setNextRollScore(1)
    }

    @Test(expected = InvalidRollScoreException::class)
    fun `첫번째 롤에서 10보다 높은 롤 스코어로 인한 예외 발생`() {
        defaultFrame.setNextRollScore(11)
    }

    @Test(expected = InvalidRollScoreException::class)
    fun `두번째 롤에서 프레임 스코어가 10보다 높은 롤 스코어로 인한 예외 발생`() {
        defaultFrame.setNextRollScore(5)
        defaultFrame.setNextRollScore(6)
    }
}