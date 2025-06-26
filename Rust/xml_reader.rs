/*

[package]
name = "xml_reader"
version = "0.1.0"
edition = "2021"

# See more keys and their definitions at https://doc.rust-lang.org/cargo/reference/manifest.html

[dependencies]
xml = "0.8"
regex = "1"

*/

use std::env;
use std::path::Path;
use std::process;
use std::fs::File;
use std::io::BufReader;
use regex::Regex;
use xml::reader::{EventReader, XmlEvent};

#[allow(unused_variables)]


fn main() -> std::io::Result<()> {

    let args: Vec<String> = env::args().collect();

    if !Path::new(&args[1]).exists() {
        eprintln!("File not found: {:?}", &args[1]);
        process::exit(1)
    }

    let file = File::open(&args[1])?;

    let file = BufReader::new(file); // Buffering is important for performance

    let mut station = "".to_string();
    let mut material = "".to_string();
    let mut orders = Vec::new();
    let parser = EventReader::new(file);
    for e in parser {
        match e {
            Ok(XmlEvent::StartElement {
                   name, attributes, ..
               }) => {
                let t: String = name.local_name;

                if attributes.is_empty() == true {
                    //"test".to_string()
                } else {
                    for att in attributes {
                        //let tet: xml::name::OwnedName = att.name;
                        let temp_name_local_name = att.name.local_name.clone();
                        let temp_value = att.value.clone();

                        //                        println!("{} {} {}", t, temp_name_local_name, temp_value);

                        if t == "customer_order" {
                            orders.push(temp_value.clone());
                        }

                        if t == "station" {
                            station = temp_value.clone();
                        }

                        if t == "id_batch" {
                            material = temp_value.clone();
                        }
                    }
                }

                // [OwnedAttribute { name: OwnedName { local_name: "value", namespace: None, prefix: None }, value: "40303.002.22 " }]
            }
            Ok(XmlEvent::EndElement { name }) => {
                //                println!("{:spaces$}-{name}", "", spaces = depth * 2);
            }
            Err(e) => {
                eprintln!("Error: {e}");
                break;
            }
            // There's more: https://docs.rs/xml-rs/latest/xml/reader/enum.XmlEvent.html
            _ => {}
        }
    }

    let material = format!(
        "442{}00",
        material
            .chars()
            .take_while(|&ch| ch != '.')
            .collect::<String>()
    );

    // println!("material   : {} ", material);
    // println!("station    : {} ", station);
    for order in orders {
        let order_number = &order[..6];
        //        println!("order_number: {}", order_number);

        let reg_order_number = Regex::new(r"^\d{6}$").unwrap();
        let reg_material = Regex::new(r"^\d{10}$").unwrap();


        if reg_order_number.is_match(order_number) && reg_material.is_match(&material)  {
            println!("{} {} {}", order_number, material, station);
        }

    }

    Ok(())
}