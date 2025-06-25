case class Contact(
                    name: String,
                    surname: String,
                    numbers: List[ContactNumber],
                    company: Option[String],
                    email: Option[String]) {
  def toPrettyString: String = s"$surname $name"
}


sealed trait Label
case object Work extends Label
case object Home extends Label

case class ContactNumber(number: String, label: Label)

object Main {

  def main(args: Array[String]): Unit = {

    val c1 = Contact("Dieter", "Meyer", List(ContactNumber("12345", Home)), Some("Yello"), Some("dieter@gmail.com"))
    val c2 = Contact("Boris", "Blank",  List(ContactNumber("12345", Home)), Some("Yello"), Some("boris@gmail.com"))
    val c3 = Contact("Dieter", "Zetsche",  List(ContactNumber("50504", Home)), Some("Daimler"), Some("zetsche@daimler.com"))

    val contacts = List(c1, c2, c3)

    val result = contacts.flatMap(contact => contact.numbers)

    for (number <- result) {
      println(s"Telefonnummer: ${number.number}")
    }

    val emails = List("boris@gmail.com", "dieter@gmail.com")

    val emailAddresses = for {
      contact <- contacts
      email <- emails
      if contact.email.exists(_.equalsIgnoreCase(email))
    } yield
      email

    println(emailAddresses)

    println(isPresent(contacts, c1))

    println(isPresentByName(contacts, "Meyer"))

    println(countByCompany(contacts, "Yello"))

    val optContact = findByCompany(contacts, "yello")

    println(optContact)

    println(sort(contacts))

    println(describeFirstN(2, contacts))

    println(totalNumbers(contacts))

    println(perCompany(contacts))

    println(perLetter(contacts))

  }

  def isPresent(addressBook: List[Contact], contact: Contact): Boolean = addressBook.contains(contact)

  def isPresentByName(addressBook: List[Contact], name: String): Boolean = addressBook.exists(_.name == name)

  def countByCompany(addressBook: List[Contact], company: String): Int = addressBook.count(_.company.contains(company))

  // def findByCompany(addressBook: List[Contact], company: String): Option[Contact] = addressBook.find(_.company.getOrElse("").equalsIgnoreCase(company))

  def findByCompany(addressBook: List[Contact], company: String): Option[Contact] = addressBook.find(contact => contact.company.exists(_.equalsIgnoreCase(company)) )

  def sort(addressBook: List[Contact]): List[Contact] = addressBook.sortBy(contact => (contact.surname, contact.name))

  def describeFirstN(n: Int, addressBook: List[Contact]): String = addressBook.take(n).map(_.toPrettyString).mkString("\n")

  def totalNumbers(addressBook: List[Contact]): Int = addressBook.map(_.numbers.size).sum

  def perCompany(addressBook: List[Contact]): Map[Option[String], List[Contact]] = addressBook.groupBy(_.company)

  def perLetter(addressBook: List[Contact]): Map[Char, List[Contact]] = addressBook.groupBy(_.surname.headOption.getOrElse('_'))

}