package com.example.concatadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.concatadapter.adapter.AccountAdapter
import com.example.concatadapter.adapter.NetPositionAdapter
import com.example.concatadapter.adapter.NotificationAdapter
import com.example.concatadapter.adapter.SpaceAdapter
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainContract.MainViewModel by lazy { ViewModelImpl() }

    private val notificationAdapter = NotificationAdapter()
    private val accountAdapter = AccountAdapter()
    private val netPositionAdapter = NetPositionAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(LoggerManager())

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val adapters = listOf(
            notificationAdapter,
            SpaceAdapter(16.toPx),
            accountAdapter,
            SpaceAdapter(16.toPx),
            netPositionAdapter
        )

        val concatAdapter = ConcatAdapter(adapters)

        recyclerView.adapter = concatAdapter


        mainViewModel.initialize()

        with(mainViewModel) {
            notification.observe(this@MainActivity) {
                notificationAdapter.submitList(listOf(it))
                recyclerView.scrollToPosition(0)
            }

            accounts.observe(this@MainActivity) {
                accountAdapter.submitList(it)
                recyclerView.scrollToPosition(0)
            }

            netPositions.observe(this@MainActivity) {
                netPositionAdapter.submitList(it)
                recyclerView.scrollToPosition(0)
            }
        }
    }

    class LoggerManager : Timber.DebugTree() {

        override fun createStackElementTag(element: StackTraceElement): String {
            val tag = super.createStackElementTag(element)
            val methodName = element.methodName
            val lineNumber = element.lineNumber
            return "$tag $methodName:$lineNumber"
        }
    }
}