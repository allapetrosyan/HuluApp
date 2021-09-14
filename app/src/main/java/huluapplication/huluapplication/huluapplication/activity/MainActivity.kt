package huluapplication.huluapplication.huluapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import huluapplication.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)
        val navController = findNavController(R.id.nav_fragment)
        bottomNavigationView.setupWithNavController(navController)
}
}






















/*
<androidx.drawerlayout.widget.DrawerLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:id="@+id/drawer_layout"
tools:context=".MainActivity">

<LinearLayout
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical">

<LinearLayout
android:id="@+id/container_toolbar"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:orientation="vertical">

<include
android:id="@+id/toolbar"
layout="@layout/toolbar" />
</LinearLayout>
<FrameLayout
android:id="@+id/content_frame"
android:layout_width="match_parent"
android:layout_height="match_parent" />

</LinearLayout>
<ListView
android:id="@+id/left_drawer"
android:layout_width="240dp"
android:layout_height="match_parent"
android:layout_gravity="start"
android:background="#FFFFFF"
android:choiceMode="singleChoice"
android:divider="@android:color/darker_gray"
android:dividerHeight="1dp" />

</androidx.drawerlayout.widget.DrawerLayout>
*/




/*
private lateinit var mNavigationDrawerItemTitles: Array<String>
private var mDrawerLayout: DrawerLayout? = null
private var mDrawerList: ListView? = null
var toolbar: Toolbar? = null
private var mDrawerTitle: CharSequence? = null
private var mTitle: CharSequence? = null
var mDrawerToggle: ActionBarDrawerToggle? = null
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    mDrawerTitle = title
    mTitle = mDrawerTitle
    mNavigationDrawerItemTitles =
        resources.getStringArray(R.array.navigation_drawer_items_array)
    mDrawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
    mDrawerList = findViewById<View>(R.id.left_drawer) as ListView
    setupToolbar()
    val drawerItem = arrayOfNulls<DataModel>(3)
    drawerItem[0] = DataModel(R.drawable.ic_launcher_background, "Connect")
    drawerItem[1] = DataModel(R.drawable.ic_launcher_background, "Fixtures")
    drawerItem[2] = DataModel(R.drawable.ic_launcher_background, "Table")
    supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    supportActionBar!!.setHomeButtonEnabled(true)
    val adapter = DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem)
    mDrawerList!!.adapter = adapter
    mDrawerList!!.onItemClickListener = DrawerItemClickListener()
    mDrawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
    mDrawerLayout!!.setDrawerListener(mDrawerToggle)
    setupDrawerToggle()
}

private inner class DrawerItemClickListener : AdapterView.OnItemClickListener {
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectItem(position)
    }
}

private fun selectItem(position: Int) {
    var fragment: Fragment? = null
    when (position) {
        0 -> fragment = ConnectFragment()
        1 -> fragment = FixturesFragment()
        2 -> fragment = TableFragment()
        else -> {
        }
    }
    if (fragment != null) {
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit()
        mDrawerList?.setItemChecked(position, true)
        mDrawerList?.setSelection(position)
        setTitle(mNavigationDrawerItemTitles[position])
        mDrawerList?.let { mDrawerLayout?.closeDrawer(it) }
    } else {
        Log.e("MainActivity", "Error in creating fragment")
    }
}

override fun onOptionsItemSelected(item: MenuItem1): Boolean {
    return if (mDrawerToggle?.onOptionsItemSelected(item)!!) {
        true
    } else super.onOptionsItemSelected(item)
}

override fun setTitle(title: CharSequence) {
    mTitle = title
    supportActionBar!!.title = mTitle
}

override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)
    mDrawerToggle?.syncState()
}

private fun setupToolbar() {
    toolbar = findViewById<View>(R.id.toolbar) as Toolbar
    setSupportActionBar(toolbar)
    supportActionBar!!.setDisplayShowHomeEnabled(true)
}

private fun setupDrawerToggle() {
    mDrawerToggle = ActionBarDrawerToggle(
        this,
        mDrawerLayout,
        toolbar,
        R.string.app_name,
        R.string.app_name
    )
    //This is necessary to change the icon of the Drawer Toggle upon state change.
    mDrawerToggle!!.syncState()
*/

