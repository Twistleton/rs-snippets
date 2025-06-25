import React, {Component} from 'react'
import reactDOM from "react-dom"

class ReactLink extends Component {

    render() {
        console.log(Object.isFrozen(this.props))
        console.log(this.props)
        return React.createElement(
            'p',
            null,
            React.createElement(
                'a',
                {href: this.props.url},
                `Read more about ${this.props.description}`,
            )
        );
    }
}

const group = React.createElement(
    React.Fragment,
    null,
    React.createElement(ReactLink, {url: 'https://reactjs.org/',
        description: 'React'}),
    React.createElement(ReactLink, {url: 'https://go.dev/',
        description: 'Go'}),
    React.createElement(ReactLink, {url: 'https://www.rust-lang.org/',
        description: 'Rust'})

)

const domElement = document.getElementById('root');
reactDOM.render(group, domElement);