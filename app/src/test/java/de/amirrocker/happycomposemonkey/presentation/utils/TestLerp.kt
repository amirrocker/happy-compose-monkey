package de.amirrocker.happycomposemonkey.presentation.utils

import org.junit.Assert
import org.junit.Test

class TestLerp {

    @Test
    fun `test we receive values for the lerp`() {

        val lerp = lerp(1f, 3f, 0.1f)

        // TODO put in kluent
//        lerp shouldBe 1.2f

        Assert.assertTrue("Expect 1.2f but was $lerp", lerp == 1.2f)
    }
}