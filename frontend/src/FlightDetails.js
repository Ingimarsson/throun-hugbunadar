import React, { Component } from 'react';
import { Container, Segment, Header, Form, Input, Button, Select } from 'semantic-ui-react';
import axios from 'axios';

import FlightSegment from './FlightSegment';

class FlightDetails extends Component {
  constructor(props) {
    super(props);

    this.state = {
      flightNumber: '',
      result: null
    };
  }

  handleChange = (e, { name, value }) => this.setState({ [name]: value })

  search() {
    var url = "/api/flight?flightNumber="+this.state.flightNumber;

    axios.get(url).then(response => {
        console.log(response.data);
        this.setState({result: response.data});
    });
  }

  render() {
    return (
      <div>
        <Segment>
          <Header>Upplýsingar um flug</Header>
          <Form>
            <Form.Group>
              <Form.Field
                name='flightNumber'
                control={Input}
                label='Flugnúmer'
                placeholder='Flugnúmer'
                value={this.state.flightNumber}
                onChange={this.handleChange}
                width={16}
              />
            </Form.Group>
            <Form.Field primary control={Button} onClick={() => this.search()}>Leita</Form.Field>
          </Form>
        </Segment>
        { this.state.result && <div>
        <Header dividing>Flugupplýsingar</Header>
        <FlightSegment {...this.state.result} bookable={false}/>
        </div> }
      </div>
    );
  }
}

export default FlightDetails;
