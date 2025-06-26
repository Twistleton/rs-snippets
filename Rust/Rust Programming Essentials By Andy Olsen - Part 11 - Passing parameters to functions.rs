fn main() {

    let x = 42;
    f1(x);  // passes a copyable value
    println!("{}", x);

    let s = String::from("Hugo");
    f2(s);  // passes a non-copyable value like String - Rust moves ownership
    // println!("{}", s);  // a original string loses ownership

    let t = String::from("Oscar");
    // passing by reference
    // borrows the value
    // calling function retains ownership
    f3(&t);
    println!("{}", t);

    // You can't pass in a &str (technical a slice of text)
    // expected `&String`, found `&str`
    // f3("Gustav");
    // f3(&"Gustav".to_string());

    // passing a &str (a String literal) or &String (String struct) to a &str function
    f4(&"Gustav");
    f4(&String::from("Gustav"));

    // mutable reference

    let mut number = 42;
    let mut name = String::from("Oscar");

    name.push_str("?");

    f5(&mut number, &mut name);

    println!("{}", number);
    println!("{}", name);

}

// receives a copy
fn f1(x: i32) {
    println!("{}", x)
}

// receives ownership
fn f2(s: String) {
    println!("{}", s)
}

// receives an reference - borrows the value
fn f3(s: &String) {
    let result1 = (*s).to_uppercase();

    // Rust actually allows a more direct syntax for invoking methods
    // obj.method()         <-- Rust allows the same syntax for obj or ref
    // ref.method()
    let result2 = s.to_uppercase();
    println!("{}", result1);
    println!("{}", result2);
}

fn f4(s: &str) {
    println!("{0} {0:p}", s)
}

fn f5(n: &mut i32, s: &mut String) {
    *n = *n + 1;
    println!("mutable reference: {} (before change)", s);
    s.clear();
    // shorthand syntax
    s.push_str("Karl-Heinz");
    println!("mutable reference: {} (after change)", s);
}