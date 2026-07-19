import React from 'react';
import { books, showBooks } from '../data';

// Conditional Rendering: if...else (Method 1)
const BookDetails = () => {
  // Method 1: if...else
  if (!showBooks) {
    return <p>No books available.</p>;
  }

  // Method 3: Logical && (used inside JSX below)
  // Method 2: Ternary (used for price label)
  return (
    <div>
      <h2 className="panel-heading">Book Details</h2>
      {books.map((book) => (
        <div key={book.id} style={{ marginBottom: '18px' }}>
          <p style={{ fontSize: '26px', fontWeight: 'bold', margin: '0' }}>
            {book.bname}
          </p>
          {/* Method 2: Ternary Operator */}
          <p style={{ fontSize: '16px', margin: '4px 0 0 0' }}>
            {book.price > 0 ? `Rs. ${book.price}` : 'Free'}
          </p>
          {/* Method 3: Logical && */}
          {book.price > 700 && (
            <p style={{ fontSize: '13px', color: 'darkgreen', margin: '2px 0 0 0' }}>
              ★ Premium
            </p>
          )}
        </div>
      ))}
    </div>
  );
};

export default BookDetails;
