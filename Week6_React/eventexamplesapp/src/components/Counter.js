import React, { useState } from 'react';

const Counter = () => {
  const [count, setCount] = useState(0);

  const incrementCount = () => {
    setCount((prev) => prev + 1);
  };

  const showAlert = () => {
    alert('Hello! Member');
  };

  const handleIncrement = () => {
    incrementCount();
    showAlert();
  };

  const handleDecrement = () => {
    setCount((prev) => prev - 1);
  };

  const sayWelcome = (message) => {
    alert(message);
  };

  const handleSyntheticClick = (event) => {
    alert('I was clicked');
  };

  return (
    <div style={{ fontFamily: 'Arial', marginLeft: '30px', marginTop: '30px' }}>
      <p style={{ fontSize: '20px', fontWeight: 'bold', marginBottom: '10px' }}>
        Counter Value : {count}
      </p>

      <div style={{ display: 'flex', flexDirection: 'column', gap: '8px', width: '90px' }}>
        <button className="app-btn" onClick={handleIncrement}>
          Increment
        </button>
        <button className="app-btn" onClick={handleDecrement}>
          Decrement
        </button>
        <button className="app-btn" onClick={() => sayWelcome('Welcome')}>
          Say Welcome
        </button>
        <button className="app-btn" onClick={handleSyntheticClick}>
          Click on me
        </button>
      </div>
    </div>
  );
};

export default Counter;
