import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.surveyapp.databinding.ItemSurveyBinding

class SurveyAdapter(
    private val surveys: List<Survey>,
    private val onSurveyClick: (Survey) -> Unit
) : RecyclerView.Adapter<SurveyAdapter.SurveyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyViewHolder {
        val binding = ItemSurveyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SurveyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SurveyViewHolder, position: Int) {
        val survey = surveys[position]
        holder.bind(survey)
    }

    override fun getItemCount(): Int = surveys.size

    inner class SurveyViewHolder(private val binding: ItemSurveyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(survey: Survey) {
            binding.surveyTitle.text = survey.title
            binding.root.setOnClickListener { onSurveyClick(survey) }
        }
    }
}
