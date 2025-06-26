use std::{fs, io};

fn main() {

    let result: Result<String, io::Error> = read_username_from_file();

    match result {
        Ok(s) => println!("string: {}", s),
        Err(e) => println!("error : {}", e)
    };

}

fn read_username_from_file() -> Result<String, io::Error> {
    fs::read_to_string("hello.txt")
}