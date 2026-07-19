import React from 'react';

const ScoreBelow70 = ({ players }) => {
  const filteredPlayers = players.filter((player) => player.score <= 70);

  return (
    <div>
      <h1 className="heading">List of Players having Scores Less than 70</h1>
      <ul className="player-list">
        {filteredPlayers.map((player, index) => (
          <li key={index}>{player.name} {player.score}</li>
        ))}
      </ul>
    </div>
  );
};

export default ScoreBelow70;
