package bowling.score

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ScoreBoardTest {
    private lateinit var scoreBoard: ScoreBoard

    @BeforeTest
    fun setup() {
        scoreBoard = ScoreBoard()
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

    // TODO: extension이 되어야 하는 함수로 보임 수정하기
    private fun ScoreBoard.repeatableRoll2TimesWith3Points(repeat: Int): ScoreBoard {
        for (i in 0..repeat) {
            this.roll(3)
            this.roll(3)
        }
        return this
    }
    // TODO: 잘못된 점수 입력 시도 시 예외 발생
    // TODO: 스코어 보드가 각종 보너스 점수를 계산하여 최종점수를 출력할 수 있어야 함
}