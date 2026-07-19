import React from 'react';
import ListOfPlayers from './components/ListOfPlayers';
import ScoreBelow70 from './components/ScoreBelow70';
import IndianPlayers from './components/IndianPlayers';
import ListOfIndianPlayers from './components/ListOfIndianPlayers';
import './App.css';

const App = () => {
  const players = [
    { name: 'Mr. Jack',    score: 50 },
    { name: 'Mr. Michael', score: 70 },
    { name: 'Mr. John',    score: 40 },
    { name: 'Mr. Steve',   score: 90 },
    { name: 'Mr. David',   score: 65 },
    { name: 'Mr. Chris',   score: 80 },
    { name: 'Mr. Kevin',   score: 55 },
    { name: 'Mr. Brian',   score: 95 },
    { name: 'Mr. Tony',    score: 60 },
    { name: 'Mr. Paul',    score: 75 },
    { name: 'Mr. James',   score: 45 },
  ];

  const flag = false;

  if (flag) {
    return (
      <div>
        <ListOfPlayers players={players} />
        <hr className="divider" />
        <div className="section-gap">
          <ScoreBelow70 players={players} />
        </div>
      </div>
    );
  } else {
    return (
      <div>
        <IndianPlayers />
        <hr className="divider" />
        <div className="section-gap">
          <ListOfIndianPlayers />
        </div>
      </div>
    );
  }
};

export default App;
