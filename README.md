# Bottom Navigation


### 1.- Set Up graddle:

gradle Project

```
buildscript {
  ext.kotlin_version = '1.3.72'
  ext.navigation_version = '2.3.2' // <<=
  repositories {
    google()
    ...
```

gradle Module

```

    implementation "androidx.navigation:navigation-fragment-ktx:$rootProject.navigation_version"
    implementation "androidx.navigation:navigation-ui-ktx:$rootProject.navigation_version"
```

### 2.- Using Navigation Component



📄 mobile_navigation.xml

```
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/mobile_navigation"
    app:startDestination="@id/navigation_monster">

    <fragment
        android:id="@+id/navigation_monster"
        android:name="MonsterFragment"
        android:label="@string/monster" />
    <fragment
        android:id="@+id/navigation_slug"
        android:name="com.raywenderlich.feedmenav.fragments.SlugFragment"
        android:label="@string/slug" >
        <action
            android:id="@+id/action_cake"
            app:destination="@id/navigation_cake"
            app:enterAnim="@anim/slide_in_right"
            app:exitAnim="@anim/slide_out_left"
            app:popEnterAnim="@anim/slide_in_left"
            app:popExitAnim="@anim/slide_out_right" />
    </fragment>
    <fragment
        android:id="@+id/navigation_pizza"
        android:name="com.raywenderlich.feedmenav.fragments.PizzaFragment"
        android:label="@string/pizza" />
    <fragment
        android:id="@+id/navigation_cake"
        android:name="com.raywenderlich.feedmenav.fragments.CakeFragment"
        android:label="@string/cake" />
</navigation>
```

Remember update name and label

### 3.- Make Menu

📄 bottom_nav_menu

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/navigation_monster"
        android:icon="@drawable/ic_pizza"
        android:title="@string/title_pizza"/>

    <item
        android:id="@+id/navigation_slug"
        android:icon="@drawable/ic_cake"
        android:title="@string/title_cake"/>
</menu>
```

### 4.- Make container and Bottom Navigation:

📄 activity_main.xml

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingTop="?attr/actionBarSize">

    <fragment
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:defaultNavHost="true"
        app:layout_constraintBottom_toTopOf="@+id/nav_view"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:navGraph="@navigation/mobile_navigation" />

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/nav_view"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="0dp"
        android:layout_marginEnd="0dp"
        android:background="?android:attr/windowBackground"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:menu="@menu/bottom_nav_menu" />


</androidx.constraintlayout.widget.ConstraintLayout>
```

### 5.- Conectar código con navigation

📄 MainActivity

```
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val navView: BottomNavigationView = findViewById(R.id.nav_view)

    val navController = findNavController(R.id.nav_host_fragment)
    val appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.navigation_monster, R.id.navigation_slug, R.id.navigation_pizza, R.id.navigation_cake // <<-  Here add destination continually
      )
    )

    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)

  }
}
```

First case with Animations

📄 MonsterFragment

```
class MonsterFragment : Fragment() {

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_monster, container, false)
  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val option = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }

        }

// Using Navigation 👇🏼
        feedMePizza.setOnClickListener {
            findNavController().navigate(R.id.navigation_pizza,null, option)
        }
    }
}
```

### 6.- Second case usgin Action

In mobile_navigation.xml

```
...
        <action
            android:id="@+id/action_cake"
            app:destination="@id/navigation_cake"
            app:enterAnim="@anim/slide_in_right"
            app:exitAnim="@anim/slide_out_left"
            app:popEnterAnim="@anim/slide_in_left"
            app:popExitAnim="@anim/slide_out_right" />
```

📄 SlugFragment

```
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

        iWantCake.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_cake, null))
    }
}
```





Way to pas to second View 

📄 PizzaFragment

```
class PizzaFragment : Fragment() {

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_pizza, container, false)
  }
}
```

📄 CakeFragment

```
class CakeFragment : Fragment() {

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_cake, container, false)
  }
}
```

#### Animations Files:

slide_in_left.xml

```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">

    <translate android:fromXDelta="-100%" android:toXDelta="0%"
               android:fromYDelta="0%" android:toYDelta="0%"
               android:duration="700"/>
</set>
```

slide_in_right.xml

```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">

    <translate android:fromXDelta="100%" android:toXDelta="0%"
               android:fromYDelta="0%" android:toYDelta="0%"
               android:duration="700"/>
</set>

```

slide_out_left.xml

```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">

    <translate android:fromXDelta="100%" android:toXDelta="0%"
               android:fromYDelta="0%" android:toYDelta="0%"
               android:duration="700"/>
</set>

```

slide_out_right.xml

```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">

    <translate android:fromXDelta="0%" android:toXDelta="100%"
               android:fromYDelta="0%" android:toYDelta="0%"
               android:duration="700"/>
</set>
```

## Use Multiple Back Stacks

#### 7 Add the new Library:

```
implementation 'com.pandora.bottomnavigator:bottom-navigator:1.8'
```

And syncronize

#### 8 Change Frame for FrameLayout in activity_main.xml

```
    <FrameLayout
        android:id="@+id/fragment_container"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintBottom_toTopOf="@+id/nav_view"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```


#### 9 - Update MainActivity

```
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
```

#### 10 - Updaye Monster Fragment

```
class MonsterFragment : Fragment() {

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_monster, container, false)
  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feedMePizza.setOnClickListener {
            val navigator = BottomNavigator.provide(activity!!)
            navigator.addFragment(PizzaFragment())
        }
    }
}
```

#### 11 - Update Slug Fragment

```
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
```

⭐️ Don't forget clean import library

#### 12 - Delete nav and clean dependencies








[https://engineering.pandora.com/announcing-bottom-navigator-64f6e426a6b1](https://engineering.pandora.com/announcing-bottom-navigator-64f6e426a6b1)



