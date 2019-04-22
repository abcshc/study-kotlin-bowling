package bowling.score

import kotlin.test.BeforeTest

class ScoreBoardTest {
    private lateinit var scoreBoard: ScoreBoard

    @BeforeTest
    fun setup() {
        scoreBoard = ScoreBoard()
    }
    // TODO: 점수 입력을 10프레임까지 정상적으로 입력할 수 있도록 함
    // TODO: 잘못된 점수 입력 시도 시 예외 발생
    // TODO: 스코어 보드가 각종 보너스 점수를 계산하여 최종점수를 출력할 수 있어야 함
}