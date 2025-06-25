class CreditCard

data class Payment(val creditCard: CreditCard, val amount: Int) {

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

fun main() {

    val creditCardA = CreditCard()
    val creditCardB = CreditCard()

    val payment1 = Payment(creditCardA, 100)

    val result = payment1.combine(Payment(creditCardA, 200)).combine(Payment(creditCardA, 200)).combine(Payment(creditCardA, 400))

    println(result)     // Payment(creditCard=CreditCard@7cc355be, amount=900)

}