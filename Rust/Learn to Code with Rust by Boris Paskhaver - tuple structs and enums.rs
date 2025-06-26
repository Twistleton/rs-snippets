// tuple struct
#[derive(Debug)]
// hours, minutes
struct ShortDuration(u32, u32);

// days, hours, minutes
#[derive(Debug)]
struct LongDuration(u32, u32, u32);

// enum
#[derive(Debug)]
enum PaymentMethod {
    CreditCard(String),
    DebitCard(String),
    PayPal(u32, bool),
    Cash
}

fn main() {

    let short_duration = ShortDuration(2, 25);

    println!("short duration with hours: {} minutes: {}", short_duration.0, short_duration.1);

    let long_duration = LongDuration(5, 12, 30);

    println!("long duration with days: {} hours: {} minutes: {}", long_duration.0, long_duration.1, long_duration.2);

    let visa = PaymentMethod::CreditCard(String::from("123-456-789-000"));
    let mastercard = PaymentMethod::DebitCard(String::from("123-456-789-000"));
    let paypal = PaymentMethod::PayPal(1234, true);

    println!("payment method: {:?}", visa);

}