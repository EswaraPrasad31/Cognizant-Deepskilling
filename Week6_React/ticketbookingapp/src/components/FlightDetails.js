import React from 'react';

const flights = [
  {
    flightNo: 'AI-201',
    source: 'Chennai',
    destination: 'Delhi',
    departure: '08:00 AM',
    arrival: '11:00 AM',
    price: 'Rs. 5500',
  },
  {
    flightNo: 'SG-305',
    source: 'Mumbai',
    destination: 'Bangalore',
    departure: '10:30 AM',
    arrival: '12:15 PM',
    price: 'Rs. 3800',
  },
  {
    flightNo: '6E-412',
    source: 'Hyderabad',
    destination: 'Kolkata',
    departure: '02:00 PM',
    arrival: '04:30 PM',
    price: 'Rs. 4200',
  },
];

const FlightDetails = () => {
  return (
    <div style={{ marginTop: '80px' }}>
      <h2 style={{ fontSize: '28px', fontWeight: 'bold', color: 'black', marginBottom: '12px' }}>
        Available Flights
      </h2>
      <table className="flight-table">
        <thead>
          <tr>
            <th>Flight</th>
            <th>Source</th>
            <th>Destination</th>
            <th>Departure</th>
            <th>Arrival</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          {flights.map((flight, index) => (
            <tr key={index}>
              <td>{flight.flightNo}</td>
              <td>{flight.source}</td>
              <td>{flight.destination}</td>
              <td>{flight.departure}</td>
              <td>{flight.arrival}</td>
              <td>{flight.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default FlightDetails;
