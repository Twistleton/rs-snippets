const NUMBER_OF_MONTHS: u32 = 12;
static SEASONS: [&str; 4] = ["Spring", "Summer", "Fall", "Winter"];

fn main() {
    // Create a String with the into()-function
    let _my_string: String = "Try to make this a String".into();

    let s1: &str = "Hugo";
    let s2: String = String::from("Hugo");

    print_string(s1); // a string slice
    print_string(&s2); // a reference to a String

    // Passed String with ownership
    let country_1 = String::from("Scotland");
    prints_country_with_ownership(country_1.clone());
    prints_country_with_ownership(country_1);

    // Passed a reference to a String
    let country_2 = String::from("Scotland");
    prints_country_via_reference(&country_2);
    prints_country_via_reference(&country_2);

    let mut country = String::from("Austria");
    add_hungary(&mut country);

    let country = String::from("Austria");
    adds_hungary_using_mut(country);
}

// Passed a &str or reference to a string to the function
fn print_string(s: &str) {
    println!("{}", s);
}

// Passed a String with moving ownership (using clone)
fn prints_country_with_ownership(country_name: String) {
    println!("{}", country_name);
}

// Passed a reference to a String
fn prints_country_via_reference(country_name: &String) {
    println!("{}", country_name);
}

fn add_hungary(country_name: &mut String) {
    country_name.push_str("-Hungary");
    println!("Now it says: {country_name}");
}

// Passed parameter changed to a mutable String
fn adds_hungary_using_mut(mut string_to_add_hungary_to: String) {
    string_to_add_hungary_to.push_str("-Hungary");
    println!("{}", string_to_add_hungary_to);
}