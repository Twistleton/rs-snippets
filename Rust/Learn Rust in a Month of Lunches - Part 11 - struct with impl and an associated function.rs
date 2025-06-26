struct Book {
    number: u32,
}

impl Book {
    fn get_number(&self) -> u32 {
        self.number
    }

    fn change_number(&mut self, new_number: u32) {
        self.number = new_number;
    }

    //  associated function
    fn new(number: u32) -> Self {
        Self { number: number }
    }
}

fn main() {
    let mut my_book = Book::new(50);

    my_book.change_number(60);

    println!("{}", my_book.get_number());
}