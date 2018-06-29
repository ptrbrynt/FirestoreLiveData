package com.ptrbrynt.firestorelivedatasample

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ptrbrynt.firestorelivedata.ResourceObserver
import com.ptrbrynt.firestorelivedata.observeResource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.booksQuery.observeResource(this, object : ResourceObserver<List<Book>> {
            override fun onSuccess(data: List<Book>?) {
                text.text = data.orEmpty().toString()
            }

            override fun onError(throwable: Throwable?) {
                text.text = throwable?.localizedMessage ?: "An error occurred"
            }

            override fun onLoading() {
                text.text = "Loading..."
            }
        })

        button.setOnClickListener {
            viewModel.add()
        }

    }
}
