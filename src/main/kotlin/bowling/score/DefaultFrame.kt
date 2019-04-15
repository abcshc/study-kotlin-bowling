package bowling.score

import bowling.score.exception.InvalidRollCountException

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
        try {
            if (rollCount > 1)
                throw InvalidRollCountException()
            rollScoreArray[rollCount] = rollScore
            rollCount++
        } catch (exception: InvalidRollCountException) {
            print("invalid roll count exception")
        }
    }
}