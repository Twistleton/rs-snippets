fn main() {

    let arr = [5,9,13,15,19];

    // an array slice refers to some (or all) data in an array

    // an array slice contains two pieces of info:
    // - the address of an element in the array
    // - the length of the slice, in elements

    // declare an array slice for the entire array (implicit typed)
    let s1 = &arr;

    println!("s1 - pointer {:p}, len: {}, content: {:?}", s1.as_ptr(), s1.len(), s1);

    // declare an array slice for the entire array (explicitly typed)
    let s2: &[i32] = &arr;

    println!("s2 - pointer {:p}, len: {}, content: {:?}", s2.as_ptr(), s2.len(), s2);

    // declare an array slice for certain elements
    let s3 = &arr[2..4];

    println!("s3 - pointer {:p}, len: {}, content: {:?}", s3.as_ptr(), s3.len(), s3);

    // iterate over an array slice
    for elem in s3 {
        print!("{}\t", elem);
    }

    let mut array = [10, 23, 42];

    let s4: &mut [i32] = &mut array[0..2];

    s4[0] = 11;

    println!("\n{:?}", s4);

}