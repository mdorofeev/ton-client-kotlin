# TON-CLIENT-KOTLIN

![Maven Central](https://img.shields.io/maven-central/v/ee.nx-01.tonclient/ton-client-kotlin)
![Main build and publish](https://github.com/mdorofeev/ton-client-kotlin/workflows/Main%20build%20and%20publish/badge.svg)

TON-CLIENT-KOTLIN - is an async Kotlin bindings for TON SDK/EVER SDK Library 

## How to build

    ./gradlew build

## How to use

Add to build.gradle:

```groovy
repositories {
    mavenCentral()
}
```

```groovy
implementation "ee.nx-01.tonclient:ton-client-kotlin:0.0.53"
```

## Supported OS 

| OS       | Arch               |
|----------|--------------------|
| Windows  | x64                |
| Linux    | x64                |
| Mac OS X | x64                |
| Android  | arm64, armeabi-v7a |

## Example

Examples can be found here: `src\test\kotlin\ee\nx01\tonclient`

##### Create key pair
```kotlin
import ee.nx01.tonclient.TonClient

suspend fun main() {
    val client = TonClient()
    val keyPair = client.crypto.ed25519Keypair()
}
```
##### Process transaction with EVER

```kotlin
import ee.nx01.tonclient.NetworkConfig
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.TonClientConfig
import ee.nx01.tonclient.TonUtils
import ee.nx01.tonclient.abi.CallSet
import ee.nx01.tonclient.abi.KeyPair
import ee.nx01.tonclient.abi.ParamsOfEncodeMessage
import ee.nx01.tonclient.abi.Signer
import ee.nx01.tonclient.process.ParamsOfProcessMessage
import ee.nx01.tonclient.process.ResultOfProcessMessage
import ee.nx01.tonclient.types.AccountFilterInput
import ee.nx01.tonclient.types.StringFilterInput

suspend fun main() {
    val client = TonClient()

    val message = ParamsOfEncodeMessage(
        abi = TonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json"),
        address = "0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3",
        callSet = CallSet(
            "submitTransaction",
            input = mapOf(
                "dest" to "0:fec160062b890ae304c9589357cb3a711fce91f2ca0d03852668de01a507671c",
                "value" to TonUtils.convertToken(BigDecimal(0.3)),
                "bounce" to false,
                "allBalance" to false,
                "payload" to ""
            )
        ),
        signer = Signer(
            keys = KeyPair(
                "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca",
                "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c0"
            )
        )
    )

    val params = ParamsOfProcessMessage(message)
    val response = client.processing.processMessage(params)
}
```

##### Find account in main network

```kotlin
import ee.nx01.tonclient.NetworkConfig
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.TonClientConfig
import ee.nx01.tonclient.types.AccountFilterInput
import ee.nx01.tonclient.types.StringFilterInput

suspend fun main() {
    val client =
        TonClient(TonClientConfig(NetworkConfig(endpoints = listOf("https://mainnet.evercloud.dev/replace_me/graphql"))))

    val accountList = client.net.accounts.query(
        AccountFilterInput(id = StringFilterInput(eq = "0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3")),
        "id acc_type boc last_paid balance"
    )
}
```

## Related Links
- [Android project example](https://github.com/mdorofeev/ton-client-kotlin-android-example)
- [EVER SDK docs](https://github.com/tonlabs/ever-sdk/blob/master/docs/reference/types-and-methods/modules.md)
- [EVER SDK repository](https://github.com/tonlabs/ever-sdk)

