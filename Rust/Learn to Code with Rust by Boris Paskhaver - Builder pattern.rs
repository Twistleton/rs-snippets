#[derive(Debug)]
struct Writer {
    name: String,
    nobleprize: bool,
    country: String,
}

impl Writer {
    fn new(name: String, nobleprize: bool, country: String) -> Writer {
        Writer {
            name,
            nobleprize,
            country,
        }
    }
    fn update_nobleprize(&mut self, nobleprize: bool) -> &mut Writer {
        self.nobleprize = nobleprize;
        self
    }
    fn update_country(&mut self, country: String) -> &mut Writer {
        self.country = country;
        self
    }

}

fn main() {

    let mut writer = Writer::new(String::from("William Butler Yeats"),
                                 false,
                                 String::from("Great Britain" ));

    println!("{:#?}", writer);

    let writer = writer.update_nobleprize(true)
        .update_country(String::from("Irland"));

    println!("{:#?}", writer);

}