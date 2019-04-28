package bowling.score

import bowling.score.exception.EndedFrameException
import bowling.score.exception.EndedGameException
import bowling.score.exception.InvalidRollScoreException
import kotlin.test.*

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

    @Test
    fun `종료되지 않은 프레임 확인 0점`() {
        lastFrame.setNextRollScore(0)
        assertFalse(lastFrame.isEndedFrame())
    }

    @Test
    fun `종료된 프레임 확인 일반 점수 입력`() {
        lastFrame.setNextRollScore(3)
        lastFrame.setNextRollScore(5)
        assertTrue(lastFrame.isEndedFrame())
    }

    @Test
    fun `종료되지 않은 프레임 확인 스트라이크`() {
        lastFrame.setNextRollScore(10)
        assertFalse(lastFrame.isEndedFrame())
    }

    @Test
    fun `종료되지 않은 프레임 확인 2번 스트라이크`() {
        lastFrame.setNextRollScore(10)
        lastFrame.setNextRollScore(10)
        assertFalse(lastFrame.isEndedFrame())
    }

    @Test
    fun `종료되지 않은 프레임 확인 스페어`() {
        lastFrame.setNextRollScore(9)
        lastFrame.setNextRollScore(1)
        assertFalse(lastFrame.isEndedFrame())
    }

    @Test
    fun `종료된 프레임 확인 0졈 입력`() {
        lastFrame.setNextRollScore(0)
        lastFrame.setNextRollScore(0)
        assertTrue(lastFrame.isEndedFrame())
    }

    @Test
    fun `종료된 프레임 확인 스페어 후 일반 점수 입력`() {
        lastFrame.setNextRollScore(5)
        lastFrame.setNextRollScore(5)
        lastFrame.setNextRollScore(5)
        assertTrue(lastFrame.isEndedFrame())
    }

    @Test
    fun `종료된 프레임 확인 스페어 후 스트라이크 입력`() {
        lastFrame.setNextRollScore(5)
        lastFrame.setNextRollScore(5)
        lastFrame.setNextRollScore(10)
        assertTrue(lastFrame.isEndedFrame())
    }

    @Test
    fun `종료된 프레임 확인 스트라이크 후 일반 점수 입력`() {
        lastFrame.setNextRollScore(10)
        lastFrame.setNextRollScore(5)
        lastFrame.setNextRollScore(5)
        assertTrue(lastFrame.isEndedFrame())
    }

    @Test
    fun `종료된 프레임 확인 스트라이크 3번`() {
        lastFrame.setNextRollScore(10)
        lastFrame.setNextRollScore(10)
        lastFrame.setNextRollScore(10)
        assertTrue(lastFrame.isEndedFrame())
    }

    @Test(expected = InvalidRollScoreException::class)
    fun `잘못된 스코어 입력 (10개보다 많은 점수 입력)`() {
        lastFrame.setNextRollScore(10)
        lastFrame.setNextRollScore(5)
        lastFrame.setNextRollScore(6)
    }

    @Test(expected = EndedGameException::class)
    fun `클리어 못했는데 3번 롤하는 경우`() {
        lastFrame.setNextRollScore(3)
        lastFrame.setNextRollScore(4)
        lastFrame.setNextRollScore(4)
    }

    @Test(expected = EndedGameException::class)
    fun `클리어 못했는데 3번 롤하는 경우 0점`() {
        lastFrame.setNextRollScore(0)
        lastFrame.setNextRollScore(0)
        lastFrame.setNextRollScore(0)
    }

    @Test(expected = InvalidRollScoreException::class)
    fun `잘못된 스코어 입력`() {
        lastFrame.setNextRollScore(15)
    }

    @Test(expected = EndedGameException::class)
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
        assertFailsWith(EndedGameException::class) {
            lastFrame.setNextRollScore(10)
        }
    }

    @Test
    fun `프레임 시작 상태확인 준비`() {
        assertFalse(lastFrame.isStartedFrame())
    }

    @Test
    fun `프레임 시작 상태확인 진행중`() {
        lastFrame.setNextRollScore(4)
        assertTrue(lastFrame.isStartedFrame())
    }

    @Test
    fun `프레임 시작 상태확인 스페어 이후 진행중`() {
        lastFrame.setNextRollScore(5)
        lastFrame.setNextRollScore(5)
        assertTrue(lastFrame.isStartedFrame())
    }

    @Test
    fun `프레임 종료 상태확인 진행중`() {
        lastFrame.setNextRollScore(4)
        assertFalse(lastFrame.isEndedFrame())
    }

    @Test
    fun `프레임 종료 상태확인 스페어 이후 확인`() {
        lastFrame.setNextRollScore(5)
        lastFrame.setNextRollScore(5)
        assertFalse(lastFrame.isEndedFrame())
    }

    @Test
    fun `프레임 종료 상태확인 종료`() {
        lastFrame.setNextRollScore(4)
        lastFrame.setNextRollScore(4)
        assertTrue(lastFrame.isEndedFrame())
    }

    @Test
    fun `프레임 종료 상태확인 3번 점수 입력 후 종료`() {
        lastFrame.setNextRollScore(5)
        lastFrame.setNextRollScore(5)
        lastFrame.setNextRollScore(5)
        assertTrue(lastFrame.isEndedFrame())
    }
}