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

    // TODO: 점수 입력을 10프레임까지 정상적으로 입력할 수 있도록 함
    @Test
    fun `정상적인 롤 스코어 입력 프레임 변경`() {
        scoreBoard.roll(5)
        scoreBoard.roll(3)
        scoreBoard.roll(3)

        assertEquals(2, scoreBoard.getCurrentFrameNumber())
    }

    // TODO: extension이 되어야 하는 함수로 보임 수정하기
    private fun repeatableRoll2TimesWith3Points(scoreBoard: ScoreBoard, repeat: Int): ScoreBoard {
        for (i in 0..repeat) {
            scoreBoard.roll(3)
            scoreBoard.roll(3)
        }
        return scoreBoard
    }
    // TODO: 잘못된 점수 입력 시도 시 예외 발생
    // TODO: 스코어 보드가 각종 보너스 점수를 계산하여 최종점수를 출력할 수 있어야 함
}