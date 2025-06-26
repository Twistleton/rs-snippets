fn division(numerator: f64, denominator: f64) -> Result<f64, String> {
    if denominator == 0.0 {
        Err("Division by zero".to_string())
    } else                {
        Ok(numerator / denominator)
    }
}

fn operation(great_success: bool) -> Result<&'static str, &'static str > {
    if great_success {
        Ok("Great success")
    } else {
        Err("Failure")
    }
}

fn main() {

    // let result = division(10.0, 5.0);

    // match result {
    //     Ok(value) => println!("Result: {}", value),
    //     Err(error) => println!("Error: {}", error),
    // }

    // let value = result.unwrap();
    // let value = result.expect("Division by zero");
    // let value = result.unwrap_or(0.0);

    // let is_ok = result.is_ok();
    // let is_err = result.is_err();

    let result = operation(true);

    let content = match result {
        Ok(value) => value,
        Err(error) => error,
    };

    println!("{}", result.unwrap());
    println!("{}", result.unwrap());
    println!("{}", result.unwrap());
    println!("{}", result.unwrap());


}