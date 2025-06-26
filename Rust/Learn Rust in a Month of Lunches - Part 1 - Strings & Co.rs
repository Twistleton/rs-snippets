// const is for values that don't change, and are created at compile time
const NUMBER_OF_MONTHS: u32 = 12;

// static is similar to const, but has a fixed memory location. It might not be created at compile time
static SEASONS: [&str; 4] = ["Spring", "Summer", "Fall", "Winter"];

fn main() {
    // create a string slice

    let slice = "Hello 🦀";

    let size_of_slice = std::mem::size_of_val(slice);

    println!("A string slice has {size_of_slice} bytes. A real bytes of the string slice.");

    // create a String

    let s1 = "Hello 🦀".to_string();
    let s2 = String::from("Hello 🦀");
    let s3: String = "Hello 🦀".into();

    let crab = "🦀";
    let s4 = format!("Hello {crab}");

    println!("{s1}, {s2}, {s3}, {s4}");

    let size_of_string = std::mem::size_of::<String>();

    let size_of_s1 = std::mem::size_of_val(&s1);

    println!("A String is sized and always {size_of_string} bytes on the stack. The text itself is on the heap.");
    println!("s1 is a String and has {size_of_s1} bytes.");

    print_months()
}

fn print_months() {
    println!("Number of months in the year: {NUMBER_OF_MONTHS}");
}