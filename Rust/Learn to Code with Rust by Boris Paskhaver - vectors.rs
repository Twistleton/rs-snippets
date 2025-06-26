
fn main() {

    // create an empty vector

    let pizza_diameters: Vec<i32> = Vec::new();
    let mut pizza_diameters = Vec::<i32>::new();   // with turbofish operator

    // add elements
    pizza_diameters.push(12);
    pizza_diameters.push(14);
    pizza_diameters.push(16);

    println!("{:?}", pizza_diameters);

    let pastas: Vec<&str> = Vec::new();
    let mut pastas = Vec::<&str>::new();           // with turbofish operator

    pastas.push("macarronada");
    pastas.push("calabresa");
    pastas.push("brasileira");

    println!("{:?}", pastas);

    // vec! macro
    let pizza_diameters = vec![12, 14, 16];
    let mut pastas = vec!["macarronada", "calabresa", "brasileira"];

    println!("{:?}", pizza_diameters);
    println!("{:?}", pastas);

    pastas.insert(1, "portuguesa");
    println!("{:?}", pastas);

    let option = pastas.pop();      // last element
    println!("{:?}", option);

    let remove_element = pastas.remove(1);                     // remove element at index n
    println!("{pastas:?}");
    println!("{:?}", remove_element);

    let pepperoni = String::from("pepperoni");
    let mushrooms = String::from("mushrooms");
    let salami = String::from("salami");

    // vec with string slice reference
    let pizza_toppings = vec![&pepperoni, &mushrooms, &salami];
    println!("{:?}", pizza_toppings);
    println!("pepperoni: {}", pepperoni);

    let pizza_toppings = vec![pepperoni, mushrooms, salami];
    println!("{:?}", pizza_toppings);
    // println!("pepperoni: {}", pepperoni);  // value borrowed here after move

    let mut my_toppings = pizza_toppings;

    // replace element
    my_toppings[1] = String::from("green peppers");
    println!("{:?}", my_toppings);

    // vector with capacity
    let mut season: Vec<&str> = Vec::with_capacity(4);

    println!("Length  : {}, Capacity: {}", season.len(), season.capacity());

    season.push("spring");
    season.push("summer");

    println!("Length  : {}, Capacity: {}", season.len(), season.capacity());

    season.push("fall");
    season.push("winter");

    println!("Length  : {}, Capacity: {}", season.len(), season.capacity());

    season.push("spring");
    println!("Length  : {}, Capacity: {}", season.len(), season.capacity());


}