use std::num::ParseIntError;

// After anything that returns a Result, you can add ?. This will:
// * give what is inside the Result if it is Ok
// * pass the error back if it is Err (this is called an early return)

fn parse_and_log_str(input: &str) -> Result<i32, ParseIntError> {
    let parsed_number = input.parse::<i32>()?;   // early return if a errror occured
    println!("Number parsed successfully into {parsed_number}");
    Ok(parsed_number)
}

fn main() {

    let my_vec = vec!["Seven", "7", "Nine", "9", "10"];

    for number in my_vec {
        let result = parse_and_log_str(number);
        println!("check {}, result: {:?}", number, result);
    }

}