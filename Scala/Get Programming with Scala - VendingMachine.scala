class VendingMachine {

  var chocolateBar = 2
  var granolaBar = 0

  var totalMoney = 0.0

  def buy(product: String, money: Double): String = {
    if !isProductAvailable(product) then "Das Produkt $product ist bereits ausvekauft"
    else if !isMoneyEnough(product, money) then "Bitte ausreichend Geld einwerfen"
    else completeRequest(product, money)
  }

  def isProductAvailable(product: String): Boolean = {
    val productQuantity = if product == "chocolate" then chocolateBar
    else if product == "granola" then granolaBar
    else 0
    productQuantity > 0
  }

  def isMoneyEnough(product: String, money: Double): Boolean = {
    val productPrice = if product == "chocolate" then 1.5
    else if product == "granola" then 1.0
    else 0.0
    money >= productPrice
  }

  def completeRequest(product: String, money: Double): String = {
    collectMoney(money)
    releaseProduct(product)
    s"You buy one $product"
  }

  def collectMoney(money: Double): Unit =
    totalMoney += money

  def releaseProduct(product: String): Unit =
    if product == "chocolate" then chocolateBar -= 1
    else if product == "granola" then granolaBar -= 1

}

object Main {
  def main(args: Array[String]): Unit = {
    val vm = VendingMachine()
    var result = vm.buy("chocolate", 0.5)
    println(result)
    result = vm.buy("chocolate", 2)
    result = vm.buy("chocolate", 2)
    println(vm.totalMoney)
    println(result)
  }
}
