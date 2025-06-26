use std::fs::File;
use std::io::ErrorKind;
use std::io::Read;

fn main() {
    let mut greeting_file = File::open("hello.txt").unwrap_or_else(|error| {
        if error.kind() == ErrorKind::NotFound {
            File::create("hello.txt").unwrap_or_else(|error| {
                panic!("Problem creating the file: {:?}", error);
            })
        } else {
            panic!("Problem opening the file: {:?}", error);
        }
    });


    let mut file_content = String::new();

    let _ = greeting_file.read_to_string(&mut file_content);

    println!("content: {}", file_content);
}