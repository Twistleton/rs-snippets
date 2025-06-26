fn main() {

    // Arrays

    // This one is type [&str; 2]
    let array1 = ["One", "Two"];

    // But this one is type [&str; 3]. Different type!
    let array2 = ["One", "Two", "Five"];

    // array2.todo();   // compiler error:  no method named `todo` found for array `[&str; 3]` in the current scope

    let my_array = ["a"; 5];
    println!("{:?}", my_array);

    // array of 16 u8 zeroes
    let mut buffer = [0u8; 16];
    println!("{:?}", buffer);

    // Vectors

    let name1 = String::from("Eddie");
    let name2 = String::from("Peppe");

    // let mut my_vec = Vec::new();
    let mut cats: Vec<String> = Vec::new();

    cats.push(name1);
    cats.push(name2);

    println!("{:#?}", cats);

    let mut dogs = vec!["Gromit", "Preston"];

    dogs.push("Lassie");

    println!("{:#?}", dogs);
    println!("capacity of dogs: {}", dogs.capacity());

    dogs.push("Beagle-1");
    dogs.push("Beagle-2");

    println!("capacity of dogs: {}", dogs.capacity());

    // let mut numbers = Vec::new();               // vec without a given capacity
    // if you think you know how many elements you need, you can use Vec::with_capacity() to use less memory and make your program more efficient.
    let mut numbers = Vec::with_capacity(8);       // vec with capacity of 8
    println!("length of numbers: {}, capacity of numbers: {}", numbers.len(), numbers.capacity()); // 0 elements: prints 0

    numbers.push(9);
    println!("length of numbers: {}, capacity of numbers: {}", numbers.len(), numbers.capacity()); // 1 elements: prints 4

    numbers.push(10);
    numbers.push(12);
    numbers.push(20);

    println!("length of numbers: {}, capacity of numbers: {}", numbers.len(), numbers.capacity()); // 4 elements: prints 4

    numbers.push(21);
    println!("length of numbers: {}, capacity of numbers: {}", numbers.len(), numbers.capacity()); // 5 elements: prints 8

    let my_vec1: Vec<u8> = [1, 2, 3].into(); // This makes a Vec<i32>

    let my_vec2: Vec<_> = [9, 0, 10].into();

    // tuples

    // destructuring of a tuple
    let my_tuple = ("one".to_string(), "two".to_string(), "three".to_string());
    let (a, b, c) = my_tuple;
    println!("{a}, {b}");
    // println!("{:?}", strings); // value borrowed here after partial move

}