use std::collections::BinaryHeap;

fn main() {
    let mut jobs = BinaryHeap::new(); // Add jobs to do throughout the day

    jobs.push((100, "Reply to email from the CEO"));
    jobs.push((80, "Finish the report today"));
    jobs.push((5, "Watch some YouTube"));
    jobs.push((70, "Tell your team members thanks for always working hard"));
    jobs.push((30, "Plan who to hire next for the team"));

    for (_, job) in &jobs {
        println!("You need to: {job}");
    }

    while let Some((prio, description)) = &jobs.pop() {
        println!("Prio: {} {}", prio, description);
    }

    /*
    Prio: 100 Reply to email from the CEO
    Prio: 80 Finish the report today
    Prio: 70 Tell your team members thanks for always working hard
    Prio: 30 Plan who to hire next for the team
    Prio: 5 Watch some YouTube
    */

}