fn main() {

    let s1 = String::from("Здравствуйте");

    println!("\n-- chars() --");

    for c in s1.chars() {
        print!("{}|", c);
    }

    println!("\n-- bytes() --");

    for b in s1.bytes() {
        print!("{}|", b);
    }

    // -- chars() --
    // З|д|р|а|в|с|т|в|у|й|т|е|
    // -- bytes() --
    // 208|151|208|180|209|128|208|176|208|178|209|129|209|130|208|178|209|131|208|185|209|130|208|181|

}