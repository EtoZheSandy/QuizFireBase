package su.afk.quizfirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import su.afk.quizfirebase.adapter.QuizAdapter
import su.afk.quizfirebase.databinding.ActivityMainBinding
import su.afk.quizfirebase.models.QuizModel
import su.afk.quizfirebase.quiz.QuizActivity

class MainActivity : AppCompatActivity(), QuizAdapter.listenerQuiz {
    private lateinit var binding: ActivityMainBinding
    lateinit var quizModelList: MutableList<QuizModel>
    private lateinit var adapter: QuizAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quizModelList = mutableListOf()
        getDataFromFirebase()
    }

    private fun getDataFromFirebase() {
        binding.progressBarQuiz.visibility = View.VISIBLE
        // Получаем доступ к корневой директории базы данных
        val database = FirebaseDatabase.getInstance().reference

        // Устанавливаем слушатель для чтения данных
        database.child("quizs").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (quizSnapshot in dataSnapshot.children) {
                    Log.d("TAG", "quizSnapshot: $quizSnapshot")
                    val quizModel = quizSnapshot.getValue(QuizModel::class.java)
                    Log.d("TAG", "quizModel: $quizModel")
                    if (quizModel != null) {
                        quizModelList.add(quizModel)
                    }
                }
                setupRecycleView()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("MainActivity", "Failed to read value.", databaseError.toException())
            }
        })
    }

    private fun setupRecycleView() {
        binding.progressBarQuiz.visibility = View.GONE
        adapter = QuizAdapter(this, quizModelList)
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.adapter = adapter
    }

    override fun startQuiz(quiz: QuizModel) {
            val intent = Intent(this, QuizActivity::class.java).apply {
                putExtra(QuizActivity.QUIZ, quiz)
            }
            startActivity(intent)
    }
}