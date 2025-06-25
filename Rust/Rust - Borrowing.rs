fn greet(name: &mut String) {
    println!("{}", name);
    *name = "Susi".to_string();
}

fn bye(name: String) {
    println!("{}", name);
}

fn main() {
    let mut hugo = "Hugo".to_string();
    greet(&mut hugo);
    bye(hugo);
}