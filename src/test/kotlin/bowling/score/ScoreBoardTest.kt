package bowling.score

import bowling.score.exception.EndedGameException
import bowling.score.exception.InvalidRollScoreException
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
    fun `getTotalScore 기능 테스트1`() {
        scoreBoard.roll(5)
        scoreBoard.roll(3)
        scoreBoard.roll(3)

        assertEquals(11, scoreBoard.getTotalScore())
    }

    @Test
    fun `getTotalScore 기능 테스트2`() {
        scoreBoard.roll(10)
        scoreBoard.roll(5)
        scoreBoard.roll(3)

        assertEquals(26, scoreBoard.getTotalScore())
    }

    @Test
    fun `게임 시작 전 상태 확인`() {
        assertEquals(GameStatus.READY, scoreBoard.getGameStatus())
    }

    @Test
    fun `게임 시작 후 상태 확인`() {
        scoreBoard.roll(5)
        assertEquals(GameStatus.PLAYING, scoreBoard.getGameStatus())
    }

    @Test
    fun `정상적인 롤 스코어 입력 프레임 변경`() {
        scoreBoard.roll(5)
        scoreBoard.roll(3)
        scoreBoard.roll(3)

        assertEquals(11, scoreBoard.getTotalScore())
        assertEquals(GameStatus.PLAYING, scoreBoard.getGameStatus())
    }

    @Test
    fun `정상적인 롤 스코어 입력 스트라이크 보너스 점수`() {
        scoreBoard.roll(10)
        scoreBoard.roll(5)
        scoreBoard.roll(3)

        assertEquals(26, scoreBoard.getTotalScore())
        assertEquals(GameStatus.PLAYING, scoreBoard.getGameStatus())
    }

    @Test
    fun `정상적인 롤 스코어 입력 4연속 스트라이크 보너스 점수`() {
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)

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

        assertEquals(300, scoreBoard.getTotalScore())
        assertEquals(GameStatus.COMPLETED, scoreBoard.getGameStatus())
    }

    @Test
    fun `정상적인 롤 스코어 입력 9프레임 퍼펙트 240`() {
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)
        scoreBoard.roll(10)

        assertEquals(240, scoreBoard.getTotalScore())
        assertEquals(GameStatus.PLAYING, scoreBoard.getGameStatus())
    }

    @Test
    fun `정상적인 롤 스코어 입력 스페어 보너스 점수`() {
        scoreBoard.roll(5)
        scoreBoard.roll(5)
        scoreBoard.roll(8)
        scoreBoard.roll(1)

        assertEquals(27, scoreBoard.getTotalScore())
        assertEquals(GameStatus.PLAYING, scoreBoard.getGameStatus())
    }

    @Test
    fun `정상적인 롤 스코어 입력 프레임 변경 10프레임까지`() {
        scoreBoard.repeatableRoll2TimesWith3Points(10)

        assertEquals(60, scoreBoard.getTotalScore())
        assertEquals(GameStatus.COMPLETED, scoreBoard.getGameStatus())
    }

    @Test
    fun `마지막 프레임 추가 점수입력`() {
        scoreBoard.repeatableRoll2TimesWith3Points(9)
        scoreBoard.roll(5)
        scoreBoard.roll(5)
        scoreBoard.roll(5)

        assertEquals(69, scoreBoard.getTotalScore())
        assertEquals(GameStatus.COMPLETED, scoreBoard.getGameStatus())
    }

    @Test(expected = InvalidRollScoreException::class)
    fun `10점보다 많은 점수입력`() {
        scoreBoard.roll(11)
    }

    @Test
    fun `6프레임에서 10점보다 많은 점수입력`() {
        scoreBoard.repeatableRoll2TimesWith3Points(5)

//        assertEquals(30, scoreBoard.getTotalScore())
        assertFailsWith(InvalidRollScoreException::class) {
            scoreBoard.roll(11)
        }
    }

    @Test(expected = InvalidRollScoreException::class)
    fun `10점보다 많은 점수입력(2번 입력의 합)`() {
        scoreBoard.roll(5)
        scoreBoard.roll(6)
    }

    @Test
    fun `마지막 프레임 이후 점수입력`() {
        scoreBoard.repeatableRoll2TimesWith3Points(10)

        assertEquals(60, scoreBoard.getTotalScore())
        assertEquals(GameStatus.COMPLETED, scoreBoard.getGameStatus())

        assertFailsWith(EndedGameException::class) {
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

        assertEquals(108, scoreBoard.getTotalScore())
        assertEquals(GameStatus.COMPLETED, scoreBoard.getGameStatus())

        assertFailsWith(EndedGameException::class) {
            scoreBoard.roll(5)
        }
    }

    @Test
    fun `마지막 프레임 추가 점수입력 후 잘못된 점수입력`() {
        scoreBoard.repeatableRoll2TimesWith3Points(9)
        scoreBoard.roll(5)
        scoreBoard.roll(5)
        scoreBoard.roll(5)

        assertEquals(69, scoreBoard.getTotalScore())
        assertEquals(GameStatus.COMPLETED, scoreBoard.getGameStatus())

        assertFailsWith(EndedGameException::class) {
            scoreBoard.roll(5)
        }
    }

    private fun ScoreBoard.repeatableRoll2TimesWith3Points(repeat: Int) {
        for (i in 0 until repeat) {
            this.roll(3)
            this.roll(3)
        }
    }
}