/*

Cargo.toml

[package]
name = "watch-dog"
version = "0.1.0"
edition = "2021"

# See more keys and their definitions at https://doc.rust-lang.org/cargo/reference/manifest.html

[dependencies]
notify = "6.0.1"
xml = "0.8"
regex = "1"
chrono = "0.4.26"

*/

use notify::*;
use notify::EventKind::Create;
use notify::event::CreateKind::Any;
use std::{path::Path, time::Duration};
use std::process::Command;
use std::path::PathBuf;
use std::fs;
use chrono::prelude::*;

fn main() {

    println!("[/root/Documents/rust/watch-dog]");

    let (tx, rx) = std::sync::mpsc::channel();

    let mut watcher: Box<dyn Watcher> = {
        let config = Config::default().with_poll_interval(Duration::from_secs(1));
        Box::new(PollWatcher::new(tx, config).unwrap())
    };

    // watch path
    watcher
        .watch(Path::new("/home/host2pc/gemini/ED/Output/"), RecursiveMode::NonRecursive)
        .unwrap();

    // loop forever
    for e in rx {
        println!("{:?}", e);
        match e {
            Ok(e) => match e.kind {
                // Access(Close(_)) => process_file(e.paths.clone().into_iter().next()),
                Create(Any) => process_file(e.paths.clone().into_iter().next()),
                _        => println!(),
            }
            Err(err)  => println!("{}", err),
        }
    }
}

fn process_file(option_path_buf: Option<PathBuf>) {
    match option_path_buf {
        Some(path_buf) if path_buf.file_name().unwrap().to_string_lossy().contains("bundling_started")  => parse_file(path_buf),
        _  => println!(""),
    }
}

fn parse_file(path_buf: PathBuf)  {

    println!("[/root/Documents/rust/xml_reader] - parse file {:?}", path_buf);

    let output = Command::new("/root/Documents/rust/xml_reader/target/release/xml_reader")
        .arg(path_buf.clone())
        .output()
        .expect("failed to execute process");

    let result = String::from_utf8(output.stdout).unwrap();
    for line in result.split("\n")  {
        println!("{}", line);

        if line.len() > 0 {
            println!("[/root/Documents/gemini/GeminiOutput/GeminiOutput.sh]");
            let gemini_output = Command::new("/root/Documents/gemini/GeminiOutput/GeminiOutput.sh")
                .arg(line)
                .output()
                .expect("failed to execute java jar process");

            let gemini_result = String::from_utf8(gemini_output.stderr).unwrap();

            for gemini_line in gemini_result.split("\n") {
                println!("{}", gemini_line);
            }

        }
    }

    let local = Local::now();

    let now = local.format("%Y-%m-%d_%H.%M.%S.%f").to_string();

    let result = fs::rename(path_buf.clone(), format!("/home/host2pc/gemini/ED/Processed Kommissionier ABs/{}_{}.xml", path_buf.file_name().unwrap().to_string_lossy(), now));

    println!("result fs::rename {:?}", result);

}