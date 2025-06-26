// references never give ownership and the functions only make use of them until they return.

fn add_and_print_hungary(country: &mut String) {
    country.push_str("-Hungary");
    println!("{}", country);
}

fn main() {

    let mut country: String = String::from("Estonia");

    let country_ref: &mut String = &mut country;

    // Here we are use the reference for printing
    println!("Country is now: {}", country_ref);

    // Giving away the ownership for the reference? No?
    add_and_print_hungary(country_ref);

    // We still have the reference's ownership, even though we seemingly gave it away to the function
    println!("Country is now: {}", country_ref);
}