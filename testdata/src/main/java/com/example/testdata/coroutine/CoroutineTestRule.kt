package com.example.testdata.coroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement


@ExperimentalCoroutinesApi
class CoroutineTestRule(
    val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestRule, CoroutineScope by TestScope(dispatcher) {

    /**
     * Use testDispatcherProvider when your view model has a dependency on coroutine [CoroutinesDispatcherProvider]
     */
    val testDispatcherProvider = TestDispatcherProviderFactory().create(dispatcher)

    fun runTest(block: suspend TestScope.() -> Unit) {
        runTest(dispatcher) {
            block()
        }
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {

                Dispatchers.setMain(dispatcher)

                // everything above this happens before the test
                base.evaluate()
                // everything below this happens after the test

                Dispatchers.resetMain()
            }
        }
    }
}
