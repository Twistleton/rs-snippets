use std::num::ParseIntError;

// Option         Result
// --------       ------
// Some(x)  <-->  Ok(x)
// None     <-->  Err(x)


fn main() {

    // Convert an Option<i32> to a Result<i32, bool>

    match set_option_get_result() {
        Ok(n) => println!("Option - Some() -> Result - Ok:  {}", n),
        Err(b) => println!("Option - None -> Result - Err: {}", b),
    };

    // Convert an Result<i32, ParseIntError> to Option<i32>

    match set_result_get_option() {
        Some(n) => println!("Result -> Option - Some: {n}"),
        None => println!("Result -> Option - None"),
    };
}

fn set_option_get_result() -> Result<i32, bool> {
    // let x: Option<i32> = Some(5);
    let x: Option<i32> = None;
    x.ok_or_else(|| true)
}

fn set_result_get_option() -> Option<i32> {
    let result: Result<i32, ParseIntError> = "42".parse::<i32>();
    result.ok()
}