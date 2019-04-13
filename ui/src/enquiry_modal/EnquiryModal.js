
import React, { Component } from 'react';
import { Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import axios from '../axios';

export default class EnquiryModal extends Component {

    state = {
        result: null,
        errorMessage: '',
        error: true,
        coverage: 0
    };

    onChange(coverage) {
        let model = this.props.model;
        let error = !(coverage >= model.min && coverage <= model.max);
        this.setState({
            errorMessage: error ? `Coverage must be between € ${model.min} and € ${model.max}` : '',
            error,
            coverage
        });
    }

    calculate() {
        let enquiry = {
            coverage: this.state.coverage,
            insuranceType: this.props.model.name
        };
        console.log(enquiry);
        axios.post('/api/enquiry', enquiry)
            .then(result => {
                this.setState({
                    result: `Price of the tariff for the coverage € ${this.state.coverage} is € ${result.data}`
                });
            })
            .catch(error => {
                console.error(error);
                this.setState({
                    result: 'Sorry, something went wrong! Try Again!'
                });
            });
    }

    render() {
        let model = this.props.model;
        return (
            <div>
                <Modal isOpen={true}>
                    <ModalHeader><span className={`fas fa-${model.class}`}></span> {model.name}</ModalHeader>
                    <ModalBody>
                        {
                            (this.state.result == null) ? (
                            <div className="row">
                                <div className="col-8 offset-2 form-group">
                                    <input type='number' placeholder="Enter Your Coverage" min='0' max='20000' onChange={e => this.onChange(e.target.value)} className="form-control" /><br />
                                    {this.state.errorMessage && <small className='text-danger'>{this.state.errorMessage}</small>}
                                </div>
                            </div>) : (
                            <div>
                                <h5 className={this.state.result.includes('Price') ? 'text-info' : 'text-danger'}>
                                    {this.state.result}
                                </h5>
                            </div>)
                        }
                    </ModalBody>
                    <ModalFooter>
                        <div className="col-12 text-center">
                            {
                                this.state.result == null &&
                                <button type="button" className="btn btn-success" onClick={() => this.calculate()} disabled={this.state.error}>
                                    <span className="fas fa-calculator"></span> Calculate
                                </button>
                            }&nbsp;&nbsp;
                            <button type="button" className="btn btn-danger" onClick={() => this.props.closeModal()}>
                                <span className="fa fa-times"></span> {this.state.result == null ? 'Cancel' : 'Close'}
                            </button>
                        </div>
                    </ModalFooter>
                </Modal>
            </div>
        );
    }

}