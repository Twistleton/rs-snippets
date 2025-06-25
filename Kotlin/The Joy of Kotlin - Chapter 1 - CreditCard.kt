
data class CreditCard(val ccNumber: Long)

class Donut() {
    companion object {
        val price = 1.00
    }
}

class Payment(val creditCard: CreditCard, val amount: Double) {

    fun combine(payment: Payment): Payment =
        if (creditCard == payment.creditCard)
            Payment(creditCard, amount + payment.amount)
        else
            throw IllegalStateException("Cards don't match.")

    companion object {
        fun groupByCard(payments: List<Payment>): List<Payment> =
            payments.groupBy { it.creditCard }
                    .values
                    .map { it.reduce(Payment::combine) }
        }
}

data class Purchase(val donuts: List<Donut>, val payment: Payment)

fun buyDonuts(quantity: Int = 1, creditCard: CreditCard): Purchase {

    return Purchase(List(quantity) {
             Donut()
	}, Payment(creditCard, Donut.price * quantity))
}

fun main() {
    val purchase1 = buyDonuts(2, CreditCard(29201000))
    val purchase2 = buyDonuts(2, CreditCard(29202000))
    val purchase3 = buyDonuts(3, CreditCard(29201000))

    val payments = Payment.groupByCard(listOf(purchase1.payment, purchase2.payment, purchase3.payment))

    for (payment in payments) {
        println("${payment.creditCard} ${payment.amount} ")
    }

}