package material.test

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.HORIZONTAL
import com.bumptech.glide.Glide
import com.example.R
import com.google.android.material.snackbar.Snackbar
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fruit_item.view.*

class MainActivity : AppCompatActivity() {
    private val fruitAdapter = GroupAdapter<ViewHolder>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "删除数据", Snackbar.LENGTH_SHORT).setAction("取消") {
                Toast.makeText(this, "数据已恢复", Toast.LENGTH_SHORT).show()
            }.show()
        }
        initFruits()
        recycler_view.layoutManager = StaggeredGridLayoutManager(3, HORIZONTAL)
        recycler_view.adapter = fruitAdapter
        swipe_refresh.setColorSchemeResources(R.color.colorPrimary)
        swipe_refresh.setOnRefreshListener { this.refreshFruits() }
    }

    private fun refreshFruits() {
        runOnUiThread {
            initFruits()
            fruitAdapter.notifyDataSetChanged()
            swipe_refresh.isRefreshing = false
        }
    }

    private fun initFruits() {
        fruitAdapter.clear()
        for (i in 1..3) {
            fruitAdapter.add(FruitItem(Fruit("苹果", R.drawable.apple)))
            fruitAdapter.add(FruitItem(Fruit("香蕉", R.drawable.banana)))
            fruitAdapter.add(FruitItem(Fruit("橙子", R.drawable.orange)))
            fruitAdapter.add(FruitItem(Fruit("西瓜", R.drawable.watermelon)))
            fruitAdapter.add(FruitItem(Fruit("梨子", R.drawable.pear)))
            fruitAdapter.add(FruitItem(Fruit("葡萄", R.drawable.grape)))
            fruitAdapter.add(FruitItem(Fruit("菠萝", R.drawable.pineapple)))
            fruitAdapter.add(FruitItem(Fruit("草莓", R.drawable.strawberry)))
            fruitAdapter.add(FruitItem(Fruit("樱桃", R.drawable.cherry)))
            fruitAdapter.add(FruitItem(Fruit("芒果", R.drawable.mango)))
        }
    }

    class Fruit(val name: String, val imageId: Int)
    inner class FruitItem(private val fruit: Fruit) : Item<ViewHolder>() {
        override fun getLayout() = R.layout.fruit_item
        override fun bind(holder: ViewHolder, position: Int) {
            val context = holder.itemView.context
            val itemView = holder.itemView
            itemView.fruitName.text = fruit.name
            Glide.with(context).load(fruit.imageId).into(itemView.fruitPic)
            itemView.setOnClickListener {
                val intent = Intent(context, FruitActivity::class.java)
                intent.putExtra(FRUIT_NAME, fruit.name)
                intent.putExtra(FRUIT_IMAGE_ID, fruit.imageId)
                context.startActivity(intent)
            }
        }
    }

    companion object {
        const val FRUIT_NAME = "水果名称"
        const val FRUIT_IMAGE_ID = "图片编号"
    }
}