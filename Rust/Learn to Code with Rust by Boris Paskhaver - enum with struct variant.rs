#[derive(Debug)]
struct UserCard {
    card_number: u32,
    card_holder: String,
    expiration_date: String,
}

#[derive(Debug)]
struct Credentials {
    username: String,
    password: String,
}

#[derive(Debug)]
enum PaymentMethod {
    // tuple variant
    CreditCard(String),
    // struct variant
    DebitCard(UserCard),
    // struct variant
    PayPal { username: String, password: String },
    Cash,
}

fn main() {

    let user_card = UserCard {
        card_holder: String::from("Hugo"),
        card_number: 12345,
        expiration_date: String::from("12/2025"),
    };

    let payment_methode = PaymentMethod::CreditCard(user_card);

    println!("{:?}", payment_methode);


    // struct variant
    let payment_methode = PaymentMethod::PayPal {
        username: String::from("Hugo"),
        password: String::from("password"),
    };

    println!("{:?}", payment_methode);

}