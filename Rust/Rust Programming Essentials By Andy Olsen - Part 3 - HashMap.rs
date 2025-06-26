use std::collections::HashMap;

fn main() {

    // HashMap<K, V> - a generic dictionary with key/value pairs

    // HashMap<Contry_code, Dialling_code>

    let mut m: HashMap<String, i32> = HashMap::new();

    let mut _m2 = HashMap::<String, i32>::new();

    // if the key always exists, its value is overwritten

    m.insert(String::from("UK"),  0);
    m.insert(String::from("UK"), 44);
    m.insert(String::from("NO"), 47);
    m.insert(String::from("DE"), 49);

    // if you want to insert an item only if the key doesn't always exists

    m.entry(String::from("UK")).or_insert(55);

    // look up a key - panic if key is missing

    let val = m["UK"];

    println!("val: {}", val);

    // look up a key safely

    let _opt = m.get("UK");   // returns Option<V>

    for entry in &m {
        println!("{:?}", entry);
    }

    println!("{:?}", m);

}