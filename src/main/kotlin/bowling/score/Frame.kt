package bowling.score

interface Frame {
    fun setNextRollScore(rollScore: Int)
    fun getFrameScore(): Int
    fun getRollScore(rollNumber: Int): Int
    fun isEndedFrame(): Boolean
}