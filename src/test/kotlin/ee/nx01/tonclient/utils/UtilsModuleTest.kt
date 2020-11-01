package ee.nx01.tonclient.utils

import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.TonClientErrorCode
import ee.nx01.tonclient.TonClientException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class UtilsModuleTest : StringSpec({

    "Should be able convert address" {
        val client = TonClient()

        val accountId = "fcb91a3a3816d0f7b8c2c76108b8a9bc5a6b7a55bd79f8ab101c52db29232260"
        val hex = "-1:fcb91a3a3816d0f7b8c2c76108b8a9bc5a6b7a55bd79f8ab101c52db29232260"
        val hexWorkchain0 = "0:fcb91a3a3816d0f7b8c2c76108b8a9bc5a6b7a55bd79f8ab101c52db29232260"
        val base64 = "Uf/8uRo6OBbQ97jCx2EIuKm8Wmt6Vb15+KsQHFLbKSMiYG+9"
        val base64url = "kf_8uRo6OBbQ97jCx2EIuKm8Wmt6Vb15-KsQHFLbKSMiYIny"

        client.utils.convertAddress(accountId, AddressStringFormat(AddressType.Hex)) shouldBe hexWorkchain0
        client.utils.convertAddress(hexWorkchain0, AddressStringFormat(AddressType.AccountId)) shouldBe accountId
        client.utils.convertAddress(
            hex, AddressStringFormat(
                AddressType.Base64,
                url = false,
                test = false,
                bounce = false
            )
        ) shouldBe base64
        client.utils.convertAddress(
            base64,
            AddressStringFormat(AddressType.Base64, url = true, test = true, bounce = true)
        ) shouldBe base64url

    }

    "Should be get error on wrong address" {
        val client = TonClient()

        val exception = shouldThrow<TonClientException> {
            client.utils.convertAddress("accountId", AddressStringFormat(AddressType.Hex))
        }

        exception.tonClientError.code shouldBe TonClientErrorCode.InvalidAddress

    }
})