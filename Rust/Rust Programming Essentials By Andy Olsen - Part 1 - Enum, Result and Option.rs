#![allow(dead_code)] // crate-level attribut

fn main() {
    let x: bool = false;

    let y: i32 = x as i32;

    println!("{} = {}", x, y);

    // compile-time constants
    const SECOND_PER_DAY: i32 = 24 * 60 * 60;

    println!("{}", SECOND_PER_DAY);

    // an enum is a datatype that has a closed set of allowed values

    enum Color {
        Red, // variants
        Green,
        Blue,
    }

    let c: Color = Color::Blue;

    match c {
        Color::Red => println!("red"), // arms *)
        #[allow(dead_code)]
        Color::Green => println!("green"),
        #[allow(dead_code)]
        Color::Blue => println!("blue"),
    }

    // *) all the terms in Rust are something to do with crabs (crates, arms)

    enum HouseLocation {
        Number(i32),
        Name(String),
        Unknown,
    }

    let l1: HouseLocation = HouseLocation::Number(123);
    let _l2: HouseLocation = HouseLocation::Name("Downton Abbey".to_string());
    let _l3: HouseLocation = HouseLocation::Unknown;

    match l1 {
        HouseLocation::Number(n) => println!("house location {}", n),
        HouseLocation::Name(s) => println!("house name is {}", s),
        _ => println!("unknown"),
    }

    let size = std::mem::size_of::<HouseLocation>();

    println!("size of HouseLocaton is {}", size);

    let sec: Option<u32> = seconds_of_day(24, 59, 59);

    match sec {
        Some(n) => println!("Seconds of day : {}", n),
        None => println!("Seconds of day : invalid arguments"),
    }

    println!("Seconds of day (unwrap) {}", sec.unwrap_or(0));

    let res: Result<i32, std::num::ParseIntError>;

    let some_str = "FF".to_string();

    res = i32::from_str_radix(&some_str, 16);

    match res {
        Ok(n) => println!("Parsed str as i32: {}", n),
        Err(e) => println!("Error occurred: {}", e),
    }

    let res2: Result<i32, std::num::ParseIntError> = i32::from_str_radix(&some_str, 16);

    println!("Unwrapped result: {}", res2.unwrap_or(-1));
}

fn seconds_of_day(h: u32, m: u32, s: u32) -> Option<u32> {
    if h <= 23 && m <= 59 && s <= 59 {
        let sec = h * 3600 + m * 60 + s;
        Some(sec)
    } else {
        None
    }
}