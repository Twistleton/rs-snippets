fn main() {

    // Rust defines two string-related types

    // String
    // - a standard Rust struct type
    // - manages text allocated on the heap

    // str
    // - Rust primitive type
    // - a fat pointer (memory address + length of the field)
    // - represents a slice of text elsewhere

    let name = String::from("Hugo");

    // explicit type coercion from &String to &str
    let s1: &str = &name;

    // implicit type coercion from &String to &str
    let s2 = &name;

    println!("{}", name);
    println!("s1: {}", s1);
    println!("s2: {}", s1);

    println!("{:p}", &s1);
    println!("{:p}", &s2);

    // a string literal is a string slice with static lifetime - explicit typed

    let s3: &'static str = "Sligo";

    // a string literal is a string slice with static lifetime - implicit typed
    let s4 = "Galway";

    println!("s3: {}, s4: {}", s3, s4);

    println!("pointer: {:p}, len: {}, text: {}", s3.as_ptr(), s3.len(), s3);
    println!("pointer: {:p}, len: {}, text: {}", s4.as_ptr(), s4.len(), s4);

    let text = "HelloðŸ¦€";    // ðŸ¦€ = 4 Bytes

    // to iterate over the raw bytes in a string
    for b in text.bytes() {
        println!("{} {:x} {:b}", b, b, b);
    }

    // to iterate over the characters in a string
    for c in text.chars() {
        println!("{}", c);
    }

    // You can create a slice as a portion of a string
    // specify the start and end indexes (as byte positions)
    // the start index is inclusive (default is 0)
    // the end index is exclusvie (default is to end of string)

    // &text[start_index .. end_index]     // end_index exclusive

    let crab = &text[5..9];

    println!("pointer {:p}, len: {}, text: {}", text.as_ptr(), text.len(), text);
    println!("pointer {:p}, len: {}, text: {}", crab.as_ptr(), crab.len(), crab);

    // slice mutability

    let mut message = String::from("creoso");

    message.push_str(" o gymru");

    if true {
        let s: &mut str = &mut message[9..];
        s.make_ascii_uppercase();
    }

    println!("\nmessage: {}", message);

}