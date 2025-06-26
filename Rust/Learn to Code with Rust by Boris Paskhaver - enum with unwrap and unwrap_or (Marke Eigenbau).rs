#[derive(Debug)]
enum MyOption {
    MySome(i32),
    MyNone,
}

impl MyOption {
    fn unwrap(self) -> i32 {
        match self {
            MyOption::MySome(x) => x,
            MyOption::MyNone => panic!("unwrap on None"),
        }
    }
    fn unwrap_or(self, default: i32) -> i32 {
        match self {
            MyOption::MySome(x) => x,
            MyOption::MyNone => default,
        }
    }
}


fn main() {

    let option_some = MyOption::MySome(1);
    let option_none = MyOption::MyNone;

    println!("option_some: {:?}", option_some);
    println!("option_none: {:?}", option_none);

    // println!("unwrap: {}", option_some.unwrap());
    // println!("unwrap: {}", option_none.unwrap());

    println!("unwrap: {}", option_some.unwrap_or(-1));
    println!("unwrap: {}", option_none.unwrap_or(-1));

}