package bowling.score

import bowling.score.exception.InvalidRollCountException
import bowling.score.exception.InvalidRollScoreException

class LastFrame : Frame {
    private val rollScoreArray: IntArray = IntArray(3)
    private var rollCount: Int = 0
    private var currentScore: Int = 0
    override fun setNextRollScore(rollScore: Int) {
        if (rollScore > 10)
            throw InvalidRollScoreException()
        if (rollCount > 2)
            throw InvalidRollCountException()
        if (rollCount > 1 && rollScoreArray[0] + rollScoreArray[1] < 10)
            throw InvalidRollCountException()
        if (currentScore + rollScore > 10)
            throw InvalidRollCountException()
        if (currentScore + rollScore == 10)
            currentScore = 0
        rollScoreArray[rollCount] = rollScore
        currentScore += rollScore
        rollCount++
    }

    override fun getFrameScore(): Int {
        return rollScoreArray[0] + rollScoreArray[1] + rollScoreArray[2]
    }

    override fun getRollScore(rollNumber: Int): Int {
        return rollScoreArray[rollNumber]
    }
}