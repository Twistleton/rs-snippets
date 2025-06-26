
struct Song {
    title: String,
    artist: String,
    album: String,
    release_year: u32,
    duration: u32,
}


// explicit             implicit             ownership or borrowing?
//
// self: Self           self                 takes ownership - immutable
// mut self: Self       mut self             takes ownership - mutable
//
// self: &Self          &self                borrowing - immutable
// self: &mut Self      &mut self            borrowing - mutable


impl Song {

    // associated function - Rust will know it is a associated function if there is no "self" keyword
    fn new(title: String, artist: String, album: String, release_year: u32, duration: u32) -> Self {
        Self {
            title,
            artist,
            album,
            release_year,
            duration,
        }
    }

    // borrowing - immutable
    fn display_song(&self) {
        println!("{} by {} from {} ({}) with the duration {} seconds", self.title, self.artist, self.album, self.release_year, self.duration);
    }

    // borrowing - mutable
    fn double_duration(&mut self) {
        self.duration *= 2;
    }

    fn is_long_than(&self, other: &Self) -> bool {
        self.duration > other.duration
    }

    fn years_since_release(&self) -> u32 {
        2025 - self.release_year
    }

}

fn main() {

    let mut echolon = Song::new(
        String::from("Echolon"),
        String::from("Leech"),
        String::from("If we get there one day, would you please open the gates?"),
        2021,
        350);

    let october = Song::new(
        String::from("October"),
        String::from("Leech"),
        String::from("If we get there one day, would you please open the gates?"),
        2021,
        800);

    echolon.display_song();

    echolon.double_duration();

    echolon.display_song();
    october.display_song();

    let is_longer = echolon.is_long_than(&october);
    println!("Is echolon longer than october? {}", is_longer);

    println!("echolon is {} years since release", echolon.years_since_release());

}