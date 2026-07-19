import React from 'react';
import OddPlayers from './OddPlayers';
import EvenPlayers from './EvenPlayers';

const IndianPlayers = () => {
  const indianPlayers = ['Sachin', 'Dhoni', 'Virat', 'Rohit', 'Yuvraj', 'Sehwag'];

  const [first, second, third, fourth, fifth, sixth] = indianPlayers;

  return (
    <div>
      <OddPlayers first={first} third={third} fifth={fifth} />
      <hr className="divider" />
      <EvenPlayers second={second} fourth={fourth} sixth={sixth} />
    </div>
  );
};

export default IndianPlayers;
