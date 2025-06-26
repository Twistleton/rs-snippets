use std::collections::BTreeMap;

struct City {
    name: String,
    population: BTreeMap<i32, i32>,
}

fn main() {
    let mut tallinn = City {
        name: "Tallinn".to_string(),
        population: BTreeMap::new(),
    };

    tallinn.population.insert(2020, 437_619);
    tallinn.population.insert(1372, 3_250);
    tallinn.population.insert(1851, 24_000);

    for (year, population) in &tallinn.population {
        println!("In the year {year} the city of Tallinn had a population of {population}.");
    }

    let mut year = 1372;

    if let Some(population) = &tallinn.population.get(&year) {
        println!("The population was {}", population);
    }

    // get all entries for a given range
    for i in 1500..=2020 {
        if let Some(population) = &tallinn.population.get(&i) {
            println!("In the year {} the city of Tallin had a population of {}", i, population);
        }
    }

    year = 1492;

    if let None = &tallinn.population.get(&year) {
        println!("No value for the population is {}", &year);
    }

    println!("{}", &tallinn.population[&1372]);     // Be careful, because the program will crash if there is no key
    // println!("{}", &tallinn.population[&1492]);  //  panicked
}