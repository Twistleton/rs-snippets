fn main() {
    let sky = "cloudy";
    let temperature = "warm";

    match (sky, temperature) {
        ("cloudy", "cold") => println!("It's dark and unpleasant today"), // arm
        ("clear", "warm") => println!("It's a nice day"),                 // arm
        ("cloudy", "warm") => println!("It's dark but not bad"),          // arm
        _ => println!("Not sure what the weather is."),                   // arm
    }

    let children = 5;
    let married = true;

    match (children, married) {
        (children, married) if !married => println!("Not married with {children} kids"),
        (children, married) if children == 0 && married => println!("Married but no children"),
        _ => println!("Married? {married}. Number of children: {children}."),
    }

    let first = (200, 0, 0);
    let second = (50, 50, 50);
    let third = (200, 50, 0);

    match_colours(first);
    match_colours(second);
    match_colours(third);

    match_number(50);
    match_number(13);
    match_number(16);
    match_number(4);
}

fn match_colours(rgb: (i32, i32, i32)) {
    match rgb {
        (r, _, _) if r < 10 => println!("Not much red"),
        (_, g, _) if g < 10 => println!("Not much green"),
        (_, _, b) if b < 10 => println!("Not much blue"),
        _ => println!("Each colour has at least 10"),
    }
}

// use @ to give a name to the value of a match expression
fn match_number(input: i32) {
    match input {
        number @ 4 => println!("{number} is unlucky in China (sounds close to 死)!"),
        number @ 13 => println!("{number} is lucky in Italy! In bocca al lupo!"),
        number @ 14..=19 => println!("Some other number that ends with -teen: {number}"),
        _ => println!("Some other number, I guess"),
    }
}