import React, { Component } from 'react';

class GetUser extends Component {
  constructor(props) {
    super(props);
    this.state = {
      loading: true,
      person: null,
      error: null,
    };
  }

  async componentDidMount() {
    try {
      const response = await fetch('https://api.randomuser.me/');
      if (!response.ok) {
        throw new Error('Unable to load user information.');
      }
      const data = await response.json();
      this.setState({
        person: data.results[0],
        loading: false,
      });
    } catch (err) {
      this.setState({
        error: 'Unable to load user information.',
        loading: false,
      });
    }
  }

  render() {
    const { loading, person, error } = this.state;

    if (loading) {
      return <div className="user-container loading-text">Loading User...</div>;
    }

    if (error || !person) {
      return <div className="user-container error-text">Unable to load user information.</div>;
    }

    const { title, first, last } = person.name;

    return (
      <div className="user-container">
        <h1 className="user-name">
          {title} {first} {last}
        </h1>
        <img
          src={person.picture.large}
          alt={`${first} ${last}`}
          className="user-image"
        />
      </div>
    );
  }
}

export default GetUser;
