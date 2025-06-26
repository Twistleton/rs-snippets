struct Employee {
    name: String,
    age: i32,
}

impl Employee {
    fn new(name: String, age: i32) -> Employee {
        Employee { name, age }
    }
}

fn main() {
    let employees = vec![
        Employee::new(String::from("Hugo"), 42),
        Employee::new(String::from("Oscar"), 39),
        Employee::new(String::from("Carlo"), 29),
        Employee::new(String::from("William"), 39),
    ];

    let result: Vec<_> = employees
        .iter()
        .filter(|x| x.name.to_lowercase().contains('o'))
        .filter(|x| x.age > 30)
        .map(|x| &x.name)
        .collect();

    println!("result: {:?}", result);

    // result: ["Hugo", "Oscar"]

}