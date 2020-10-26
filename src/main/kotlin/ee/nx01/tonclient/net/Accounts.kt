package ee.nx01.tonclient.net

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.TonUtils
import ee.nx01.tonclient.types.AccountFilterInput
import ee.nx01.tonclient.types.StringFilterInput
import java.math.BigDecimal


class Accounts(private val net: NetModule): NetCollection<Account, AccountFilterInput> {

    override suspend fun query(filter: AccountFilterInput, result: String): List<Account> {
        val response = net.query(Query("accounts", filter, result))

        return JsonUtils.read<AccountResponse>(response).result
    }

    override suspend fun subscribe(filter: AccountFilterInput, result: String, onResult: (result: Account) -> Unit): Long {
        return net.subscribe(Query("accounts", filter, result)) {
            onResult(JsonUtils.read<AccountSubscriptionResponse>(it).result)
        }
    }

    suspend fun getAccount(account: String): Account? {
        val filter = AccountFilterInput(id = StringFilterInput(eq = account))
        return query(filter, "id acc_type boc code_hash data_hash balance last_paid").firstOrNull()
    }
}

data class Account(
    val id: String,
    @JsonProperty("acc_type") val accType: AccountType? = null,
    @JsonProperty("last_paid") val lastPaid: Long = 0,
    @JsonProperty("due_payment") val duePayment: Long = 0,
    @JsonProperty("last_trans_lt") val lastTransLt: Long = 0,
    @JsonProperty("balance_other") val balanceOther: Map<Int, Long>? = null,
    @JsonProperty("split_depth") val splitDepth: Int? = null,
    val tick: Boolean? = null,
    val code: String? = null,
    val data: String? = null,
    val proof: String? = null,
    val boc: String? = null,
    val code_hash: String? = null,
    val data_hash: String? = null,
    val balance: String? = null
){
    fun getBalance(): BigDecimal = TonUtils.convertHexToToken(balance ?: "0x0")
}


data class AccountResponse(val result: List<Account>)

data class AccountSubscriptionResponse(val result: Account)

enum class AccountType {
    UNINITIALIZED,
    ACTIVE,
    FROZEN;

    @JsonValue
    open fun toValue(): Int {
        return ordinal
    }
}