import React, { Component } from 'react';

class CountPeople extends Component {
  constructor(props) {
    super(props);
    this.state = {
      entrycount: 0,
      exitcount: 0,
    };
  }

  updateEntry = () => {
    this.setState((prevState) => ({
      entrycount: prevState.entrycount + 1,
    }));
  };

  updateExit = () => {
    this.setState((prevState) => ({
      exitcount: prevState.exitcount + 1,
    }));
  };

  render() {
    const { entrycount, exitcount } = this.state;

    return (
      <div className="counter-container">
        <div className="counter-section">
          <button className="counter-btn" onClick={this.updateEntry}>
            Login
          </button>
          <span className="counter-text">{entrycount} People Entered!!</span>
        </div>

        <div className="counter-section">
          <button className="counter-btn" onClick={this.updateExit}>
            Exit
          </button>
          <span className="counter-text">{exitcount} People Left!!</span>
        </div>
      </div>
    );
  }
}

export default CountPeople;
