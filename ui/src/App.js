import React, { Component } from 'react';
import InsuranceOptions from './insurance_options/InsuranceOptions';
import EnquiryModal from './enquiry_modal/EnquiryModal';

class App extends Component {

  state = {
    modalOpen: false,
    selectedOption: null
  }

  constructor(props) {
    super(props);
    this.valueMap = new Map();
    this.valueMap.set('Bike', [0, 3000]);
    this.valueMap.set('Jewelry', [500, 10000]);
    this.valueMap.set('Electronics', [500, 6000]);
    this.valueMap.set('Sports Equipment', [0, 20000]);
}

  onOptionClick(value) {
    let ranges = this.valueMap.get(value.name);
    value.min = ranges[0];
    value.max = ranges[1];
    this.setState({open: true, selectedOption: value});
  }

  render() {
    return (
      <div className='row body'>
        <div className='col-12'>
          <InsuranceOptions onOptionClick={value => this.onOptionClick(value)} />
          { this.state.open && <EnquiryModal model={this.state.selectedOption} closeModal={() => this.setState({open: false, selectedOption: null})} /> }
        </div>
      </div>
    );
  }
}

export default App;
