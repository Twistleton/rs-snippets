fn main() {


    // Copying vs moving

    // Copying - simple types implement the Copy trait.
    // When you assign, it bit-copies the value.

    let mut a = 42;

    let b = a;

    a = 43;

    println!("{}, {}", a, b);

    // Moving - other types don't implement the Copy trait.
    // When you assign, it moves the value (i. e.) transfers ownership).
    // the original variable is invalidated.

    // copies only the pointer, s1 lost the ownership, s2 acquired the owership

    let s1 = String::from("Hello");
    let s2 = s1;

    println!("{}", s2);
    // println!("{}", s1);   // compiler error

    // Clone trait - makes a deep copy
    // String implements the Clone trait.

    let mut t1 = String::from("Hello");
    let t2 = t1.clone();

    t1.push_str(" World!");

    println!("{}, {}", t1, t2);     // Output: Hello World, Hello


}