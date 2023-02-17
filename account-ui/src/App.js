import logo from './logo.svg';
import './App.css';
import {Component} from "react";

class App extends Component {
  state = {
    customer: {}
  };

  async componentDidMount() {
    const response = await fetch("/v1/customer/caacc088-257f-4bb3-8e28-3827bd425e9f");
    const body = await response.json();
    this.setState({customer: body});
  }

  render() {
    const {customer} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo"/>
            <div className="App-intro">
              <h2>Customers</h2>
              <div key={customer.id}>
                {customer.name} {customer.surname}
              </div>
            </div>
          </header>
        </div>
    )
  }

}

export default App;