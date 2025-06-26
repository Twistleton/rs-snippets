use std::fs::File;
use std::io::Error;
use std::io::ErrorKind;
use std::io::Read;

fn main() {
    let greeting_file_result: Result<File, Error> = File::open("hello.txt");

    let mut greeting_file = match greeting_file_result {
        Ok(file) => file,
        Err(error) => match error.kind() {
            ErrorKind::NotFound => match File::create("hello.txt") {
                Ok(fc) => fc,
                Err(e) => panic!("Problem creating the file {:?}", e),
            },
            other_error => {
                panic!("Problem opening the file {:?}", other_error);
            }
        },
    };

    let mut file_content = String::new();

    let _ = greeting_file.read_to_string(&mut file_content);

    println!("content: {}", file_content);
}