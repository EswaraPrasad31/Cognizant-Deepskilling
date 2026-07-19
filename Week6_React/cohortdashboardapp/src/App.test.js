import React from 'react';
import { shallow } from 'enzyme';
import App from './App';

test('renders Cohort Dashboard title', () => {
  const wrapper = shallow(<App />);
  expect(wrapper.find('.dashboard-title').text()).toBe('Cohort Dashboard');
});
