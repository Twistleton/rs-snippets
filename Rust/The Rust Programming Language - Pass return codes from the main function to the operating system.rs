use std::error::Error;
use std::fs::File;

// Checks the return code in the terminal of the operating system with: echo $?

fn main() -> Result<(), Box<dyn Error>> {

    println!();
    let greeting_file: File = File::open("hello.txt")?;

    Ok(())
}