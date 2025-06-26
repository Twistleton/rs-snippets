enum Direction {
    North,
    West,
    East,
    South,
}

impl Direction {
    fn check(&self) {
        match self {
            Direction::North => println!("To north ..."),
            Direction::West => println!("To west ..."),
            Direction::East => println!("To east ..."),
            Direction::South => println!("To south ..."),
        }
    }
}

fn main() {
    let my_direction = Direction::North;
    my_direction.check();
}