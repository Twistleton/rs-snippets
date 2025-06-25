import java.time.LocalDate

case class Student(id: Int, name: String)
case class ExamSession(title: String, localDate: LocalDate)

val historySession = ExamSession("History", localDate = LocalDate.now.plusDays(30))
val chemistrySession = ExamSession("Chemistry", localDate = LocalDate.now.plusDays(45))
val mathSession = ExamSession("Math", localDate = LocalDate.now)

val alice = Student(id = 1, name = "Alice Abbott")
val bob = Student(id = 2, name = "Bob Brown")
val charlie = Student(id = 3, name = "Charlie Clarke")

val registrations: Map[ExamSession, List[Student]] = Map(
  historySession -> List(alice, bob),
  chemistrySession -> List(alice, charlie)
)

val registrations_v2: Map[ExamSession, List[Student]] = Map(
  mathSession -> List(alice, bob, charlie)
)

// merging maps
def merge(
           regA: Map[ExamSession, List[Student]],
           regB: Map[ExamSession, List[Student]]
         ): Map[ExamSession, List[Student]] = regA ++ regB

def filterByStudentId(registrations: Map[ExamSession, List[Student]], ids: List[Int]): Map[ExamSession, List[Student]] =
  registrations.flatMap { case (examSession, students) =>
    val matches = students.filter(student => ids.contains(student.id))
    if (matches.nonEmpty) List(examSession -> matches) else List.empty
  }

def filterByStudentId_v2(registrations: Map[ExamSession, List[Student]], ids: List[Int]): Map[ExamSession, List[Student]] =
  for {
    (examSession, students) <- registrations
    matches = students.filter(student => ids.contains(student.id))
    if matches.nonEmpty
  } yield examSession -> matches

def getStudents(registrations: Map[ExamSession, List[Student]], session: ExamSession): List[Student] = registrations.getOrElse(session, List.empty)

def getExamSessions(registrations: Map[ExamSession, List[Student]]): Iterable[ExamSession] = registrations.keys

def getStudents(registrations: Map[ExamSession, List[Student]]): Set[Student] = registrations.values.flatten.toSet

val allRegistrations = merge(regA = registrations, regB = registrations_v2)

val newRegistrations = registrations + (mathSession -> List(bob)) - chemistrySession

println(newRegistrations)
println(allRegistrations)

val resultMap_v1 = filterByStudentId(allRegistrations, List(1))
val resultMap_v2 = filterByStudentId_v2(allRegistrations, List(1))

println(s"resultMap_v1: $resultMap_v1")
println(s"resultMap_v2: $resultMap_v2")

val studentsForChemistrySession = getStudents(allRegistrations, chemistrySession)

println(s"students for chemistry: $studentsForChemistrySession")


val allSessions = getExamSessions(allRegistrations)

println(s"all sessions: $allSessions")

val allStudents = getStudents(allRegistrations)

println(s"all students: $allStudents")

// --------------------------------------------------------------

// remove multiple entries from a map by providing their keys

val capitals_v1 = Map("Rome" -> "Italy", "London" -> "UK") -- Set("Rome", "Paris")
val capitals_v2 = Map("Rome" -> "Italy", "London" -> "UK") -- List("Rome", "Paris")

println(capitals_v1) // Map(London -> UK)
println(capitals_v2) // Map(London -> UK)

// --------------------------------------------------------------

val capitals = Map("Rome" -> "Italy", "London" -> "UK", "Dublin" -> "Ireland", "Paris" -> "France", "Stockholm" -> "Sweden")

val optItaly: Option[String] = capitals.get("Rome")        // Option[String] = Some(Italy)

val strItaly: String = capitals.getOrElse("Rome", "not found")         // String = "Italy"

val noInformation: String = capitals.getOrElse("Rome", "not found")    // String = "not found"

val countryItaly = capitals.apply("Rome")                              // String

// capitals.apply("Olso")                                              // NoSuchElementException - method is unsafe

def getCountry(capitalToCountry: Map[String, String], capital: String): String = capitalToCountry.getOrElse(capital, "unknown")

def getCapitals(capitalToCountry: Map[String, String]): List[String] = capitalToCountry.keys.toList

def longestCapitalName(capitalToCountry: Map[String, String]): String = capitalToCountry.keys.maxBy { _.size }

val japan = getCountry(capitals, "Tokio")

println(japan)                                  // unknown

println(capitals.size)                          // 5

println(capitals.keys)                          // Set(Stockholm, London, Rome, Dublin, Paris)

println(capitals.values)                        // Iterable(Sweden, UK, Italy, Ireland, France)

println(getCapitals(capitals))                  // List(Stockholm, London, Rome, Dublin, Paris)

println(longestCapitalName(capitals))           // Stockholm

