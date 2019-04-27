package bowling.score

import bowling.score.exception.EndedGameException
import bowling.score.exception.InvalidRollScoreException

class LastFrame : Frame {
    private val rollScoreArray: IntArray = IntArray(3)
    private var rollCount: Int = 0
    private var currentScore: Int = 0
    override fun setNextRollScore(rollScore: Int) {
        if (isEndedFrame())
            throw EndedGameException()
        if (rollScore > 10 || currentScore + rollScore > 10)
            throw InvalidRollScoreException()
        if (currentScore + rollScore == 10)
            currentScore = 0
        else
            currentScore += rollScore
        rollScoreArray[rollCount] = rollScore
        rollCount++
    }

    override fun getFrameScore(): Int {
        return rollScoreArray[0] + rollScoreArray[1] + rollScoreArray[2]
    }

    override fun getRollScore(rollNumber: Int): Int {
        return rollScoreArray[rollNumber]
    }

    override fun isEndedFrame(): Boolean {
        if (rollCount > 2)
            return true
        if (rollCount > 1 && rollScoreArray[0] + rollScoreArray[1] < 10)
            return true
        return false
    }
}