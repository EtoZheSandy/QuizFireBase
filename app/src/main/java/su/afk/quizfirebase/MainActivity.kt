package su.afk.quizfirebase

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
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
        binding.progressBarQuiz.visibility = View.VISIBLE
        Log.d("TAG", "getDataFromFirebase: Инициализируем")
        //get data from FirebaseDatabase
        val database = FirebaseDatabase.getInstance()
        database.reference.get()
            .addOnSuccessListener { dataSnapshot ->
                Log.d("TAG", "addOnSuccessListener")
                if (dataSnapshot.exists()) {
                    Log.d("TAG", "dataSnapshot.exists()")
                    for (snapshot in dataSnapshot.children) {
                        val quizModel = snapshot.getValue(QuizModel::class.java)
                        if (quizModel != null) {
                            Log.d("TAG", "quizModel: $quizModel")
                            quizModelList.add(quizModel)
                        } else {
                            Log.d("TAG", "quizModel is null")
                        }
                    }
                } else {
                    Log.d("TAG", "dataSnapshot does not exist")
                }
                setupRecycleView()
            }
            .addOnFailureListener { exception ->
                Log.e("TAG", "Failed to get data from Firebase", exception)
            }

    }

    private fun setupRecycleView() {
        binding.progressBarQuiz.visibility = View.GONE
        adapter = QuizAdapter(quizModelList)
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.adapter = adapter
    }
}