use chrono::{Duration, Local};
use simple_logger::SimpleLogger;

fn main() {

    SimpleLogger::new().with_local_timestamps().init().unwrap();

    let dt = Local::now() - Duration::days(1);
    let s = dt.format("%d.%m.%Y").to_string();
    log::info!("{}", s);

}