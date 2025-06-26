fn largest<T: std::cmp::PartialOrd>(list: &[T]) -> &T {

    let mut largest = &list[0];

    for item in list.iter() {
        if item > largest {
            largest = item;
        }
    }
    largest
}

fn main() {
    let number_list = vec![34, 50, 25, 100, 65];

    let result = largest(&number_list);
    println!("The largest number is {result}");

    let char_list = vec!['a', 'c', 'e', 'x'];

    let result = largest(&char_list);
    println!("The largest char is {result}");


    let bool_list = vec![true, false, false, true];

    let result = largest(&bool_list);
    println!("The largest boolean is {result}");

}