class Employee(name: String): Comparable<Employee> {

    val name: String = name

    override fun compareTo(other: Employee): Int {
        return this.name.compareTo(other.name)
    }
    override fun toString(): String {
        return this.name
    }
}

fun main() {

    val employees = listOf<Employee>(Employee("Oscar"), Employee("Hugo"), Employee("Boris"), Employee("Alfred"))

    val result = employees.sorted()

    println(result)         // [Alfred, Boris, Hugo, Oscar]

}