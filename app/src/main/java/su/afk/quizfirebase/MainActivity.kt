package su.afk.quizfirebase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import su.afk.quizfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var quizModelList: MutableList<QuizModel>
    lateinit var adapter: QuizAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quizModelList = mutableListOf()
        getDataFromFirebase()

    }

    private fun getDataFromFirebase() {
        //get data
        quizModelList.add(QuizModel("1", "Програмиование", "Базовое програмировапние", "10"))
        quizModelList.add(QuizModel("2", "Японский язык", "Как хорошо ты знаешь японский", "5"))
        quizModelList.add(QuizModel("3", "Английский", "Как хорошо ты знаешь английский", "7"))
        setupRecycleView()
    }

    private fun setupRecycleView() {
        adapter = QuizAdapter(quizModelList)
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.adapter = adapter
    }
}