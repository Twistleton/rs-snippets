use std::{thread, time};

fn main() {
    // Erstellt den ersten Thread
    let handle1 = thread::spawn(|| {
        for i in 1..=5 {
            let ten_millis = time::Duration::from_millis(10);
            thread::sleep(ten_millis);
            println!("Thread 1: {}", i);
        }
    });

    // Erstellt den zweiten Thread
    let handle2 = thread::spawn(|| {
        for i in 1..=5 {
            let ten_millis = time::Duration::from_millis(10);

            thread::sleep(ten_millis);
            println!("Thread 2: {}", i);
        }
    });

    // Warten auf die Beendigung der beiden Threads
    handle1.join().unwrap();
    handle2.join().unwrap();

    println!("Threads fertig.");
}