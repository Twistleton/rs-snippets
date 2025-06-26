use once_cell::sync::Lazy;
use chrono::{DateTime, Utc};

use std::thread::sleep;
use std::time::Duration;

fn main() {

    static_init_at_compile_time();

    static_init_at_run_time();

    sleep(Duration::new(5, 0));

    static_init_at_run_time();

}

fn static_init_at_compile_time() {
    static MESSAGE: &str = "Hello world";
    println!("MESSAGE: {}", MESSAGE)
}

fn static_init_at_run_time() {

    println!("Current time {}", Utc::now().format("%T"));

    static TIMESTAMP: Lazy<DateTime<Utc>> = Lazy::new(|| {
        sleep(Duration::new(5, 0));
        let now = Utc::now();
        println!("Current time {}", now.format("%T"));
        return now
    });

    println!("TIMESAMP: {}", (*TIMESTAMP).format("%T"));

}