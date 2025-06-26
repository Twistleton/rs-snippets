fn main() {

    // returning a copyable value - Rust bit-copies the value back to the caller
    let x = f1();      // receives a copy
    println!("{}", x);

    // returning a non-copyable value
    let y = f2();       // receives ownership
    println!("{}", y);

    let x = f3(80);
    println!("{}", x);
}

fn f1() -> i32 {
    43                      // returns a copy
}

fn f2() -> String {
    String::from("Hugo")    // returns ownership
}

fn f3(points: i32) -> String {

    // returning a string literal as return type for String is not allowed
    // let result = if points > 70 {"GOOD"} else {"BAD"};

    if points > 70 {"GOOD".to_string()} else {"BAD".to_string()}
}