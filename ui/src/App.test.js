import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

import EnquiryModal from './enquiry_modal/EnquiryModal';
import InsuranceOptions from './insurance_options/InsuranceOptions';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<App />, div);
  ReactDOM.unmountComponentAtNode(div);
});

it('Enquiry Modal renders without Crash', () => {
  const div = document.createElement('div');
  let dataModel = {
    name: "Bike",
    class: "motorcycle"
  };

  ReactDOM.render(<EnquiryModal model={dataModel} />, div);
  ReactDOM.unmountComponentAtNode(div);
});

it('Insurance Options renders without Crash', () => {
  const div = document.createElement('div');
  ReactDOM.render(<InsuranceOptions />, div);
  ReactDOM.unmountComponentAtNode(div);
});