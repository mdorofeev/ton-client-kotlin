# TON-CLIENT-KOTLIN

TON-CLIENT-KOTLIN - is an async Kotlin bindings for TON Client Library 

## How to build

    ./gradlew build

## How to use

Add to build gradle:

```groovy
implementation "ee.nx01.tonclient:ton-client-kotlin:0.0.12"
```

## Supported OS 

Platform | Arch    
-------- |-----------
Windows  | x64 
Linux    | x64
Mac OS X | x64
Android  | arm64, armeabi-v7a

## Example

Examples can be found here `TODO`

##### Create key pair
```kotlin
        val client = TonClient()
        val keyPair = client.crypto.ed25519Keypair()
```
##### Process transaction with crystals

```kotlin
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
            signer = Signer(keys = KeyPair(
                "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca",
                "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c0"
            )
            )
        )

        val params = ParamsOfProcessMessage(message)
        val response = client.processing.processMessage(params)
```

## Related Links

- [TON OS docs](https://docs.ton.dev/)

