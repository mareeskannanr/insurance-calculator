import React, { Component } from 'react';

export default class InsuranceOptions extends Component {

    state = {
        options : [{
            name: "Bike",
            class: "motorcycle",
            coverage: "(Coverage 0-3k, Risk 30%)"
        },{
            name: "Jewelry",
            class: "gem",
            coverage: "(Coverage 500-10k, Risk 5%)"
        },{
            name: "Electronics",
            class: "laptop-medical",
            coverage: "(Coverage 500-6k, Risk 35%)"
        },{
            name: "Sports Equipment",
            class: "baseball-ball",
            coverage: "(Coverage 0-20k, Risk 30%)"
        }]
    }

    openEnquiryModal(insuranceType) {
        let value = this.state.options.filter(item => item.name === insuranceType);
        this.props.onOptionClick(value[0]);
    }

    render() {
        return (
            <div className='col-12'>
                <div className='row'>
                    <div className='col-2'>
                        <img src='images/element.png' alt='Element' style={{'width': '150px'}} />
                    </div>
                    <div className='col-9 text-left'>
                        <h2 className="d-none d-sm-block">Insurance Options</h2>
                    </div>
                </div>
                <hr />
                <h4>Please click on the option</h4>
                <div className="card-body">
                    <div className='row'>
                        {
                            this.state.options.map(item => (
                                <div key={item.name} className='col-8 offset-2 insurance-type bg-light' onClick={() => this.openEnquiryModal(item.name)}>
                                    <div className='row clearfix'>
                                        <div className='col-2'>
                                            <span className={`pad fas fa-5x fa-${item.class}`}></span>
                                        </div>
                                        <div className='col-10 d-none d-sm-block'>
                                            <span className='model-name'>{item.name}</span><br />
                                            <span className='coverage'><b>{item.coverage}</b></span>
                                        </div>
                                    </div>
                                </div>
                            ))
                        }
                    </div>
                </div>
            </div>
        );
    }

}