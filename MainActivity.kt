import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.surveyapp.databinding.ActivityMainBinding
import java.util.UUID

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val surveyList = mutableListOf<Survey>()  // Список опросов

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Настройка RecyclerView для отображения списка опросов
        binding.surveyRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = SurveyAdapter(surveyList) { survey -> openSurvey(survey) }
        binding.surveyRecyclerView.adapter = adapter

        // Обработка нажатия кнопки "Создать опрос"
        binding.createSurveyButton.setOnClickListener {
            createSurvey()
            adapter.notifyDataSetChanged()
        }
    }

    private fun createSurvey() {
        // Логика для создания нового опроса
        val survey = Survey(
            id = generateUniqueId(),
            title = "Опрос ${surveyList.size + 1}",
            questions = listOf(
                Question("1", "Ваш любимый цвет?", listOf("Красный", "Синий", "Зелёный")),
                Question("2", "Ваш любимый спорт?", listOf("Футбол", "Баскетбол", "Теннис"))
            )
        )
        surveyList.add(survey)
    }

    private fun openSurvey(survey: Survey) {
        // Генерация уникальной ссылки для опроса и переход к активности прохождения
        val surveyLink = "https://myapp.com/survey/${survey.id}"
        val intent = Intent(this, SurveyDetailActivity::class.java).apply {
            putExtra("survey_id", survey.id)
        }
        startActivity(intent)
    }

    private fun generateUniqueId(): String {
        return UUID.randomUUID().toString()
    }
}
