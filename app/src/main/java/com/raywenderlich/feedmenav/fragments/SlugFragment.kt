package com.raywenderlich.feedmenav.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pandora.bottomnavigator.BottomNavigator
import com.raywenderlich.feedmenav.R
import kotlinx.android.synthetic.main.fragment_slug.*

class SlugFragment : Fragment() {

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_slug, container, false)
  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iWantCake.setOnClickListener {
            val navigator = BottomNavigator.provide(activity!!)
            navigator.addFragment(CakeFragment())
        }
    }
}