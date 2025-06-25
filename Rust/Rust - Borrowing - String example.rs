fn greet(name: &mut String) {
    println!("{}", name);
    *name = "Oscar".to_string();
}

fn bye(name: String) {
    println!("{}", name);
}

fn main() {
    let a: String = "Hi".into();
    println!("{}", a);

    let mut hugo = "Hugo".to_string();
    greet(&mut hugo);
    bye(hugo);
}