package com.example.petheart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

private const val Extra_Memory = "com.example.petheart.memoryId"

class MemoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory)


        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.activity_container)
        val memoryId = intent.getSerializableExtra(Extra_Memory) as UUID
        if (currentFragment == null) {
            val fragment =
                MemoryFragment.newInstance(
                    memoryId
                )
            supportFragmentManager
                .beginTransaction()
                .add(R.id.activity_container, fragment)
                .commit()
        }
    }


    companion object {
        fun newIntent(packageContext: Context, memoryId: UUID): Intent {
            return Intent(packageContext, MemoryActivity::class.java).apply {
                putExtra(Extra_Memory, memoryId)
            }
        }
    }
}