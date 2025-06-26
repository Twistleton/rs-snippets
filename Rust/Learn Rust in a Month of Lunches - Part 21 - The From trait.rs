#[derive(Debug)]
struct City {
    name: String,
    population: u32,
}

impl City {
    fn new(name: &str, population: u32) -> Self {
        Self {
            name: name.to_string(),
            population,
        }
    }
}

#[derive(Debug)]
struct Country {
    cities: Vec<City>,
}

impl From<Vec<City>> for Country {
    fn from(cities: Vec<City>) -> Self {
        Self { cities }
    }
}
impl Country {
    fn print_cities(&self) {
        for city in &self.cities {
            println!("{:?} has a population of {:?}.", city.name, city.population);
        }
    }
}
fn main() {

    let helsinki = City::new("Helsinki", 631_695);
    let turku = City::new("Turku", 186_756);
    let finland_cities = vec![helsinki, turku];

    let finland: Country = Country::from(finland_cities);
    finland.print_cities();

    // "Helsinki" has a population of 631695.
    // "Turku" has a population of 186756.

}