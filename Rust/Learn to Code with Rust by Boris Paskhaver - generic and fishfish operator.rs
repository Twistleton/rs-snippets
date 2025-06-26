#[derive(Debug)]
struct Point {
    x: i32,
    y: i32,
}

fn main() {

    let x: i32 = identity(5);

    let x: i64 = identity(5);

    let x: bool = identity(true);

    // turbofish operator
    let x: u8 = identity::<u8>(5);

    let p = Point { x: 0, y: 7 };

    let result = identity::<Point>(p);

}

// generic function
fn identity<T>(x: T) -> T {
    x
}