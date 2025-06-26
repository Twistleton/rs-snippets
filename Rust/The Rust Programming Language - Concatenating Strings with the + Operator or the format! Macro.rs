fn main() {

    // concat with the + Operator

    let s1 = String::from("Hello, ");
    let s2 = String::from("world!");

    let s3 = s1 + &s2;

    // println!("{}", s1);   // error: move ownership
    println!("{}", s2);
    println!("{}", s3);

    // concat with format! Macro

    let t1 = "Hello, ".to_string();
    let t2 = "world!".to_string();

    let t3 = format!("{}{}", t1, t2);

    println!("{}", t1);
    println!("{}", t2);
    println!("{}", t3);

}