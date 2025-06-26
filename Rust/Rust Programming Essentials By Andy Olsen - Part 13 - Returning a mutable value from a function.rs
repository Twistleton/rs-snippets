fn main() {

    // returning a reference from a function

    let mut s = String::from("Hugo");

    let r = f(&mut s);

    println!("{}", r);
    println!("{}", s);

}

fn f(s: &mut String) -> &String {
    s.push_str("!!!");
    return s;
}