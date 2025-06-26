fn main() {

    // array - fixed-size homogeneous collection

    let mut arr0: [i32; 5];     // [type, length]

    arr0 = [1,2,3,4,5];

    let arr1 = [1,2,3,4,5];

    let mut arr2 = [1,2,3,4,5];

    println!("{}", arr1.len());

    for elem in arr1 {
        println!("{}", elem);
    }

    // Debug trait - fmt function
    println!("{:?}", arr2);

    // tuple - a fixed-size heterogeneous collecton

    let t1 = (1, "foo", true);

    let mut t2 = (1, "bar", false);

    t2.2 = true;

    println!("{:?}", t2);

    let t3: (i32, String, bool);

    t3 = (42, "foo".to_string(), true);

    println!("t3 elements are {}, {}, {}", t3.0, t3.1, t3.2);

    // empty tuple - for returning nothing from a function

    let t4 = ();


    // Vec<T> is a generic, sequential, resizable collection

    let mut v1: Vec<i32> = Vec::new();

    let mut v2 = Vec::<i32>::new();

    let mut v3 = vec![101, 102, 103];

    let elem = v3[1];          // returns T (will panic if index is out-of-bounds)

    let opt = v3.get(1);       // returns Option<T> - safely access

    match opt {
        Some(n) => println!("Value {}", n),
        None => println!("No Value")
    }

    v3.push(203);  // append
    v3.push(204);
    v3.push(205);
    v3.pop();      // remove the last element

    let optLastElem = v3.pop();  // get the last element and remove it from the vec

    match optLastElem {
        Some(n) => println!("last element of v3 {}", n),
        None => println!("no last element")
    }

    for elem in &v3 {
        println!("{}", elem);
    }

    println!("length of v3: {}", v3.len());

}