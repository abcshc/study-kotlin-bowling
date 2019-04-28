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

    // TODO: 리펙토링 해보기
    fun getTotalScore(): Int {
        var totalScore = 0
        var spareCount = 1
        var strikeCount = 1
        frames.forEachIndexed { index, it ->
            //마지막 프레임 상황만 먼저 처리
            if (index == frames.lastIndex) {
                for (i in 0..2) {
                    if(i == 2) totalScore += it.getRollScore(i)
                    else {
                        if (getBonusWeight(spareCount, strikeCount) > 1) {
                            totalScore += it.getRollScore(i) * getBonusWeight(spareCount, strikeCount)
                            if (spareCount > 1) spareCount--
                            if (strikeCount > 1) strikeCount--
                        } else {
                            totalScore += it.getRollScore(i) *
                                    if (getBonusWeight(spareCount, strikeCount) > 3) 3
                                    else getBonusWeight(spareCount, strikeCount)
                        }
                    }
                }
            } else {
                //이후 일반 프레임들 구현
                if (getBonusWeight(spareCount, strikeCount) > 1) {
                    totalScore += it.getRollScore(0) *
                            if (getBonusWeight(spareCount, strikeCount) > 3) 3
                            else getBonusWeight(spareCount, strikeCount)
                    if (spareCount > 1) spareCount--
                    if (strikeCount > 1) strikeCount--
                    totalScore += it.getRollScore(1) *
                            if (getBonusWeight(spareCount, strikeCount) > 3) 3
                            else getBonusWeight(spareCount, strikeCount)
                } else {
                    totalScore += it.getFrameScore()
                }

                if (it.getRollScore(0) == 10 && strikeCount > 1) {
                    strikeCount += 2
                    spareCount++
                } else if (it.getRollScore(0) == 10) {
                    strikeCount += 2
                } else if (it.getFrameScore() == 10)
                    spareCount++

            }
        }
        return totalScore
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

    private fun getBonusWeight(spareCount: Int, strikeCount: Int): Int {
        return if (spareCount > 1 && strikeCount > 1) 3 else if (spareCount == 1 && strikeCount == 1) 1 else 2
    }
}