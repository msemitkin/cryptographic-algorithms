package algoriths.cryptographic

import java.util.Collections.rotate

private const val ALPHABET = "abcdefghijklmnopqrstuvwxyz"
private val ENCRYPTION_MATRIX: List<List<Char>> = ALPHABET
    .mapIndexed { index, _ -> ALPHABET.toList().rotate(-1 * index) }

fun encrypt(textToEncrypt: String, encryptionKey: String): String {
    var index = 0
    val words = textToEncrypt.split(" ")
    val encryptedWords = words.map { word ->
        word.map { plainCharacter ->
            try {
                val encrypted = plainCharacter.encrypt(encryptionKey[index % encryptionKey.length])
                index++
                encrypted
            } catch (e: IllegalArgumentException) {
                plainCharacter
            }
        }.joinToString(separator = "")
    }
    return encryptedWords.joinToString(separator = " ")
}

fun decrypt(encryptedText: String, encryptionKey: String): String {
    var index = 0
    val words = encryptedText.split(" ")
    val decryptedWords = words.map { word ->
        word.map { character ->
            try {
                val decryptedCharacter = character.decrypt(encryptionKey[index % encryptionKey.length])
                index++
                decryptedCharacter
            } catch (e: IllegalArgumentException) {
                character
            }
        }.joinToString(separator = "")
    }
    return decryptedWords.joinToString(separator = " ")
}

private fun Char.encrypt(key: Char) =
    with(alphabetCode()) {
        if (this != -1) ENCRYPTION_MATRIX[this][key.alphabetCode()] else throw IllegalArgumentException()
    }

private fun Char.decrypt(key: Char) =
    with(key.alphabetCode()) {
        val line = ENCRYPTION_MATRIX[this]
        val indexOfRealCharacter = line.indexOf(this@decrypt)
        if (indexOfRealCharacter != -1) ALPHABET[indexOfRealCharacter] else throw IllegalArgumentException()
    }

private fun Char.alphabetCode() = ALPHABET.indexOf(this)


private fun <T> List<T>.rotate(distance: Int): List<T> {
    val list = ArrayList(this)
    rotate(list, distance)
    return list
}
