package bowling.score

import bowling.score.exception.EndedGameException

class ScoreBoard {
    private val frames = ArrayList<Frame>()

    init {
        for (i in 0..8)
            frames.add(DefaultFrame())

        frames.add(LastFrame())
    }

    fun roll(score: Int) {
        frames[getCurrentFrameNumber()].setNextRollScore(score)
    }


    fun getGameStatus(): GameStatus {
        return if (!frames[0].isStartedFrame())
            GameStatus.READY
        else if (frames[9].isEndedFrame())
            GameStatus.COMPLETED
        else
            GameStatus.PLAYING
    }

    fun getTotalScore(): Int {
        return 0
    }

    private fun getCurrentFrameNumber(): Int {
        for (i in 0 until frames.size) {
            if (!frames[i].isEndedFrame())
                return i
        }

        if (frames[9].isEndedFrame())
            return 9
        else
            throw EndedGameException()
    }
}