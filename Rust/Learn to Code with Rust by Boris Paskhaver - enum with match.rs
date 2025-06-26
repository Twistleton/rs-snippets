
enum LaundryCycle {
    Cold,
    Hot { temperature: u32 },
    Delicate(String),
}

fn main() {
    wash_laundry(LaundryCycle::Cold);
    wash_laundry(LaundryCycle::Hot { temperature: 25 });
    wash_laundry(LaundryCycle::Delicate("Cotton".to_string()));
}

fn wash_laundry(cycle: LaundryCycle) {

    match cycle {
        LaundryCycle::Cold => {
            println!("Running the laundry with cold temperature");
        }
        LaundryCycle::Hot { temperature} => {
            println!("Running the laundry with hot temperature of {temperature}");
        }
        LaundryCycle::Delicate(fabric_type) => {
            println!("Running the laundry with {fabric_type}");
        }
    }
}