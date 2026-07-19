import React, { useState } from 'react';

const BookingSection = () => {
  const [passengerName, setPassengerName] = useState('');
  const [numTickets, setNumTickets] = useState('');
  const [booked, setBooked] = useState(false);

  const handleBook = () => {
    if (passengerName.trim() && numTickets) {
      setBooked(true);
    }
  };

  return (
    <div style={{ marginTop: '40px' }}>
      <h2 style={{ fontSize: '28px', fontWeight: 'bold', color: 'black', marginBottom: '14px' }}>
        Book Ticket
      </h2>

      <div style={{ marginBottom: '10px', display: 'flex', alignItems: 'center', gap: '10px' }}>
        <label style={{ fontSize: '18px', color: 'black', width: '160px' }}>Passenger Name</label>
        <input
          type="text"
          value={passengerName}
          onChange={(e) => setPassengerName(e.target.value)}
          style={{ width: '150px', height: '30px', border: '1px solid gray', fontSize: '15px', padding: '2px 6px' }}
        />
      </div>

      <div style={{ marginBottom: '10px', display: 'flex', alignItems: 'center', gap: '10px' }}>
        <label style={{ fontSize: '18px', color: 'black', width: '160px' }}>Number of Tickets</label>
        <input
          type="number"
          value={numTickets}
          onChange={(e) => setNumTickets(e.target.value)}
          style={{ width: '150px', height: '30px', border: '1px solid gray', fontSize: '15px', padding: '2px 6px' }}
        />
      </div>

      <div style={{ marginTop: '10px', marginLeft: '170px' }}>
        <button className="app-btn" onClick={handleBook}>
          Book
        </button>
      </div>

      {booked && (
        <p style={{ fontSize: '18px', color: 'green', marginTop: '14px', fontWeight: 'bold' }}>
          Ticket booked successfully!
        </p>
      )}
    </div>
  );
};

export default BookingSection;
