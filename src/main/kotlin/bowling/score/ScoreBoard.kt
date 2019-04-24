package bowling.score

class ScoreBoard {
    val frames = ArrayList<Frame>()

    fun roll(score: Int) {
    }

    // TODO: roll에 적용될 프레임, 10프레임 이후에는 증가하지 않음
    fun getCurrentFrameNumber(): Int {
        return 0
    }

    fun getGameStatus(): GameStatus {
        return GameStatus.COMPLETED
    }

    fun getTotalScore(): Int {
        return 0
    }
}