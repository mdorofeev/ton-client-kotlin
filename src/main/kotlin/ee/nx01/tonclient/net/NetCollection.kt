package ee.nx01.tonclient.net

interface NetCollection<C, F> {
    suspend fun query(filter: F, result: String): List<C>
    suspend fun subscribe(filter: F, result: String, onResult: (result: C) -> Unit): Long
}