package ee.nx01.tonclient

object TestConstants {
    val WALLET_PHRASE = "defense talent layer admit setup virus deer buffalo timber ethics symbol cover"
    val WALLET_ADDRESS = "0:7f458ae01e28573a181e2227dc77710d6421d4e103fdd3e023200aa4bce83950"
    val MESSAGE_ID = "3bb0b9c0d883c554ea072d5c7baae1d95b4e1f8648d4618e427ac478fccde2b7"
    val BLOCK_ID = 461728f
    val TRANSACTION_ID = "888454fa9327e976a62f9e4213a44a683df541486beeee8e9a360bf9bced34b5"
    val KEY_BLOCK_ID = "ca4d47f585e5cefd90ba91ab3fed22d0ffd0bfa21f7c0f530bb7d381df8db7c5"
    val WALLET_SECRET = "db16e21ee91b5064f6e31d9a2fb4771ce7f8acf14fe6d6ffade8ffcbeec31d69"
    val WALLET_PUBLIC = "335c317bfef32545749a665bfec97f692fdc107d0867a1a1e1b2d2906b40d24c"
    val CONFIG = TonClientConfig(NetworkConfig(endpoints = listOf("https://devnet.evercloud.dev/00ce8109d59745d9b0ee5207f1ebb46b/graphql")))
    val CONFIG_MAIN = TonClientConfig(NetworkConfig(endpoints = listOf("https://mainnet.evercloud.dev/00ce8109d59745d9b0ee5207f1ebb46b/graphql")))

}