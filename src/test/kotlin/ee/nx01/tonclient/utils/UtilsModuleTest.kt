package ee.nx01.tonclient.utils

import ee.nx01.tonclient.TestConstants
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.TonClientErrorCode
import ee.nx01.tonclient.TonClientException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import java.util.*

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

    "Should be able calculate storage fee" {
        val client = TonClient()

        val boc = client.net.accounts.getAccount(TestConstants.WALLET_ADDRESS)?.boc ?: error("Account not found")
        val fee = client.utils.calcStorageFee(boc, 24 * 60 * 60 * 30 * 12)

        fee shouldBeGreaterThan 0
    }

    "Should be able compress data" {
        val client = TonClient()

        val response = client.utils.compressZstd(Base64.getEncoder().encodeToString("Test string".encodeToByteArray()))

        response shouldBe "KLUv/QBYWQAAVGVzdCBzdHJpbmc="
    }

    "Should be able decompress data" {
        val client = TonClient()

        val response = client.utils.decompressZstd("KLUv/QBYWQAAVGVzdCBzdHJpbmc=")

        String(Base64.getDecoder().decode(response)) shouldBe "Test string"
    }

    "Should be able get address type in Hex" {
        val client = TonClient()

        val response = client.utils.getAddressType(ParamsOfGetAddressType("0:919db8e740d50bf349df2eea03fa30c385d846b991ff5542e67098ee833fc7f7"))

        response.addressType shouldBe AccountAddressType.Hex
    }

    "Should be able get address type in base 64" {
        val client = TonClient()

        val response = client.utils.getAddressType(ParamsOfGetAddressType("EQCRnbjnQNUL80nfLuoD+jDDhdhGuZH/VULmcJjugz/H9wam"))

        response.addressType shouldBe AccountAddressType.Base64
    }
})