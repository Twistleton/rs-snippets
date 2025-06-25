case class User(id: Int)

case class PersonalDetails()

case class Account()

case class UserContext(id: Int, details: PersonalDetails, account: Account)

case class ProductSelection(productIds: List[Int])

def purchase(userId: Int, selection: ProductSelection): Either[String, Int] = {

  given userContext: UserContext = getUserContext(userId)     // declaring a value as implicit

  for {
    _ <- validateAddressWithinDistance
    _ <- validateSelection(selection)
    _ <- validateBalance(selection)
  } yield placeOrder(selection)
}

private def getUserContext(userId: Int): UserContext = ???

private def validateBalance(selection: ProductSelection)(using userContext: UserContext): Either[String, Double] = ???     // declaring a function parameter as implicit

private def validateAddressWithinDistance(using userContext: UserContext): Either[String, UserContext] = ???

private def validateSelection(selection: ProductSelection)(using userContext: UserContext): Either[String, ProductSelection] = ???

private def placeOrder(selection: ProductSelection)(using userContext: UserContext): Int = ???