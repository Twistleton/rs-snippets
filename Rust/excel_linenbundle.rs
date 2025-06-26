/*  Cargo.toml


[package]
name = "excel_linenbundle"
version = "0.1.0"
edition = "2021"

# See more keys and their definitions at https://doc.rust-lang.org/cargo/reference/manifest.html

[dependencies]
umya-spreadsheet = "0.9.2"
hyper = { version = "0.14", features = ["full"] }
tokio = { version = "1", features = ["full"] }
serde_json = "1.0"


*/

use std::env;
use hyper;
use serde_json::json;
use umya_spreadsheet::*;

#[allow(dead_code)]
struct Row {
    col_a: String,
    col_b: String,
    col_c: String,
    col_d: String,
    col_e: String,
    col_f: String,
    col_g: String,
    col_h: String,
    col_i: String,
    col_j: String,
}

const WS_SERVER: &str = "http://rbsvapplserv01:9050/api";

#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error + Send + Sync>> {

    // args
    let args: Vec<String> = env::args().collect();

    if args.len() != 2 {
        eprintln!("Error - no file name was provided");
        std::process::exit(1);
    }

    let file = &args[1];

    // reader
    let path = std::path::Path::new(file);
    let book: Spreadsheet = reader::xlsx::read(path).unwrap();
    let sheet = book.get_sheet(&0).unwrap();

    // vector
    let mut rows: Vec<Row> = Vec::new();
    let mut i = 1;

    loop {
        i += 1;

        let col_a: String = sheet.get_value(format!("A{}", i));
        let col_b: String = sheet.get_value(format!("B{}", i));
        let col_c: String = sheet.get_value(format!("C{}", i));
        let col_d: String = sheet.get_value(format!("D{}", i));
        let col_e: String = sheet.get_value(format!("E{}", i));
        let col_f: String = sheet.get_value(format!("F{}", i));
        let col_g: String = sheet.get_value(format!("G{}", i));
        let col_h: String = sheet.get_value(format!("H{}", i));
        let col_i: String = sheet.get_value(format!("I{}", i));
        let col_j: String = sheet.get_value(format!("J{}", i));

        let col_b = format!("{:0>10}", col_b);

        let row = Row {
            col_a,
            col_b,
            col_c,
            col_d,
            col_e,
            col_f,
            col_g,
            col_h,
            col_i,
            col_j,
        };

        if row.col_a == "" {
            break;
        }

        if i == 2 {
            // http delete request
            let client = hyper::Client::new();
            let uri = format!("{}/bundles/{}", WS_SERVER, row.col_a);
            let req = hyper::Request::builder()
                .method(hyper::Method::DELETE)
                .uri(uri)
                .body(hyper::Body::from(""))?;
            let res = client.request(req).await?;
            println!("res: {:?}", res);
        }

        if !row.col_a.trim().is_empty() &&
            !row.col_b.trim().is_empty() &&
            !row.col_f.trim().is_empty() &&
            !row.col_g.trim().is_empty() &&
            !row.col_h.trim().is_empty() {
            rows.push(row);
        }
    }

    for row in rows.iter() {
        let client = hyper::Client::new();
        let uri = format!("{}/bundle", WS_SERVER);

        let program = row.col_a.clone().parse::<i32>().unwrap();
        let model = row.col_b.clone();
        let bundle_type = row.col_f.clone().parse::<i32>().unwrap();
        let bundle_amount = row.col_g.clone().parse::<i32>().unwrap();
        let bundle_number = row.col_h.clone();
        let bundle_note = row.col_i.clone();
        let bundle_storage = row.col_j.clone();

        let bundle_note_valid = if bundle_note > "".to_string() {
            true
        } else {
            false
        };

        let bundle_storage_valid = if bundle_storage > "".to_string() {
            true
        } else {
            false
        };

        let body = json!({
             "program":        program,
             "model":          model,
             "type":           bundle_type,
             "bundle_amount":  bundle_amount,
             "bundle_number":  bundle_number,
             "bundle_storage": {"String": bundle_storage, "Valid": bundle_storage_valid},
             "bundle_note":    {"String": bundle_note, "Valid": bundle_note_valid}
        });

        let serialized = serde_json::to_string_pretty(&body).unwrap();

        // http POST request
        let req = hyper::Request::builder()
            .method(hyper::Method::POST)
            .uri(uri)
            .body(hyper::Body::from(serialized.clone()))?;

        let res = client.request(req).await?;

        let status = res.status();

        println!("http-status: {}", res.status());

        if status != 200 {
            println!("{}", serialized);
        }
    }

    Ok(())
}