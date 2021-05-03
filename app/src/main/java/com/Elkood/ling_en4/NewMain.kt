package com.Elkood.ling_en4

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.Elkood.ling_en4.Views.Home
import com.Elkood.ling_en4.Views.Profile.Profile
import com.Elkood.ling_en4.Views.Seach
import com.Elkood.ling_en4.Views.TheList
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class NewMain : AppCompatActivity() {
    private val  meowBottomNavigation: MeowBottomNavigation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_main)
        setDefaultFragment()
        setMeowBottomNavigation()
    }

    companion object {
        private const val ID_HOME = 1
        private const val ID_EXPLORE = 2
        private const val ID_MESSAGE = 3
        private const val ID_NOTIFICATION = 4
        private const val ID_ACCOUNT = 5
        var  `var`=-1;
        var SelectFragment: Fragment? = null

    }

    private fun setMeowBottomNavigation() {
        SelectFragment = Home.getInstance()
        //        bottom_navigation=findViewById(R.id.bottom_navigation);
//        bottom_navigation.setOnNavigationItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.home:
//                    SelectFragment = Home.getInstance();
//                    break;
//                case R.id.TheList:
//                    SelectFragment =  TheList.getInstance();
//                    break;
//                case R.id.search:
//                    SelectFragment = Seach.getInstance();
//                    break;
//                    case R.id.profile:
//                    SelectFragment = Profile.getInstance();;
//                default:
//                    break;
//            }
//            setFragments();
//return  true;
//        });

        meowBottomNavigation?.add(MeowBottomNavigation.Model(1, R.drawable.ic_user))
        meowBottomNavigation?.add(MeowBottomNavigation.Model(2, R.drawable.ic_student))
        meowBottomNavigation?.add(MeowBottomNavigation.Model(3, R.drawable.ic_search))
        meowBottomNavigation?.add(MeowBottomNavigation.Model(4, R.drawable.ic_home))
        meowBottomNavigation?.show(4, true)

        meowBottomNavigation?.setOnShowListener {
            // YOUR CODES
        }

        meowBottomNavigation?.setOnClickMenuListener {

            // YOUR CODES
        }
//        meowBottomNavigation.setOnShowListener { model: MeowBottomNavigation.Model? ->
//            val handler = Handler()
//            val r = Runnable { setFragments() }
//            handler.postDelayed(r, 200)
//            null
//        }
        meowBottomNavigation?.setOnClickMenuListener { model: MeowBottomNavigation.Model ->
            if (model.id == 1) {
                if (`var` != 4) {
                    SelectFragment = Profile.getInstance()
                    `var` = 4
                }
            } else if (model.id == 2) {
                if (`var` != 1) {
                    SelectFragment = TheList.getInstance()
                    `var` = 1
                }
            } else if (model.id == 3) {
                if (`var` != 0) {
                    SelectFragment = Seach.getInstance()
                    `var` = 0
                }
            } else if (model.id == 4) {
                if (`var` != 2) {
                    SelectFragment = Home.getInstance()
                    `var` = 2
                }
            }
            setFragments()
            null
        }
    }

    private fun setFragments() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.Frame_Continuer, SelectFragment!!)
        transaction.commit()
    }

    private fun setDefaultFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.Frame_Continuer, Home.getInstance())
        transaction.commit()
    }


}