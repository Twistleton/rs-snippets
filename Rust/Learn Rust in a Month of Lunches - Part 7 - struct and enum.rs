#[derive(Debug)]
enum Climate {
    Tropical,
    Dry,
    Temperate,
    Continental,
    Polar,
}

#[derive(Debug)]
struct Country {
    population: u32,
    capital: String,
    leader_name: String,
    climate: Climate,
    king_or_queen: Option<String>,
}

fn main() {

    let finland = Country {
        population: 5_614_571,
        leader_name: String::from("Sauli Niinistö"),
        capital: String::from("Helsinki"),
        climate: Climate::Polar,
        king_or_queen: None,
    };

    println!("{:#?}", finland);
}