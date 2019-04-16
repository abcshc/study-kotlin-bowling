package bowling.score

import bowling.score.exception.InvalidRollCountException
import bowling.score.exception.InvalidRollScoreException

class DefaultFrame : Frame {
    private val rollScoreArray: IntArray = IntArray(2)
    private var rollCount: Int = 0
    override fun getFrameScore(): Int {
        return rollScoreArray[0] + rollScoreArray[1]
    }

    override fun getRollScore(rollNumber: Int): Int {
        return rollScoreArray[rollNumber]
    }

    override fun setNextRollScore(rollScore: Int) {
        if (rollCount > 1)
            throw InvalidRollCountException()
        if(rollScoreArray[0] + rollScore > 10)
            throw InvalidRollScoreException()
        rollScoreArray[rollCount] = rollScore
        rollCount++
        if(rollScore == 10 && rollCount == 1)
            rollCount++
    }
}