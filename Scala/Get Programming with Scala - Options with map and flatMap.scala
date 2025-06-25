// Options

case class Car(modell: String,
               owner: Option[Person],
               registrationPlate: Option[String])

case class Person(name: String,
                  age: Int,
                  drivingLicense: Option[String])


def ownerName_v1(car: Car): Option[String] = {
  car.owner match {
    case Some(p) => Some(p.name)
    case _ => None
  }
}

def ownerName_v2(car: Car): Option[String] = car.owner.map(p => p.name)

val p = Person("Hugo", 18, Some("L2043102"))
val car = Car("smart", Some(p), Some("sm453"))

val nameOfTheOwner = ownerName_v1(car)

println(nameOfTheOwner)

def extractRegistrationPlate(car: Car): Option[String] = car.registrationPlate.map(x => x.toUpperCase())

val result_plate = extractRegistrationPlate(car)

println(result_plate)

def ownerDrivingLicense(car: Car): Option[String] = car.owner.flatMap(_.drivingLicense)

val drivingLicense = ownerDrivingLicense(car)

println(drivingLicense)


def ownerBelowAge_v1(car: Car, age: Int): Option[String] = car.owner.filter(_.age < age).map(_.name)

def ownerBelowAge_v2(car: Car, belowAge: Int): Option[String] =
  car match {
    case Car(_, Some(Person(name, age, _)), _) if age < belowAge  => Some(name)
    case _ => None
  }

def ownerBelowAge_v3(car: Car, age: Int): Option[String] = {
  car.owner.flatMap(p => if (p.age < age) Some(p.name) else None)
}

val youngDriver = ownerBelowAge_v3(car, 21)

println(youngDriver)
