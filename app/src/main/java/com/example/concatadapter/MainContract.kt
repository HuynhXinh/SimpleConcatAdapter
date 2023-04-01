package com.example.concatadapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ajalt.timberkt.Timber
import kotlinx.coroutines.*
import kotlin.random.Random

interface MainContract {
    abstract class MainViewModel : ViewModel() {
        abstract val notification: LiveData<ItemNotification>

        abstract val accounts: LiveData<List<ItemMultipleType>>

        abstract val netPositions: LiveData<List<ItemMultipleType>>

        abstract fun initialize()
    }
}

class ViewModelImpl : MainContract.MainViewModel() {
    private var _notification = MutableLiveData<ItemNotification>()
    override val notification: LiveData<ItemNotification>
        get() = _notification

    private var _accounts = MutableLiveData<List<ItemMultipleType>>()
    override val accounts: LiveData<List<ItemMultipleType>>
        get() = _accounts

    private val _netPositions = MutableLiveData<List<ItemMultipleType>>()
    override val netPositions: LiveData<List<ItemMultipleType>>
        get() = _netPositions

    override fun initialize() {
        loadNotification()
        loadAccounts()
        loadNetPositions()
    }

    private fun loadNetPositions() {
        viewModelScope.launch {
            _netPositions.value = fetchNetPositions()
        }
    }

    private suspend fun fetchNetPositions(): List<ItemMultipleType> {
        Timber.d { "fetchNetPositions()" }
        return withContext(Dispatchers.IO) {
            delay(1000)
            listOf(
                ItemHeader(title = "Net Position", enableMore = true, itemViewType = 0),

                ItemNetPosition(
                    total = "Total credit balance",
                    value = "-$66.99",
                    itemViewType = 1
                ),
                ItemNetPosition(
                    total = "Total credit balance",
                    value = "-$66.99",
                    itemViewType = 1
                ),
                ItemNetPosition(
                    total = "Total credit balance",
                    value = "-$66.99",
                    itemViewType = 1
                ),
                ItemNetPosition(
                    total = "Total credit balance",
                    value = "-$66.99",
                    itemViewType = 1
                ),

                ItemNetPositionDes(desc = "Total reflect the current and/or investment balances shown above (other than the Traveller card)")
            )
        }
    }

    private fun loadAccounts() {
        viewModelScope.launch {
            val accounts = fetchAccounts()
            _accounts.value = accounts
            _accounts.value = loadBalances(accounts)
        }
    }

    private suspend fun loadBalances(accounts: List<ItemMultipleType>): List<ItemMultipleType> {
        Timber.d { "loadBalances()" }
        return withContext(Dispatchers.IO) {
            accounts.map {
                async {
                    if (it is ItemAccount) {
                        getBalance(it)
                    } else {
                        it
                    }
                }
            }.awaitAll()
        }
    }

    private suspend fun getBalance(account: ItemAccount): ItemAccount {
        val delay = Random.nextLong(4000) + 1000
        Timber.d { "Start getBalance($account) with delay: $delay" }
        delay(delay)
        Timber.d { "Got result after delay: $delay" }
        return account.copy(
            availableBalance = ItemAccount.Balance.Available(
                name = "Available balance",
                value = "+$2000.99"
            ),
            currentBalance = ItemAccount.Balance.Available(
                name = "Available balance",
                value = "-$2000.99"
            ),
        )
    }

    private suspend fun fetchAccounts(): List<ItemMultipleType> {
        Timber.d { "fetchAccounts()" }
        return withContext(Dispatchers.IO) {
            delay(1500)
            listOf(
                ItemHeader(title = "Accounts", enableMore = true, itemViewType = 0),

                ItemAccount(
                    icon = R.drawable.ic_card_24,
                    name = "Credit card",
                    desc = "Card ending 1234",
                ),

                ItemAccount(
                    icon = R.drawable.ic_card_24,
                    name = "Personal Account",
                    desc = "Personal Account 989",
                ),

                ItemAccount(
                    icon = R.drawable.ic_card_24,
                    name = "Home loan",
                    desc = "Home loan 999",
                ),

                ItemAccount(
                    icon = R.drawable.ic_card_24,
                    name = "Transaction Account",
                    desc = "Transaction Account 985769",
                ),

                ItemFooter
            )
        }
    }

    private fun loadNotification() {
        viewModelScope.launch {
            _notification.value = fetchNotification()
        }
    }

    private suspend fun fetchNotification(): ItemNotification {
        Timber.d { "fetchNotification()" }
        return withContext(Dispatchers.IO) {
            delay(5000)
            ItemNotification()
        }
    }
}