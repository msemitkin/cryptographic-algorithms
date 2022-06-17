import algoriths.cryptographic.decrypt
import algoriths.cryptographic.encrypt

fun main() {
    val textToEncrypt = "The Quick Brown fox jumps over 13 lazy dogs."
    val encryptionKey = "cryptii"

    val encrypted = encrypt(textToEncrypt, encryptionKey)
    println(encrypted)

    val decrypted = decrypt(encrypted, encryptionKey)
    println(decrypted)

}
