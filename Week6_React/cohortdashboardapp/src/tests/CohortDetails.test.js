import React from 'react';
import { shallow, mount } from 'enzyme';
import CohortDetails from '../components/CohortDetails';

describe('CohortDetails Component Tests', () => {
  const sampleCohort = {
    id: 1,
    code: 'INTADMDF10',
    coach: 'Ashwin',
    trainer: 'Jojo Jones',
    startDate: '22-Feb-2022',
    status: 'Ongoing',
  };

  // Unit Test 1
  test('should create the component', () => {
    const wrapper = shallow(<CohortDetails cohort={sampleCohort} />);
    expect(wrapper.exists()).toBe(true);
  });

  // Unit Test 2
  test('should initialize the props', () => {
    const wrapper = mount(<CohortDetails cohort={sampleCohort} />);
    expect(wrapper.props().cohort).toEqual(sampleCohort);
  });

  // Unit Test 3
  test('should display cohort code in h3', () => {
    const wrapper = mount(<CohortDetails cohort={sampleCohort} />);
    const h3Element = wrapper.find('h3');
    expect(h3Element.text()).toEqual(sampleCohort.code);
  });

  // Unit Test 4
  test('should always render same html', () => {
    const wrapper = shallow(<CohortDetails cohort={sampleCohort} />);
    expect(wrapper).toMatchSnapshot();
  });
});
