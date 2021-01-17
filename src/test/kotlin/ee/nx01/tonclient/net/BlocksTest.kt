package ee.nx01.tonclient.net

import ee.nx01.tonclient.TestConstants
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.types.BlockFilterInput
import ee.nx01.tonclient.types.FloatFilterInput
import ee.nx01.tonclient.types.IntFilterInput
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BlocksTest : StringSpec({
    "Get block from blockchain" {
        val client = TonClient()

        val filter = BlockFilterInput(
            seq_no = FloatFilterInput(eq = TestConstants.BLOCK_ID),
            workchain_id = IntFilterInput(eq = -1)
        )

        val blockList = client.net.blocks.query(
            filter,
            "global_id status"
        )

        blockList shouldNotBe null
        blockList[0].global_id shouldBe 42

    }

})