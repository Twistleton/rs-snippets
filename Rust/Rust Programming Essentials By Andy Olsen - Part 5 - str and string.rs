fn main() {

    // https://symbl.cc

    // str
    let s1 = "ðŸ¦€";

    // a string literal is a string slice with static lifetime
    let s2: &'static str = "ðŸ¦…";

    // fat pointer (point to memory + length of the field)

    println!("{}, {:p}, {}", s1, s1.as_ptr(), s1.len());
    println!("{}, {:p}, {}", s2, s2.as_ptr(), s2.len());

    // create an instance of the String type
    // the local variable s3 lives on the stack
    // the text itself is allocated on the heap (as a potentially growable vector of bytes)

    let s3 = String::from("Rusty");

    println!("{}, {:p}, {}", s3, s3.as_ptr(), s3.len());

    let mut s4 = String::from("A language empowering everyone ");

    s4.push_str("to build reliable and efficient software.");

    println!("{}, {:p}, {}", s4, s4.as_ptr(), s4.len());

}