package bowling.game

import org.junit.Test

class MultipleGameTest {
    @Test
    fun `2명 플레이어 게임 진행 1레인 게임진행`() {
        val game = MultipleGame(1, 2)
        game.roll(3)
        game.roll(3)
    }
    @Test
    fun `2명 플레이어 게임 진행 2레인 게임진행`() {
    }
}