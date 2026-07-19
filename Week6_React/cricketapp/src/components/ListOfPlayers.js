import React from 'react';

const ListOfPlayers = ({ players }) => {
  return (
    <div>
      <h1 className="heading">List of Players</h1>
      <ul className="player-list">
        {players.map((player, index) => (
          <li key={index}>{player.name} {player.score}</li>
        ))}
      </ul>
    </div>
  );
};

export default ListOfPlayers;
