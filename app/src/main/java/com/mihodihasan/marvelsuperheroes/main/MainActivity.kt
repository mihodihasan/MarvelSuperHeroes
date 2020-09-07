package com.mihodihasan.marvelsuperheroes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mihodihasan.marvelsuperheroes.R
import com.mihodihasan.marvelsuperheroes.main.model.Hero
import com.mihodihasan.marvelsuperheroes.utils.hide
import com.mihodihasan.marvelsuperheroes.utils.show
import com.mihodihasan.marvelsuperheroes.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        presenter.start()
    }

    override fun showTopBarLoading() {
        progressBar.show()
    }

    override fun showMainContentViewLoading() {
    }

    override fun hideTopBarLoading() {
        progressBar.hide()
    }

    override fun hideMainContentViewLoading() {
    }

    override fun displayHeroesAvatar(heroList: MutableList<Hero>) {
    }

    override fun displayComicsList(heroList: MutableList<Hero>) {
    }

    override fun displayErrorMessage(errMsg: String) {
        toast(errMsg)
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }
}