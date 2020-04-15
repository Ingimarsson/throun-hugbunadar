import React, { Component } from 'react';
import { Container, Dropdown, Checkbox, Grid, Segment, Header, Form, Input, Button, Select } from 'semantic-ui-react';
import axios from 'axios';
import moment from 'moment';

class Passenger extends Component {
  constructor(props) {
    super(props);

    this.state= {
      name: '',
      ssn: 0,
      email: '',
      phoneNumber: 0,
      seatNumber: '',
      luggage: false,
      gender: 'M',
      flightNumber: props.flightNumber
    }
  }

  handleChange = (e, { name, value }) => this.setState({ [name]: value }, () => this.update())
  handleToggle = (e, { name, checked }) => this.setState({ [name]: checked }, () => this.update())

  update() {
    var booking = this.state;
    booking.ssn = parseInt(booking.ssn);
    booking.phoneNumber = parseInt(booking.phoneNumber);
    this.props.update(this.props.passenger, booking);
  }

  render() {
    return (
        <Segment>
          <Header>Farþegi</Header>
          <Form>
            <Form.Group widths='equal'>
              <Form.Field
                name='name'
                control={Input}
                label='Nafn'
                placeholder='Nafn'
                value={this.state.name}
                onChange={this.handleChange}
              />
              <Form.Field
                name='ssn'
                control={Input}
                label='Kennitala'
                placeholder='Kennitala'
                value={this.state.ssn}
                onChange={this.handleChange}
                type='number'
              />
            </Form.Group>
            <Form.Group widths='equal'>
              <Form.Field
                name='email'
                control={Input}
                label='Email'
                placeholder='Email'
                value={this.state.email}
                onChange={this.handleChange}
              />
              <Form.Field
                name='phoneNumber'
                control={Input}
                label='Símanúmer'
                placeholder='Símanúmer'
                value={this.state.phoneNumber}
                onChange={this.handleChange}
                type='number'
              />
            </Form.Group>
            <Form.Group widths='equal'>
              <Form.Field
                name='seatNumber'
                control={Select}
                label='Sæti'
                placeholder='Sæti'
                value={this.state.seatNumber}
                options={this.props.seats}
                onChange={this.handleChange}
              />
              <Form.Field
                name='luggage'
                control={Checkbox}
                label='Farangur'
                placeholder='Farangur'
                value={this.state.luggage}
                onChange={this.handleToggle}
              />
 
            </Form.Group>
          </Form>
        </Segment>
      );
  }
}

class FlightBooking extends Component {
  constructor(props) {
    super(props);

    this.state = {
      bookingNumber: '',
      flight: null,
      result: null,
      seatOptions: null,
      booking: []
    };
  }

  updateBooking(index, passenger) {
    var b = this.state.booking;
    b[index] = passenger;
    this.setState({booking: b});
    console.log(b);
  }

  handleChange = (e, { name, value }) => this.setState({ [name]: value })

  componentDidMount() {
    var url = "/api/flight?flightNumber="+this.props.flightNumber;

    axios.get(url).then(response => {
        this.setState({flight: response.data});

        var seats = [];
        response.data.seats.map(function(s) {
          seats.push({
            key: s.seatNumber,
            text: s.seatNumber,
            value: s.seatNumber
          });
        });
        this.setState({seatOptions: seats});
    });

    var b = [];
    for (let i = 0; i < this.props.passengers; i++) {
      b.push({
        name: '',
        ssn: 0,
        email: '',
        phoneNumber: 0,
        seatNumber: '',
        luggage: false,
        gender: 'M',
        flightNumber: ''
      });
      this.setState({booking: b});
    }
  }

  calculatePrice() {
    var price = 0;
    var components=[];

    if (this.state.flight==null) {
      return components;
    }

    this.state.booking.map(function(b) {
      components.push(<div style={{marginTop:'14px'}}>{b.name}</div>);
      components.push(<b>{this.state.flight.price} kr.</b>);

      price += this.state.flight.price;

      this.state.flight.seats.map(function(x) {
        if (x.seatNumber == b.seatNumber) {
          components.push(<div>Sæti {b.seatNumber}</div>);
          components.push(<b>{x.price} kr.</b>);

          price += x.price;
        }
      });

      if (b.luggage) {
        components.push(<div>Farangur</div>);
        components.push(<b>{this.state.flight.luggagePrice} kr.</b>);

        price += this.state.flight.luggagePrice;
      }

    }, this);
    
    components.push(<div style={{marginTop:'14px'}}>Samtals</div>);
    components.push(<b>{price} kr.</b>);

    return components;
  }

  createPassengers() {
    var components = [];
    for (let i = 0; i < this.props.passengers; i++) {
      components.push(<Passenger 
        seats={this.state.seatOptions}
        passenger={i}
        update={this.updateBooking.bind(this)}
        flightNumber={this.props.flightNumber}
      />);
    }
    return components;
  }

  submitBooking() {
    console.log(this.state.booking);
    axios.post('/api/booking', this.state.booking).then(function(response) {
      console.log(response.data);
      alert("Bókunarnúmerið þitt er " + response.data);
    });
  }

  render() {
    return (
      <Grid>
        <Grid.Row>
          <Grid.Column width={12}>
          {this.createPassengers()}
          </Grid.Column>
          <Grid.Column width={4}>
            <Segment>
              <Header>Bókunaryfirlit</Header>
              { this.state.flight && this.calculatePrice() }
            </Segment>
            <Button primary fluid onClick={() => this.submitBooking()} >Staðfesta</Button>
          </Grid.Column>
        </Grid.Row>
      </Grid>
    );
  }
}

export default FlightBooking;
