fn main() {

    // The Rust compiler has a borrow checker - enforces Rust's strict rules for borrowing

    // The borrow checker enforces these rules:
    //  - many readers (immutable borrow)
    //  - single write (mutable borrow)

    // -------------------------------------------------------------------------
    // immutable borrow

    let s = String::from("borrow checker");


    let r1 = &s;    // r1 is a immutable reference
    let r2 = &s;    // r2 is a immutable reference
    let r3 = &s;    // r3 is a immutable reference

    println!("r1: {}, r2: {}, r3: {}", r1, r2, r3);

    // -------------------------------------------------------------------------
    // mutable borrow

    let mut t = String::from("borrow checker");

    let u1 = &mut t;

    // You can't define another mutable reference r2
    // let u2 = &mut t;  // cannot borrow `t` as mutable more than once at a time

    // let u3 = &t; // cannot borrow `t` as immutable because it is also borrowed as mutable

    u1.push_str(" helps you!");

    // println!("{}", t);  // not allow

    println!("{}", u1);

}