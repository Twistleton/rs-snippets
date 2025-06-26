pub trait Flyable {
    fn fly(&self) -> String;

    fn fly_high(&self) -> String {
        self.fly()
    }
}

// default Implementations
pub trait Quackable {
    fn quack(&self) -> String {
        format!("Quack, quack ... ")
    }
}

pub struct Duck {
    pub name: String,
    pub age: i32,
}

impl Flyable for Duck {
    fn fly(&self) -> String {
        format!("{} is my name. I'm a duck, {} years old and I can fly.", &self.name, &self.age)
    }
}

impl Quackable for Duck {
    // overriding quack for Duck
    fn quack(&self) -> String {
        format!("{} is my name. I'm a duck, {} years old I can quack.", &self.name, &self.age)
    }
}

pub struct Frog {
    pub name: String,
}

impl Flyable for Frog {
    fn fly(&self) -> String {
        format!("{} is my name. I'm a frog and I can very bad fly.", &self.name)
    }
}

impl Quackable for Frog {
    // use the default Implementations
    // fn quack(&self) -> String {
    //     format!("{} is my name. I'm a frog and I can quack.", &self.name)
    // }
}

fn main() {

    let donald = Duck {
        name : "Donald Duck".to_string(),
        age: 23,
    };

    let fritz = Frog {
        name : "Fritz Frosch".to_string()
    };

    fly(&donald);
    fly(&fritz);

    quack(&donald);
    quack(&fritz);

    // fly_high() calls fly()
    println!("fly_high: {}", donald.fly_high());
    println!("fly_high: {}", fritz.fly_high());

}

fn fly<T: Flyable>(somebody: &T) {
    println!("{}", somebody.fly());
}

fn quack<T: Quackable>(somebody: &T) {
    println!("{}", somebody.quack());
}