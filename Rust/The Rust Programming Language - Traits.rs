pub trait Summary {
    fn summarize(&self) -> String;
}

pub struct NewsArticle {
    pub headline: String,
    pub location: String,
    pub author: String,
    pub content: String,
}

impl Summary for NewsArticle {
    fn summarize(&self) -> String {
        format!("{}, by {} ({})", self.headline, self.author, self.location)
    }
}

pub struct Tweet {
    pub text: String,
    pub author: String,
    pub retweet: bool,
}


impl Summary for Tweet {
    fn summarize(&self) -> String {
        format!("{} by {} {}", self.text, self.author, if self.retweet {"Retweet"} else {"first"})
    }
}


fn main() {
    let sz = NewsArticle {
        headline: "Winter in New York".to_string(),
        location: "SZ - Dritte Seite".to_string(),
        author: "Christian Zaschke".to_string(),
        content: "Artikel".to_string(),
    };


    println!("{}", sz.summarize());

    let tweet = Tweet {
        text: "Scala 4 published".to_string(),
        author: "Martin Odersky".to_string(),
        retweet: true,
    };

    println!("{}", tweet.summarize());

}