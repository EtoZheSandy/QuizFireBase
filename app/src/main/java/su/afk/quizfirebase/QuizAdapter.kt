package su.afk.quizfirebase

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import su.afk.quizfirebase.databinding.RvMainQuizItemBinding

class QuizAdapter(private val quizModelList: List<QuizModel>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    class QuizViewHolder(private val binding: RvMainQuizItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: QuizModel) {
            // bind all view id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}