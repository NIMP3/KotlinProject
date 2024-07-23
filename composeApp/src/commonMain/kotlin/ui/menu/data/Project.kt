package ui.menu.data

data class Project (
    val title: String,
    val description: String,
    val resource: String
) {
    companion object {
        fun createProjectList(): List<Project> {
            return listOf(
                Project(title = "Expense App", description = "This project was designed on Kotlin Multiplatform course", resource = "https://i.imgur.com/mFKXshy.png"),
                Project(title = "Movies App", description = "This project was designed on Kotlin Multiplatform youtube tutorial", resource = "https://i.imgur.com/sYAk1tp.png")
            )
        }
    }
}