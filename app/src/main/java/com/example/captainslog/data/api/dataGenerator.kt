package com.example.captainslog.data.api

import java.util.UUID
import io.github.serpro69.kfaker.Faker


/**
 * A singleton generator for creating fake test data
 */
object FakeDataFactory {
    private val faker = Faker()
    private val usedIds = mutableSetOf<String>()

    private fun uniqueId(): String {
        var id: String
        do {
            id = UUID.randomUUID().toString()
        } while (!usedIds.add(id))
        return id
    }

    fun createUser(): UserDto {
        return UserDto(
            id = uniqueId(),
            displayName = faker.name.name()
        )
    }

    fun createSentence(): String {
        return faker.quote.yoda()
    }

    fun createNote(): NoteDto {
        return testNote(
            id = uniqueId(),
            transcript = createSentence()
        )
    }
}
