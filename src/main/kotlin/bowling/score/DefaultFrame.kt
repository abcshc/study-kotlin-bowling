package bowling.score

import bowling.score.exception.InvalidRollCountException

class DefaultFrame : Frame {
    private val rollScoreArray: IntArray = IntArray(2)
    override fun getFrameScore(): Int {
        return rollScoreArray[0] + rollScoreArray[1]
    }

    override fun getRollScore(rollNumber: Int): Int {
        return rollScoreArray[rollNumber]
    }

    override fun setNextRollScore(rollScore: Int) {
        try {
            rollScoreArray[checkRollNumber()] = rollScore
        } catch (exception: InvalidRollCountException) {
            print("invalid roll count exception")
        }
    }

    private fun checkRollNumber(): Int {
        if (rollScoreArray[0] == null && rollScoreArray[1] == null)
            return 0
        if (rollScoreArray[0] != null && rollScoreArray[1] == null)
            return 1
        throw InvalidRollCountException()
    }
}