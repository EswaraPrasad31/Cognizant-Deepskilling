import React from 'react';

const OddPlayers = ({ first, third, fifth }) => {
  return (
    <div>
      <h1 className="heading">Odd Players</h1>
      <ul className="player-list">
        <li>First : {first}</li>
        <li>Third : {third}</li>
        <li>Fifth : {fifth}</li>
      </ul>
    </div>
  );
};

export default OddPlayers;
