package com.raywenderlich.feedmenav

import MonsterFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pandora.bottomnavigator.BottomNavigator
import com.raywenderlich.feedmenav.fragments.SlugFragment

class MainActivity : AppCompatActivity() {

  private lateinit var navigator: BottomNavigator
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    navigator = BottomNavigator.onCreate(
      fragmentContainer = R.id.fragment_container,
      bottomNavigationView = findViewById(R.id.nav_view),
      rootFragmentsFactory = mapOf(
        R.id.navigation_monster to { MonsterFragment() },
        R.id.navigation_slug to { SlugFragment() }
      ),
      defaultTab = R.id.navigation_monster,
      activity = this
    )
  }

  override fun onBackPressed() {
    if (!navigator.pop())
    super.onBackPressed()
  }
}
