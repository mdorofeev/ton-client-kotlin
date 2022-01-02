package ee.nx01.tonclient.abi

import ee.nx01.tonclient.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.math.BigDecimal

class AbiModuleTest : StringSpec({

    "Should be able create encoded message" {
        val client = TonClient()

        val params = ParamsOfEncodeMessage(
            abi = TonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json"),
            address = "0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3",
            callSet = CallSet(
                "submitTransaction",
                input = mapOf(
                    "dest" to "0:ee946898dee44b9b7d4ed452fae4dba773ec339974b2e75223e868214ac01dfe",
                    "value" to TonUtils.convertToken(BigDecimal(0.1)),
                    "bounce" to false,
                    "allBalance" to false,
                    "payload" to ""
                ),
                header = null
            ),
            signer = Signer(
                keys = KeyPair(
                    "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca",
                    "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c0"
                )
            )
        )

        val response = client.abi.encodeMessage(params)

        response shouldNotBe null
        response.address shouldBe "0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3"
    }

    "Should be able create encoded body" {
        val client = TonClient()

        val params = ParamsOfEncodeMessageBody(
            abi = TonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json"),
            callSet = CallSet(
                "submitTransaction",
                input = mapOf(
                    "dest" to "0:ee946898dee44b9b7d4ed452fae4dba773ec339974b2e75223e868214ac01dfe",
                    "value" to TonUtils.convertToken(BigDecimal(0.1)),
                    "bounce" to false,
                    "allBalance" to false,
                    "payload" to ""
                ),
                header = null
            ),
            signer = Signer(
                type = SignerType.External,
                publicKey = "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca"
            )
        )

        val response = client.abi.encodeMessageBody(params)

        response shouldNotBe null
        response.body shouldNotBe null
        response.dataToSign shouldNotBe null

    }


    "Should be able encode internal message" {
        val client = TonClient()

        val params = ParamsOfEncodeInternalMessage(
            abi = TonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json"),
            address = "0:ee946898dee44b9b7d4ed452fae4dba773ec339974b2e75223e868214ac01dfe",
            callSet = CallSet(
                "submitTransaction",
                input = mapOf(
                    "dest" to "0:ee946898dee44b9b7d4ed452fae4dba773ec339974b2e75223e868214ac01dfe",
                    "value" to TonUtils.convertToken(BigDecimal(0.1)),
                    "bounce" to false,
                    "allBalance" to false,
                    "payload" to ""
                ),
                header = null
            ),
            value = TonUtils.convertToken(BigDecimal.ONE).toString()
        )

        val response = client.abi.encodeInternalMessage(params)

        response shouldNotBe null
        response.message shouldNotBe  null

    }

    "Should be able decode message body" {
        val client = TonClient()

        val params = ParamsOfDecodeMessageBody(
            abi = TonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json"),
            body = "te6ccgEBAwEAqwAB4cW+aLMt1Ud3Gi0X7Nh9so6RsaLyHo3kU0wJoUeu5K6ZpHtEQe1TtZVV+Ys1ccX3kqgr7Oeaa10ejT746FMySQTfvNk0CvfSJpWcUVT3WYmBmbVLUsDxavWzmLsf/vAosoAAAF1gqYlAl+eX+8THYLNgAQFjgB3SjRMb3Ilzb6nail9cm3TufYZzLpZc6kR9DQQpWAO/wAAAAAAAAAAAAAAAAL68IAQCAAA=",
            false
        )

        val response = client.abi.decodeMessageBody(params)

        response shouldNotBe null

    }

    "Should be able encode account" {
        val client = TonClient()

        val message =
            "te6ccgECFwEAA2gAAqeIAAt9aqvShfTon7Lei1PVOhUEkEEZQkhDKPgNyzeTL6YSEZTHxAj/Hd67jWQF7peccWoU/dbMCBJBB6YdPCVZcJlJkAAAF0ZyXLg19VzGRotV8/gGAQEBwAICA88gBQMBAd4EAAPQIABB2mPiBH+O713GsgL3S844tQp+62YECSCD0w6eEqy4TKTMAib/APSkICLAAZL0oOGK7VNYMPShCQcBCvSkIPShCAAAAgEgDAoByP9/Ie1E0CDXScIBjhDT/9M/0wDRf/hh+Gb4Y/hijhj0BXABgED0DvK91wv/+GJw+GNw+GZ/+GHi0wABjh2BAgDXGCD5AQHTAAGU0/8DAZMC+ELiIPhl+RDyqJXTAAHyeuLTPwELAGqOHvhDIbkgnzAg+COBA+iogggbd0Cgud6S+GPggDTyNNjTHwH4I7zyudMfAfAB+EdukvI83gIBIBINAgEgDw4AvbqLVfP/hBbo417UTQINdJwgGOENP/0z/TANF/+GH4Zvhj+GKOGPQFcAGAQPQO8r3XC//4YnD4Y3D4Zn/4YeLe+Ebyc3H4ZtH4APhCyMv/+EPPCz/4Rs8LAMntVH/4Z4AgEgERAA5biABrW/CC3Rwn2omhp/+mf6YBov/ww/DN8Mfwxb30gyupo6H0gb+j8IpA3SRg4b3whXXlwMnwAZGT9ghBkZ8KEZ0aCBAfQAAAAAAAAAAAAAAAAACBni2TAgEB9gBh8IWRl//wh54Wf/CNnhYBk9qo//DPAAxbmTwqLfCC3Rwn2omhp/+mf6YBov/ww/DN8Mfwxb2uG/8rqaOhp/+/o/ABkRe4AAAAAAAAAAAAAAAAIZ4tnwOfI48sYvRDnhf/kuP2AGHwhZGX//CHnhZ/8I2eFgGT2qj/8M8AIBSBYTAQm4t8WCUBQB/PhBbo4T7UTQ0//TP9MA0X/4Yfhm+GP4Yt7XDf+V1NHQ0//f0fgAyIvcAAAAAAAAAAAAAAAAEM8Wz4HPkceWMXohzwv/yXH7AMiL3AAAAAAAAAAAAAAAABDPFs+Bz5JW+LBKIc8L/8lx+wAw+ELIy//4Q88LP/hGzwsAye1UfxUABPhnAHLccCLQ1gIx0gAw3CHHAJLyO+Ah1w0fkvI84VMRkvI74cEEIoIQ/////byxkvI84AHwAfhHbpLyPN4="

        val params = ParamsOfEncodeAccount(stateInit = StateInitSource(source = MessageSource(message = message)))

        val response = client.abi.encodeAccount(params)

        response shouldNotBe null

    }


    "Should be able attach signature to message" {
        val client = TonClient()

        val abi = TonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json")

        val params = ParamsOfEncodeMessage(
            abi,
            address = "0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3",
            callSet = CallSet(
                "submitTransaction",
                input = mapOf(
                    "dest" to "0:ee946898dee44b9b7d4ed452fae4dba773ec339974b2e75223e868214ac01dfe",
                    "value" to TonUtils.convertToken(BigDecimal(0.1)),
                    "bounce" to false,
                    "allBalance" to false,
                    "payload" to ""
                ),
                header = null
            ),
            signer = Signer(
                keys = KeyPair(
                    "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca",
                    "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c0"
                )
            )
        )

        val response = client.abi.encodeMessage(params)

        response shouldNotBe null
        response.address shouldBe "0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3"

        val signatureResponse = client.abi.attachSignatureToMessageBody(
            ParamsOfAttachSignatureToMessageBody(
                abi,
                "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca",
                response.message,
                "xxx"
            )
        )

        signatureResponse.body shouldNotBe null
    }

    "Should be able decode message" {
        val client = TonClient()

        val message =
            "te6ccgEBBAEA0QABRYgAIOUk2QkCZir6x9GC0jec83d6iyaPfI+1cKptIHEpXWYMAQHh9w7spN3GKgAeE8SSmvi/Q6Scw7SVwnsVRjvNxXPh1E/qDF1GTgGA/LZwnQOlAYVq/9oxrJuv4wPCZA+XfyuYB1+82TQK99ImlZxRVPdZiYGZtUtSwPFq9bOYux/+8CiygAAAXVAHUH8X41XhBMdgs2ACAWOAHdKNExvciXNvqdqKX1ybdO59hnMullzqRH0NBClYA7/AAAAAAAAAAAAAAAAAvrwgBAMAAA=="

        val abi = TonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json")

        val response = client.abi.decodeMessage(ParamsOfDecodeMessage(abi, message))

        response shouldNotBe null
        response.bodyType shouldBe MessageBodyType.Input
        response.name shouldBe "submitTransaction"

    }

    "Should be exception on wrong message" {
        val client = TonClient()

        val message = "te6ccgEBBAEA0QABRYg=="

        val abi = TonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json")

        val exception = shouldThrow<TonClientException> {
            client.abi.decodeMessage(ParamsOfDecodeMessage(abi, message))
        }

        exception.tonClientError.code shouldBe TonClientErrorCode.InvalidMessage

    }


    "Should be able updates initial account data " {
        val client = TonClient()

        val response = client.abi.updateInitialData(ParamsOfUpdateInitialData(data = "te6ccgEBBwEARwABAcABAgPPoAQCAQFIAwAWc29tZSBzdHJpbmcCASAGBQADHuAAQQiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIoA=="))

        response shouldNotBe null

    }

    "Should be able decode initial account data " {
        val client = TonClient()

        val response = client.abi.decodeInitialData(ParamsOfDecodeInitialData(data = "te6ccgEBBwEARwABAcABAgPPoAQCAQFIAwAWc29tZSBzdHJpbmcCASAGBQADHuAAQQiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIoA=="))

        response shouldNotBe null

    }

    "Should be able encode initial account data " {
        val client = TonClient()

        val abi = TonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json")

        val response = client.abi.encodeInitialData(ParamsOfEncodeInitialData(initialData = mapOf("test" to "test"), abi = abi, initialPubkey = TestConstants.WALLET_PUBLIC))

        response shouldNotBe null

    }

})