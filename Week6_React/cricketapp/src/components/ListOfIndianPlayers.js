import React from 'react';

const ListOfIndianPlayers = () => {
  const T20Players = ['Mr. Sachin', 'Mr. Dhoni', 'Mr. Virat'];
  const RanjiTrophyPlayers = ['Mr. Rohit', 'Mr. Yuvraj', 'Mr. Sehwag'];

  const mergedPlayers = [...T20Players, ...RanjiTrophyPlayers];

  return (
    <div>
      <h1 className="heading">List of Indian Players Merged</h1>
      <ul className="player-list">
        {mergedPlayers.map((player, index) => (
          <li key={index}>{player}</li>
        ))}
      </ul>
    </div>
  );
};

export default ListOfIndianPlayers;
