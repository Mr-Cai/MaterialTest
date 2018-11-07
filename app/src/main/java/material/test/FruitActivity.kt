package material.test

import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.R
import kotlinx.android.synthetic.main.activity_fruit.*
import material.test.MainActivity.Companion.FRUIT_IMAGE_ID
import material.test.MainActivity.Companion.FRUIT_NAME

class FruitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)
        setSupportActionBar(toolbar)
        window.statusBarColor = TRANSPARENT
        val fruitName = intent.getStringExtra(FRUIT_NAME)
        val fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        collapsingToolbar.title = fruitName
        Glide.with(this).load(fruitImageId).into(fruit_image_view)
        val fruitContent = generateFruitContent(fruitName)
        fruitDesc.text = fruitContent
    }

    private fun generateFruitContent(fruitName: String): String {
        val fruitContent = StringBuilder()
        for (i in 0..499) fruitContent.append(fruitName)
        return fruitContent.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
