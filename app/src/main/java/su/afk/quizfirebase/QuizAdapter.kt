package su.afk.quizfirebase

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import su.afk.quizfirebase.databinding.RvMainQuizItemBinding
import su.afk.quizfirebase.quiz.QuizActivity
import java.util.zip.Inflater

class QuizAdapter(private val quizModelList: List<QuizModel>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    class QuizViewHolder(private val binding: RvMainQuizItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: QuizModel) {
            // bind all view id
            binding.apply {
                quizTitle.text = model.title
                quizDescriptor.text = model.descriptor
                quizTime.text = model.time + " min"
                root.setOnClickListener {
                    // TODO: Перенести в листенер и на mainActivity
                    val intent = Intent(root.context, QuizActivity::class.java)
                    root.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val binding = RvMainQuizItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuizViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return quizModelList.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.bind(quizModelList[position])
    }

}