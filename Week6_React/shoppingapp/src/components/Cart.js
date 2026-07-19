import React, { Component } from 'react';

class Cart extends Component {
  render() {
    const { items } = this.props;

    return (
      <div style={{ textAlign: 'center' }}>
        <h1 className="heading">Items Ordered :</h1>
        <table className="cart-table">
          <thead>
            <tr>
              <th>Name</th>
              <th>Price</th>
            </tr>
          </thead>
          <tbody>
            {items.map((item, index) => (
              <tr key={index}>
                <td>{item.itemname}</td>
                <td>{item.price}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default Cart;
