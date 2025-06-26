#[derive(Debug)]
struct Library {
    name: String,
    books: BookCollection,
}

#[derive(Debug, Clone)]
struct BookCollection(Vec<String>);

impl Library {
    fn add_book(&mut self, book: &str) {
        self.books.0.push(book.to_string());
    }
    fn new(name: &str) -> Self {
        Self {
            name: name.to_string(),
            books: BookCollection(Vec::new()),
        }
    }
    fn get_books(&self) -> BookCollection {
        self.books.clone()
    }
}

fn main() {
    let mut my_library = Library::new("Calgary");

    my_library.add_book("Krieg und Frieden");

    println!("{my_library:?}");
}