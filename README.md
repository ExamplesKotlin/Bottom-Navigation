# Bottom Navigation

### Using Navigation Component


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
        R.id.navigation_monster, R.id.navigation_slug, R.id.navigation_pizza, R.id.navigation_cake
      )
    )

    setupActionBarWithNavController(navController, appBarConfiguration)

    navView.setupWithNavController(navController)

  }
}
```

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

        feedMePizza.setOnClickListener {
            findNavController().navigate(R.id.navigation_pizza,null, option)
        }
    }
}
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


📄 mobile_navigation

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


