package bowling.score

import bowling.score.exception.InvalidRollCountException
import bowling.score.exception.OverFrameException
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ScoreBoardTest {
    private lateinit var scoreBoard: ScoreBoard

    @BeforeTest
    fun setup() {
        scoreBoard = ScoreBoard()
    }

    @Test
    fun `게임 시작 전 상태 확인`() {
        assertEquals(GameStatus.READY, scoreBoard.getGameStatus())
    }

    @Test
    fun `게임 시작 후 상태 확인`() {
        assertEquals(GameStatus.PLAYING, scoreBoard.getGameStatus())
    }

    @Test
    fun `정상적인 롤 스코어 입력 프레임 변경`() {
        scoreBoard.roll(5)
        scoreBoard.roll(3)
        scoreBoard.roll(3)

        assertEquals(2, scoreBoard.getCurrentFrameNumber())
        assertEquals(11, scoreBoard.getTotalScore())
        assertEquals(GameStatus.PLAYING, scoreBoard.getGameStatus())
    }

    @Test
    fun `정상적인 롤 스코어 입력 스트라이크 보너스 점수`() {
        scoreBoard.roll(10)
        scoreBoard.roll(5)
        scoreBoard.roll(3)

        assertEquals(3, scoreBoard.getCurrentFrameNumber())
        assertEquals(26, scoreBoard.getTotalScore())
        assertEquals(GameStatus.PLAYING, scoreBoard.getGameStatus())
    }

    @Test
    fun `정상적인 롤 스코어 입력 4연속 스트라이크 보너스 점수`() {
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)

        assertEquals(5, scoreBoard.getCurrentFrameNumber())
        assertEquals(90, scoreBoard.getTotalScore())
        assertEquals(GameStatus.PLAYING, scoreBoard.getGameStatus())
    }

    @Test
    fun `정상적인 롤 스코어 입력 퍼펙트게임 보너스 점수`() {
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)

        assertEquals(10, scoreBoard.getCurrentFrameNumber())
        assertEquals(300, scoreBoard.getTotalScore())
        assertEquals(GameStatus.COMPLETED, scoreBoard.getGameStatus())
    }

    @Test
    fun `정상적인 롤 스코어 입력 스페어 보너스 점수`() {
        scoreBoard.roll(5)
        scoreBoard.roll(5)
        scoreBoard.roll(8)
        scoreBoard.roll(1)

        assertEquals(3, scoreBoard.getCurrentFrameNumber())
        assertEquals(27, scoreBoard.getTotalScore())
        assertEquals(GameStatus.PLAYING, scoreBoard.getGameStatus())
    }

    @Test
    fun `정상적인 롤 스코어 입력 프레임 변경 10프레임까지`() {
        scoreBoard.repeatableRoll2TimesWith3Points(10)

        assertEquals(10, scoreBoard.getCurrentFrameNumber())
        assertEquals(60, scoreBoard.getTotalScore())
        assertEquals(GameStatus.COMPLETED, scoreBoard.getGameStatus())
    }

    @Test
    fun `마지막 프레임 추가 점수입력`() {
        scoreBoard.repeatableRoll2TimesWith3Points(9)
        scoreBoard.roll(5)
        scoreBoard.roll(5)
        scoreBoard.roll(5)


        assertEquals(10, scoreBoard.getCurrentFrameNumber())
        assertEquals(75, scoreBoard.getTotalScore())
        assertEquals(GameStatus.COMPLETED, scoreBoard.getGameStatus())
    }

    @Test(expected = InvalidRollCountException::class)
    fun `10점보다 많은 점수입력`() {
        scoreBoard.roll(11)
    }

    @Test
    fun `6프레임에서 10점보다 많은 점수입력`() {
        scoreBoard.repeatableRoll2TimesWith3Points(5)

        assertEquals(6, scoreBoard.getCurrentFrameNumber())
        assertEquals(30, scoreBoard.getTotalScore())
        assertFailsWith(InvalidRollCountException::class) {
            scoreBoard.roll(11)
        }
    }

    @Test(expected = InvalidRollCountException::class)
    fun `10점보다 많은 점수입력(2번 입력의 합)`() {
        scoreBoard.roll(5)
        scoreBoard.roll(6)
    }

    @Test
    fun `마지막 프레임 이후 점수입력`() {
        scoreBoard.repeatableRoll2TimesWith3Points(10)

        assertEquals(10, scoreBoard.getCurrentFrameNumber())
        assertEquals(60, scoreBoard.getTotalScore())
        assertEquals(GameStatus.COMPLETED, scoreBoard.getGameStatus())

        assertFailsWith(OverFrameException::class) {
            scoreBoard.roll(5)
        }
    }

    @Test
    fun `마지막 프레임 이후 점수입력 스트라이크 포함`() {
        scoreBoard.repeatableRoll2TimesWith3Points(8)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)

        assertEquals(10, scoreBoard.getCurrentFrameNumber())
        assertEquals(108, scoreBoard.getTotalScore())
        assertEquals(GameStatus.COMPLETED, scoreBoard.getGameStatus())

        assertFailsWith(OverFrameException::class) {
            scoreBoard.roll(5)
        }
    }

    @Test
    fun `마지막 프레임 추가 점수입력 후 잘못된 점수입력`() {
        scoreBoard.repeatableRoll2TimesWith3Points(9)
        scoreBoard.roll(5)
        scoreBoard.roll(5)
        scoreBoard.roll(5)


        assertEquals(10, scoreBoard.getCurrentFrameNumber())
        assertEquals(75, scoreBoard.getTotalScore())
        assertEquals(GameStatus.COMPLETED, scoreBoard.getGameStatus())

        assertFailsWith(OverFrameException::class) {
            scoreBoard.roll(5)
        }
    }

    private fun ScoreBoard.repeatableRoll2TimesWith3Points(repeat: Int) {
        for (i in 0..repeat) {
            this.roll(3)
            this.roll(3)
        }
    }
}