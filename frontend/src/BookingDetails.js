import React, { Component } from 'react';
import { Container, Grid, Segment, Header, Form, Input, Button, Select } from 'semantic-ui-react';
import axios from 'axios';

class BookingDetails extends Component {
  constructor(props) {
    super(props);

    this.state = {
      bookingNumber: '',
      result: null
    };
  }

  handleChange = (e, { name, value }) => this.setState({ [name]: value })

  renderBooking(booking) {
    return (
      <Segment>
        <Grid>
          <Grid.Row>
            <Grid.Column width={10}>
              <Header>Farnúmer {booking.bookingNumber}</Header>
              <p>{booking.passenger.name}</p>
              <p>{booking.passenger.email}</p>
              <p>{booking.passenger.phoneNumber}</p>
            </Grid.Column>
            <Grid.Column width={6}>
              <Header>Flug {booking.flightNumber}</Header>
              <Header>Sæti {booking.seat}</Header>
              { booking.luggage ? <p>Með farangri</p> : <p> Án farangurs</p> }
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </Segment>
    );
  }

  search() {
    var url = "/api/booking?bookingNumber="+this.state.bookingNumber;

    axios.get(url).then(response => {
        console.log(response.data);
        this.setState({result: response.data});
    });
  }

  render() {
    return (
      <div>
        <Segment>
          <Header>Bókunin mín</Header>
          <Form>
            <Form.Group>
              <Form.Field
                name='bookingNumber'
                control={Input}
                label='Bókunarnúmer'
                placeholder='Bókunarnúmer'
                value={this.state.bookingNumber}
                onChange={this.handleChange}
                width={16}
              />
            </Form.Group>
            <Form.Field primary control={Button} onClick={() => this.search()}>Leita</Form.Field>
          </Form>
        </Segment>
        { this.state.result && <div>
        <Header dividing>Bókunarupplýsingar</Header>
        <Header as='h1'>Bókunarnúmer {this.state.result.groupBookingNumber}</Header>
        {this.state.result.bookings.map(this.renderBooking)}
        </div> }
      </div>
    );
  }
}

export default BookingDetails;
