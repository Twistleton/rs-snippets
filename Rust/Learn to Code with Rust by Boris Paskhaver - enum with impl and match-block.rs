#[derive(Debug)]
enum OnlineOrderStatus {
    Ordered,
    Packed,
    Shipped,
    Delivered
}

impl OnlineOrderStatus {
    fn check_status(&self) {
        match self {
            OnlineOrderStatus::Packed | OnlineOrderStatus::Shipped => println!("Your item is on its way!"),
            order_status => println!("Your item is still being processed, current state is {order_status:?}")
        }
    }
}

fn main() {

    OnlineOrderStatus::check_status(&OnlineOrderStatus::Shipped);
    OnlineOrderStatus::check_status(&OnlineOrderStatus::Ordered);

}