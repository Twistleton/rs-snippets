case class Student(id: Int, name: String, topics: Set[String]) {
  override def equals(student: Any): Boolean = {
    student match {
      case s: Student => id == s.id
      case _ => false
    }
  }
}

val alice = Student(id = 1, name = "Alice Abbott", topics = Set("History", "Math"))
val boris = Student(id = 2, name = "Boris Blank", topics = Set("History", "Music"))
val charlie = Student(id = 3, name = "Charlie Chaplin", topics = Set("History", "Math", "Music", "Dance"))

def getTopicsForStudentIds(students: Set[Student], ids: Set[Int]): Set[String] =
  for {
    id <- ids
    student <- students if student.id == id
    topic <- student.topics
  } yield topic

def maxByTopics(students: Set[Student]): Student = students.maxBy { _.topics.size }

def getByTopics(students: Set[Student], topics: Set[String]): Set[Student] =  topics.flatMap(t => students.filter(_.topics.contains(t)))

def getByTopics_v2(students: Set[Student], topics: Set[String]): Set[Student] =
  for {
    topic <- topics
    student <- students if student.topics.contains(topic)
  } yield student

getTopicsForStudentIds(Set(alice,boris), Set(1,2))     // Set[String] = Set(History, Math, Music)

maxByTopics(Set(alice,boris,charlie))                  // Student(1,Charlie Chaplin,Set(History, Math, Music, Dance))

getByTopics(Set(alice, boris, charlie), Set("Music"))  // Set(boris, charlie)

val tom = Student(id = 10, name = "Tom Ripley", topics = Set("History", "Math"))            // id = 10
val dick = Student(id = 10, name = "Dick Greenleaf", topics = Set("History", "Math"))       // same id!

Set(tom, dick)