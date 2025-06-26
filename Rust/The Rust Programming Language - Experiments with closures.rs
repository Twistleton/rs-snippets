use std::{thread, time};

fn main() {

    let annotated_closure = |num: u32| -> u32 {
        println!("calculating slowly with annotated_closure ...");
        thread::sleep( time::Duration::from_secs(2));
        num
    };

    let pure_closure = |num| {
        println!("calculating slowly by pure_closure ...");
        thread::sleep( time::Duration::from_secs(2));
        num
    };


    let add_one_closure = |x| x + 1;

    let my_closure = |x| x;

    annotated_closure(10);

    pure_closure(10);

    println!("add_one_fn      : {}", add_one_fn(9));
    println!("add_one_closure : {}", add_one_closure(9));

    // Call a closure with two different types is not possiable
    println!("my_closure      : {}", my_closure(String::from("Hugo")));
    // println!("my_closure      : {}", my_closure(42));

}

fn add_one_fn(x: i32) -> i32 {
    x + 1
}