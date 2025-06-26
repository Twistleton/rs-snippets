struct Duck {
    name: String,
}

//
trait Flyable {
    fn fly(&self) {
        println!("I can fly!");
    }
}

trait Quackable {
    // abstract
    fn quack(&self);
}

trait Swimable {
    fn swim(&self) {
        println!("I can swim!");
    }
}

impl Flyable for Duck {}

impl Quackable for Duck {
    fn quack(&self) {
        println!("{} can quack!", self.name);
    }
}

impl Swimable for Duck {
    // overriding function from trait
    fn swim(&self) {
        println!("{} can swim!", self.name);
    }
}

fn main() {
    let duck = Duck {
        name: "Donald".to_string(),
    };

    duck.fly();
    duck.quack();
    duck.swim();
}