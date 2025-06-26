fn main() {

    let d1 = String::from("Huey");

    // r1 is a reference to a String (&String)
    // r1 borrows the value of d1
    // d1 still owns the value (ownership)
    // implicitly typed borrowing

    let r1 = &d1;

    println!("implicitly typed borrowing, d1: {}, r1: {}", d1, r1);

    let d2 = String::from("Louie");

    // r2 is a reference to a String (&String)
    // r2 borrows the value of d2
    // d2 still owns the value (ownership)
    // explicitly typed borrowing

    let r2: &String = &d2;

    println!("explicitly typed borrowing , d2: {}, r2: {}", d2, r2);

    // mutable borrowing - explicitly typed borrowing
    // you can borrow the value mutably via the syntax &mut
    // r3 is a mutable reference to a String

    let mut d3: String = String::from("Dewey");

    // explicitly typed borrowing
    let r3: &mut String = &mut d3;

    // implicitly typed borrowing
    // let r3 = &mut d3;

    r3.push_str(" Duck");

    println!("mutable borrowing, r3: {}", r3);

}