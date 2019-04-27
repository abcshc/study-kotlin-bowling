package bowling.score

class ScoreBoard {
    private val frames = ArrayList<Frame>()
    // TODO: roll에 적용될 프레임, 시작은 1, 10프레임 이후에는 증가하지 않음
    var currentFrameNumber = 1
        private set

    fun roll(score: Int) {
    }


    fun getGameStatus(): GameStatus {
        return GameStatus.COMPLETED
    }

    fun getTotalScore(): Int {
        return 0
    }

    private fun getCurrentFrame(): Frame {
        return frames[currentFrameNumber - 1]
    }

}