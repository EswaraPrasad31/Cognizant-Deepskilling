import React from 'react';
import office1 from '../assets/office1.jpg';
import office2 from '../assets/office2.jpg';
import office3 from '../assets/office3.jpg';

const OfficeSpace = () => {
  const officeList = [
    {
      id: 1,
      name: 'DBS Business Hub',
      rent: 50000,
      address: 'Chennai',
      image: office1,
    },
    {
      id: 2,
      name: 'Prestige Tech Park',
      rent: 75000,
      address: 'Bangalore',
      image: office2,
    },
    {
      id: 3,
      name: 'Cyber Gateway Tower',
      rent: 60000,
      address: 'Hyderabad',
      image: office3,
    },
    {
      id: 4,
      name: 'Express Avenue Suites',
      rent: 45000,
      address: 'Chennai',
      image: office1,
    },
    {
      id: 5,
      name: 'Skyline Corporate Park',
      rent: 90000,
      address: 'Mumbai',
      image: office2,
    },
  ];

  return (
    <div style={{ fontFamily: 'Arial', marginLeft: '80px', paddingTop: '30px' }}>
      <h1 style={{ fontSize: '34px', fontWeight: 'bold', color: 'black', marginBottom: '30px' }}>
        Office Space , at Affordable Range
      </h1>

      {officeList.map((office) => (
        <div key={office.id} style={{ marginBottom: '50px' }}>
          <img
            src={office.image}
            alt={office.name}
            style={{ width: '200px', height: '170px', objectFit: 'cover', display: 'block', marginBottom: '10px' }}
          />
          <p style={{ fontSize: '28px', fontWeight: 'bold', color: 'black', margin: '4px 0' }}>
            Name: {office.name}
          </p>
          <p
            style={{
              fontSize: '24px',
              fontWeight: 'bold',
              color: office.rent <= 60000 ? 'red' : 'green',
              margin: '4px 0',
            }}
          >
            Rent: Rs. {office.rent}
          </p>
          <p style={{ fontSize: '22px', color: 'black', margin: '4px 0' }}>
            Address: {office.address}
          </p>
        </div>
      ))}
    </div>
  );
};

export default OfficeSpace;
