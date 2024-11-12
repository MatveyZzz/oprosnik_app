data class Survey(
    val id: String,
    val title: String,
    val questions: List<Question>
)

data class Question(
    val id: String,
    val text: String,
    val options: List<String>
)
