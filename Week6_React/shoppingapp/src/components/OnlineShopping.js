import React, { Component } from 'react';
import CartModel from '../models/Cart';
import Cart from './Cart';

class OnlineShopping extends Component {
  render() {
    const items = [
      new CartModel('Laptop', 80000),
      new CartModel('TV', 120000),
      new CartModel('Washing Machine', 50000),
      new CartModel('Mobile', 30000),
      new CartModel('Fridge', 70000),
    ];

    return <Cart items={items} />;
  }
}

export default OnlineShopping;
