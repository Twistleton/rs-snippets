// Destructuring data objects

import java.time.*

data class Person(val name: String, val registered: Instant = Instant.now())

fun showV1(persons: List<Person>) {
	for ((name, date) in persons)
	println(name + "'s registration date: " + date)
}

fun showV2(persons: List<Person>) {
	for (person in persons)
	println(person.component1()	+ "'s registration date: " + person.component2())
}

fun main() {
	val persons = listOf(Person("Mike"), Person("Paul"))
	showV1(persons)
    println()
    showV2(persons)
    // Mike's registration date: 2023-10-10T08:16:10.634152965Z
    // Paul's registration date: 2023-10-10T08:16:10.634178359Z
	//
	// Mike's registration date: 2023-10-10T08:16:10.634152965Z
    // Paul's registration date: 2023-10-10T08:16:10.634178359Z
}