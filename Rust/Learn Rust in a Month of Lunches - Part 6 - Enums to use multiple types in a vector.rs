// You can use an enum to hold different types inside a collection.

#[derive(Debug)]
enum Container {
    StringContainer(String),
    IntegerContainer(i32),
    BooleanContainer(bool),
}

use Container::*;

fn main() {

    let c1 = StringContainer(String::from("Content for Box A"));
    let c2 = IntegerContainer(42);
    let c3 = BooleanContainer(true);

    let containers = vec![c1, c2, c3];

    println!("{:?}", containers);

    for container in containers {
        open_container(&container);
    }
}

fn open_container(container: &Container) {
    match container {
        StringContainer(s) => println!("content is a String: {}", s),
        IntegerContainer(i) => println!("content is a integer: {}", i),
        BooleanContainer(b) => println!("content is a boolean: {}", b),
    }
}