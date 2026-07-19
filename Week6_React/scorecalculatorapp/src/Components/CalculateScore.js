import React from 'react';
import '../Stylesheets/mystyle.css';

const percentToDecimal = (decimal) => {
  return decimal.toFixed(2) + '%';
};

const calcScore = (total, goal) => {
  return percentToDecimal(total / goal);
};

const CalculateScore = ({ Name, School, total, goal }) => {
  return (
    <div className="formatstyle">
      <h2>Student Details</h2>
      <p>Name:</p>
      <p className="Name">{Name}</p>
      <p>School:</p>
      <p className="School">{School}</p>
      <p>Total:</p>
      <p className="Total">{total} Marks</p>
      <p>Score:</p>
      <p className="Score">{calcScore(total, goal)}</p>
    </div>
  );
};

export default CalculateScore;
