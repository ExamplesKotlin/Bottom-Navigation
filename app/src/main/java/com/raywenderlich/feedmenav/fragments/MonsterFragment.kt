import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pandora.bottomnavigator.BottomNavigator
import com.raywenderlich.feedmenav.R
import com.raywenderlich.feedmenav.fragments.PizzaFragment
import kotlinx.android.synthetic.main.fragment_monster.*

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