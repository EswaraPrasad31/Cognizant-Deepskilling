import React, { useState } from 'react';

const CurrencyConverter = () => {
  const [amount, setAmount] = useState('');
  const [currency, setCurrency] = useState('');
  const [result, setResult] = useState(null);

  const CONVERSION_RATE = 90;

  const handleSubmit = (event) => {
    event.preventDefault();

    const inrAmount = parseFloat(amount);
    const convertedAmount = (inrAmount / CONVERSION_RATE).toFixed(2);
    setResult(convertedAmount);

    alert('Currency is converted to Euro');
  };

  return (
    <div style={{ fontFamily: 'Arial', marginLeft: '30px', marginTop: '100px' }}>
      <h2
        style={{
          fontSize: '36px',
          fontWeight: 'bold',
          color: 'darkgreen',
          marginBottom: '20px',
        }}
      >
        Currency Convertor!!!
      </h2>

      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '10px', display: 'flex', alignItems: 'center', gap: '10px' }}>
          <label style={{ fontSize: '18px', color: 'black', width: '80px' }}>Amount</label>
          <input
            type="number"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            style={{ width: '130px', height: '30px', fontSize: '15px', border: '1px solid gray', padding: '2px 5px' }}
          />
        </div>

        <div style={{ marginBottom: '10px', display: 'flex', alignItems: 'center', gap: '10px' }}>
          <label style={{ fontSize: '18px', color: 'black', width: '80px' }}>Currency</label>
          <input
            type="text"
            value={currency}
            onChange={(e) => setCurrency(e.target.value)}
            style={{ width: '130px', height: '30px', fontSize: '15px', border: '1px solid gray', padding: '2px 5px' }}
          />
        </div>

        <div style={{ marginLeft: '90px', marginTop: '8px' }}>
          <button type="submit" className="app-btn">
            Submit
          </button>
        </div>
      </form>

      {result !== null && (
        <p style={{ fontSize: '18px', marginTop: '15px', color: 'black' }}>
          {amount} INR = {result} {currency || 'Euro'}
        </p>
      )}
    </div>
  );
};

export default CurrencyConverter;
