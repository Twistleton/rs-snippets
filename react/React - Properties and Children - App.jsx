import React, {Component} from 'react'
import FrameworkLink from "./FrameworkLink";

class App extends Component {

    render() {
        return (
            <>
                <FrameworkLink framework="React" url="https://reactjs.org/" disabled="">
                    <strong>Learning React</strong>
                </FrameworkLink>
                <FrameworkLink framework="Angular" url="https://angular.io/" disabled="disabled">
                    Learning Angular
                </FrameworkLink>
            </>)
    }
}

export default App;

---

import React, { Component} from 'react';
class FrameworkLink extends Component {

    getButton(text) {
        return <button disabled={this.props.disabled}>{text}</button>;
    }

    render() {
        return (
            <p>
                <a href={this.props.url} target="_blank">{this.props.children}
                </a>
                <p>
                    {this.getButton('Okay')}
                </p>
            </p>
        );
    }
}

export default FrameworkLink