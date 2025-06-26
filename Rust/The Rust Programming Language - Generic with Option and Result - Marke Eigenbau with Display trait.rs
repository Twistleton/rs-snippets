use std::fmt;

enum Vielleicht<T>{
    Etwas(T),
    Nichts,
}

impl<T: fmt::Display> fmt::Display for Vielleicht<T> {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        match self {
            Vielleicht::Etwas(wert) => write!(f, "Etwas({})", wert),
            Vielleicht::Nichts => write!(f, "Nichts"),
        }
    }
}

enum Ergebnis<T, E>{
    Erfolg(T),
    Fehler(E),
}

impl<T: fmt::Display, E: fmt::Display> fmt::Display for Ergebnis<T, E> {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        match self {
            Ergebnis::Erfolg(wert) => write!(f, "Erfolg({})", wert),
            Ergebnis::Fehler(fehler) => write!(f, "Fehler({})", fehler),
        }
    }
}

fn main() {

    let x: Vielleicht<i32> = Vielleicht::Etwas(32);
    let y: Vielleicht<i32> = Vielleicht::Nichts;

    println!("x: {}", x);
    println!("y: {}", y);

    let erfolg_enum: Ergebnis<i32, String> = Ergebnis::Erfolg(32);
    let fehler_enum: Ergebnis<i32, String> = Ergebnis::Fehler("error occurred".to_string());

    println!("erfolg_enum: {}", erfolg_enum);
    println!("fehler_enum: {}", fehler_enum);

}