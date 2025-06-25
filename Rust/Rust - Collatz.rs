fn main() {

    let mut number = 100;

    while number != 1 {

        number = if number % 2 == 0 {
            number / 2
        } else                 {
            number * 3 + 1
        };
        println!("{}", number);
    }

    println!("finish!");
}