package bowling.game

import bowling.lane.Lane
import bowling.score.ScoreBoard

// 멀티플레이 고려사항
// 1레인 플레이, 2레인 플레이
// 게임 플레이 방식에 따라 순서 정하기

// TODO: 스코어 보드에서 못하지만 게임 안에서 해야되는 일들을 생각해보기
// 0. 싱글플레이는 필요 없어보임 게임 안에서 플레이어 수로 진행
// 1. 레인이 필요함
// 2. 플레이어를 배분할 게임이 필요함.
// 3. 플레이어가 정해지면 레인을 배분함.
// 4. 플레이어 수만큼 스코어 보드 생성
class MultipleGame(numberOfLane: Int, val numberOfPlayer: Int) {
    val laneList: List<Lane> = if (numberOfLane == 1) listOf(Lane()) else listOf(Lane(), Lane())
    val scoreBoardList = ArrayList<ScoreBoard>()
    init {
        for (i in 0..numberOfPlayer)
            scoreBoardList.add(ScoreBoard())
    }
}