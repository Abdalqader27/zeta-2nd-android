package com.Elkood.ling_en4.data.content

import com.Elkood.ling_en4.R
import com.Elkood.ling_en4.data.model.ReferenceTopic
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ReferenceContentTest {
    @Test
    fun eachTopicHasItsPinnedCount() {
        assertEquals(45, ReferenceContent.itemsFor(ReferenceTopic.VOCABULARY).size)
        assertEquals(25, ReferenceContent.itemsFor(ReferenceTopic.ABBREVIATIONS).size)
        assertEquals(20, ReferenceContent.itemsFor(ReferenceTopic.COMPOUND_NOUNS).size)
        assertEquals(20, ReferenceContent.itemsFor(ReferenceTopic.EXTENSIONS).size)
    }

    @Test
    fun everyColorTagIsOneOfTheTwoLegacyColors() {
        val allowed = setOf(R.color.colorPrimary, R.color.color4)
        ReferenceTopic.entries.forEach { topic ->
            ReferenceContent.itemsFor(topic).forEach { item ->
                assertTrue("bad colorRes in $topic", item.colorRes in allowed)
                assertTrue(item.title.isNotBlank())
            }
        }
    }

    @Test
    fun extraStringResolvesToTopicWithVocabularyDefault() {
        assertEquals(ReferenceTopic.ABBREVIATIONS, ReferenceContent.referenceTopicFromExtra("ABBREVIATIONS"))
        assertEquals(ReferenceTopic.EXTENSIONS, ReferenceContent.referenceTopicFromExtra("EXTENSIONS"))
        assertEquals(ReferenceTopic.VOCABULARY, ReferenceContent.referenceTopicFromExtra(null))
        assertEquals(ReferenceTopic.VOCABULARY, ReferenceContent.referenceTopicFromExtra("garbage"))
    }
}
