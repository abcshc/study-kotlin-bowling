package bowling.score

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DefaultFrameTest{
    lateinit var defaultFrame: Frame

    @BeforeTest
    fun setup(){
        defaultFrame = DefaultFrame()
    }

    @Test
    fun `정상적인 롤 스코어 입력 (2번 입력)`(){
        defaultFrame.setNextRollScore(5)
        assertEquals(5, defaultFrame.getRollScore(0))
        assertEquals(5, defaultFrame.getFrameScore())
        defaultFrame.setNextRollScore(3)
        assertEquals(3, defaultFrame.getRollScore(1))
        assertEquals(8, defaultFrame.getFrameScore())
    }
}