package algoriths.cryptographic

const val ALPHABET = "abcdefghijklmnopqrstuvwxyz"

fun encrypt(inputText: String, key: String): String {
    var index = 0;
    val words = inputText.split(" ")
    val encryptedWords = words.map { word ->
        word.map { character ->
            character.encrypt(key[index++ % key.length])
        }.joinToString(separator = "")
    }
    return encryptedWords.joinToString(separator = " ")
}

fun Char.alphabetCode() = ALPHABET.indexOf(this)

fun Char.encrypt(key: Char) = ALPHABET[(alphabetCode() + key.alphabetCode()) % 26]