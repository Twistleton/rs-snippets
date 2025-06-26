#[derive(Debug)]
enum OnlineOrderStatus {
    Ordered,
    Packed { tracking_number: String},
    Shipped{ tracking_number: String},
    Delivered(bool)
}

impl OnlineOrderStatus {
    fn check_status(&self) {
        match self {
            OnlineOrderStatus::Packed { tracking_number} | OnlineOrderStatus::Shipped{ tracking_number } => println!("Your item is on its way!"),
            order_status => println!("Your item is still being processed, current state is {order_status:?}")
        }
    }
}

fn main() {

    let my_order = OnlineOrderStatus::Packed { tracking_number: String::from("1234567890")};

    if let OnlineOrderStatus::Packed { tracking_number} = my_order {
        println!("Packed");
    }

    let my_order = OnlineOrderStatus::Delivered(true);

    if let OnlineOrderStatus::Delivered(verified) = my_order {
        if verified {
            println!("Delivered and verified");
        } else {
            println!("Delivered but not verified");
        }
    }

    let my_order = OnlineOrderStatus::Shipped{ tracking_number: String::from("1234567890")};

    if let OnlineOrderStatus::Shipped{ tracking_number } = my_order {
        println!("Shipped with tracking number {tracking_number}");
    }

}