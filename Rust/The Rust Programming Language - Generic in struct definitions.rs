#[derive(Debug)]
struct Point<T, U> {
    x: T,
    y: U,
}

impl<T, U> Point<T, U> {
    fn x(&self) -> &T {
        &self.x
    }
    fn y(&self) -> &U {
        &self.y
    }
}

fn main() {

    let point = Point { x: 5, y: 4.0 };

    println!("x: {}, y: {}", point.x(), point.y());

}