use std::collections::HashMap;

fn main() {

    let mut scores = HashMap::new();

    scores.insert(String::from("Blue"), 10);
    scores.insert(String::from("Red"), 0);

    scores.entry(String::from("Yellow")).or_insert(50);
    scores.entry(String::from("Blue")).or_insert(50);

    let red_score = scores.entry(String::from("Red")).or_insert(0);

    *red_score += 10;
    *red_score += 10;
    *red_score += 10;

    println!("{:?}", scores);

    // {"Red": 30, "Yellow": 50, "Blue": 10}
}