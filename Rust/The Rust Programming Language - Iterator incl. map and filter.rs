fn main() {

    // https://doc.rust-lang.org/std/iter/trait.Iterator.html

    // iterate over references

    let v1 = vec![1, 2, 3];

    let v1_iter = v1.iter();

    for item in v1_iter {
        println!("item: {}", item);
    }

    // iterator that takes ownership

    let v1 = vec![1, 2, 3];

    let v1_into_iter = v1.into_iter();

    for item in v1_into_iter {
        println!("{}", item);
    }

    // v1 is out of scope
    // println!("{:?}", v1);

    // iterate over mutable references
    let mut v1 = vec![1, 2, 3];

    let v1_iter_mut = v1.iter_mut();

    for item in v1_iter_mut {
        *item += 1;
    }

    println!("{:?}", v1);

    // methods that consume the iterator

    let v1 = vec![1, 2, 3];

    let v1_iter = v1.iter();

    let total: i32 = v1_iter.sum();

    println!("total: {}", total);

    //  map

    let v1: Vec<i32> = vec![1, 2, 3];

    let v2: Vec<_> = v1.iter().map(|x| x + 1).collect();

    println!("v2 - map: {:?}", v2);

    // filter

    let v1: Vec<i32> = vec![1, 2, 3, 4, 5];

    let v2: Vec<_> = v1.into_iter().filter(|x| x % 2 == 0).collect();

    println!("v2 - filter: {:?}", v2);

}