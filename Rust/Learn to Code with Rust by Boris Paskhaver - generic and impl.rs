
#[derive(Debug)]
struct TreasureChest<T> {
    captain: String,
    treasure: T,
}

// implementation only for String
impl TreasureChest<String> {
    fn add_treasure(&mut self, treasure: String) {
        self.treasure.push_str(&treasure);
    }
    fn clean_treasure(&mut self) {
        self.treasure = self.treasure.trim().to_string()
    }
}

// implementation only for 3 element arrays of string slice - [&str;3]
impl TreasureChest<[&str; 3]> {
    fn amount_of_treasure(&self) -> usize {
        self.treasure.len()
    }
    fn capitalize_captain(&mut self) {
        self.captain = self.captain.to_uppercase();
    }
}

impl<T> TreasureChest<T> {
    fn get_treasure(&self) -> &T {
        &self.treasure
    }
}

fn main() {

    let mut my_gold_treasure_chest = TreasureChest {
        captain: String::from("Captain"),
        treasure: String::from("   Gold       ")
    };

    my_gold_treasure_chest.clean_treasure();
    my_gold_treasure_chest.add_treasure(String::from("+Silver"));

    println!("My treasure chest contains {}.", my_gold_treasure_chest.treasure);

    let mut my_treasure_chest = TreasureChest {
        captain: String::from("Captain"),
        treasure: ["Gold", "Silver", "Platinum"]
    };

    my_treasure_chest.capitalize_captain();

    println!("My treasure chest {:?}", my_treasure_chest);

    println!("My treasure chest contains {}.", my_treasure_chest.amount_of_treasure());
    println!("My treasure chest contains {:?}.", my_treasure_chest.treasure);

    let my_treasure = my_treasure_chest.get_treasure();

    println!("My treasure is {:?}.", my_treasure);

}