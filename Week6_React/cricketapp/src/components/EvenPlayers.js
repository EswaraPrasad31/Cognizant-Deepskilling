import React from 'react';

const EvenPlayers = ({ second, fourth, sixth }) => {
  return (
    <div>
      <h1 className="heading">Even Players</h1>
      <ul className="player-list">
        <li>Second : {second}</li>
        <li>Fourth : {fourth}</li>
        <li>Sixth : {sixth}</li>
      </ul>
    </div>
  );
};

export default EvenPlayers;
